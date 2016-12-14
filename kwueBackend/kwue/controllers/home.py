from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *
from kwue.DB_functions.consumption_history_db_functions import db_search_consumption_foods
from kwue.DB_functions.search_db_function import *
from kwue.DB_functions.user_db_function import db_retrieve_eating_preferences
from datetime import datetime, timedelta, timezone
import time
from collections import Counter



def get_home(req):
    food = db_retrieve_all_foods()
    #req.session['has_access']=True
    #req.session['id']=11
    req.session['username'] = 'doruk1994'
    print(req.session['username']+ " has been authenticated")
    return render(req, 'kwue/home.html', {'foods': food})


def suggest(user_id):
    #############
    #############
    #############
    suggested_total_fat = 65
    suggested_protein = 50
    suggested_carbohydrate = 300
    suggested_fiber = 25
    suggested_calories = 2000
    suggested_sugar = 3
    ##############
    ##############
    ##############
    consumed_total_fat = 0
    consumed_protein = 0
    consumed_carbohydrate = 0
    consumed_fiber = 0
    consumed_calories = 0
    consumed_sugar = 30
    start = int(datetime.utcnow().date().strftime("%s")) + 6*60*60
    end = time.time() + 3 * 60 * 60
    records = db_search_consumption_foods(start,end,user_id)
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
        suggested_carbohydrate= 0
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
    foods = list(fat_search(suggested_total_fat-10,suggested_total_fat+10,foods_query))
    foods.extend(list(protein_search(suggested_protein-10,suggested_protein+10,foods_query)))
    foods.extend(list(carbohydrate_search(suggested_carbohydrate-30, suggested_carbohydrate+30,foods_query)))
    foods.extend(list(fiber_search(suggested_fiber-10,suggested_fiber+10,foods_query)))
    foods.extend(list(sugar_search(suggested_sugar-10,suggested_sugar+10,foods_query)))
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
            calorie_value=food.calorie_value
        )
        suggested_foods.append(food_dict)
    return suggested_foods
