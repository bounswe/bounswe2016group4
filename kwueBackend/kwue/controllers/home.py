from django.shortcuts import render
from django.http import HttpResponse
from kwue.DB_functions.food_db_functions import *
from kwue.DB_functions.consumption_history_db_functions import db_search_consumption_foods
from kwue.DB_functions.search_db_function import *
from kwue.DB_functions.user_db_function import *
from kwue.DB_functions.user_db_function import db_retrieve_eating_preferences
from kwue.models.models import *
from datetime import datetime
import time
from collections import Counter
from django.views.decorators.csrf import csrf_exempt
from django.views.decorators.csrf import requires_csrf_token
from django.http import JsonResponse

def get_home(req):
    food = db_retrieve_all_foods()

    if req.session.has_key('user_id') is False:
        req.session['user_id'] = -2

    user_id = req.session['user_id']
    if user_id != -2:
        user = db_retrieve_user(user_id)
        user_name = user.user_name
        user_image = user.user_image
        user_type = user.user_type
    else:
        return render(req, 'kwue/home.html', {'recommendations': food, 'user_type': 0, 'user_name': 'Guest'})

    if user_type is False:
        recommendation = suggest(user_id)
        return render(req, 'kwue/home.html', {'recommendations': recommendation, 'user_type': 0, 'user_name': user_name, 'user_id': user_id})
    else:
        analysis_report = analyze(user_id)
        return render(req, 'kwue/home.html', {'analysis_report': analysis_report, 'user_type': 1, 'user_name': user_name, 'user_image': user_image})


def suggest(user_id):
    #############
    #############
    #############
    suggested_total_fat = 65
    suggested_protein = 50
    suggested_carbohydrate = 300
    suggested_fiber = 25
    suggested_calories = 2000
    suggested_sugar = 30
    ##############
    ##############
    ##############
    consumed_total_fat = 0
    consumed_protein = 0
    consumed_carbohydrate = 0
    consumed_fiber = 0
    consumed_calories = 0
    consumed_sugar = 0
    start = int(datetime.utcnow().date().strftime("%s")) + 6*60*60
    end = time.time() + 3 * 60 * 60
    if end < start:
        start -= 24 * 60 * 60
    records = db_search_consumption_foods(start , end , user_id)
    count = 1
    for record in records:
        consumed_total_fat += record.fat_value
        consumed_protein += record.protein_value
        consumed_carbohydrate += record.carbohydrate_value
        consumed_fiber += record.fiber_value
        consumed_calories += record.calorie_value
        consumed_sugar += record.sugar_value
        if consumed_calories > 400:
            count += 1
    #######################
    #######################
    #######################
    if count < 4:
        coefficient = count / 3
    else:
        coefficient = 1
    suggested_total_fat = coefficient * 1 * suggested_total_fat - consumed_total_fat
    if suggested_total_fat < 0:
        suggested_total_fat = 0
    suggested_protein = coefficient * 1 * suggested_protein - consumed_protein
    if suggested_protein < 0:
        suggested_protein = 0
    suggested_carbohydrate = coefficient * 1 * suggested_carbohydrate - consumed_carbohydrate
    if suggested_carbohydrate < 0:
        suggested_carbohydrate = 0
    suggested_fiber = coefficient * 1 * suggested_fiber - consumed_fiber
    if suggested_fiber < 0:
        suggested_fiber = 0
    suggested_sugar = coefficient * 1 * suggested_sugar - consumed_sugar
    if suggested_sugar < 0:
        suggested_sugar = 0
    suggested_calories = coefficient * 1 * suggested_calories - consumed_calories
    if suggested_calories < 0:
        suggested_calories = -100
    #######################
    #######################
    #######################
    user_preferences = db_retrieve_eating_preferences(user_id)
    foods_query = unwanted_search(user_preferences["unwanted_list"])
    foods_query = calorie_search(suggested_calories-200, suggested_calories+200,foods_query)
    foods_query = sugar_search(0,suggested_sugar+10,foods_query)
    foods = list(fat_search(suggested_total_fat-10,suggested_total_fat+10,foods_query))
    foods.extend(list(protein_search(suggested_protein-10,suggested_protein+10,foods_query)))
    foods.extend(list(carbohydrate_search(suggested_carbohydrate-30, suggested_carbohydrate+30,foods_query)))
    foods.extend(list(fiber_search(suggested_fiber-10,suggested_fiber+10,foods_query)))
    suggested_foods = []
    food_id_list = []
    for food in foods:
        food_id_list.append(food.food_id)
    food_id_list = [ite for ite, it in Counter(food_id_list).most_common(3)]
    for id in food_id_list:
        food = db_retrieve_food(id)
        food_dict = dict(
            food_name=food.food_name,
            food_id=food.food_id,
            food_image=food.food_image,
            food_rate=food.food_rate,
            food_description = food.food_description,
            food_owner = food.food_owner,
            calorie_value=food.calorie_value
        )
        suggested_foods.append(food_dict)
    return suggested_foods


def analyze(user_id, setting='monthly'):
    user = db_retrieve_user(user_id)
    user_foods = list(FoodModel.objects.filter(food_owner=user))
    rate_foods = list(FoodModel.objects.filter(food_owner=user).order_by("-food_rate")[:5])
    comment_foods = list(SimpleComment.objects.filter(food__in=user_foods).order_by("-date")[:5])
    if setting is  'daily':
        start_time = time.time() + 3*60*60 - 86400
    elif setting is 'monthly':
        start_time = time.time() + 3*60*60 - 2592000
    else:
        start_time = time.time() + 3*60*60 - 604800
    end_time = time.time() + 3*60*60

    last_comment_number = SimpleComment.objects.filter(food__in=user_foods, date__range=(start_time, end_time)).count()
    last_consumed_number = ConsumptionHistory.objects.filter(food__in=user_foods, date__range=(start_time, end_time)).count()
    last_consumption_records = list(ConsumptionHistory.objects.filter(food__in=user_foods, date__range=(start_time, end_time)))
    last_comments = list(SimpleComment.objects.filter(food__in=user_foods, date__range=(start_time, end_time)))
    last_consumed_food_id = []
    last_commneted_food_id = []

    for record in last_consumption_records:
        last_consumed_food_id.append(record.food.food_id)
    for comment in last_comments:
       last_commneted_food_id.append(comment.food.food_id)

    most_consumed_food_id = [ite for ite, it in Counter(last_consumed_food_id).most_common(5)]
    most_commented_food_id = [ite for ite, it in Counter(last_commneted_food_id).most_common(5)]
    most_consumed_food = []
    most_commented_food = []

    for food_id in most_commented_food_id:
       food = FoodModel.objects.get(food_id=food_id)
       food_dict = dict(
           food_id=food.food_id,
           food_name=food.food_name,
           food_image=food.food_image,
           food_rate=food.food_rate
       )
       most_commented_food.append(food_dict)

    for food_id in most_consumed_food_id:
        food = FoodModel.objects.get(food_id=food_id)
        food_dict = dict(
            food_id=food.food_id,
            food_name=food.food_name,
            food_image=food.food_image,
            food_rate=food.food_rate
        )
        most_consumed_food.append(food_dict)

    high_rate_foods = []
    for food in rate_foods:
        food_dict = dict(
            food_id=food.food_id,
            food_name=food.food_name,
            food_image=food.food_image,
            food_rate=food.food_rate
        )
        high_rate_foods.append(food_dict)

    last_comments =[]
    for comment in comment_foods:
        comment_dict = dict(
            comment_text=comment.comment_text,
            comment_id=comment.comment_id,
            food_id=comment.food.food_id,
            food_name=comment.food.food_name,
            food_image=comment.food.food_image,
            user_id=comment.user.user_id,
            user_image=comment.user.user_image,
            user_name=comment.user.user_name
        )
        last_comments.append(comment_dict)

    analysis_report=dict(
        comment_number=last_comment_number,
        consumed_number=last_consumed_number,
        high_rated_foods=high_rate_foods,
        most_consumed_foods=most_consumed_food,
        most_commented_foods=most_commented_food,
        last_comments=last_comments,
    )
    return analysis_report


@csrf_exempt
@requires_csrf_token
def create_session(req):
    if req.session.has_key('user_id') is False:
        req.session['user_id'] = -2

    return HttpResponse(json.dumps({'is_success': True}), content_type='application/json')


@csrf_exempt
def get_home_url(req):
    user_id = req.GET.dict()['user_id']

    user = db_retrieve_user(user_id)
    user_name = user.user_name
    user_image = user.user_image
    user_type = user.user_type

    if user_type is False:
        recommendation = suggest(user_id)
        print(recommendation)
        return JsonResponse({'recommendations': recommendation, 'user_type': 0, 'user_name': user_name, 'user_id': user_id})
    else:
        analysis_report = analyze(user_id)
        print(analysis_report)
        return JsonResponse({'analysis_report': analysis_report, 'user_type': 1, 'user_name': user_name, 'user_image': user_image})
