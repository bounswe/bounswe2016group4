from django.shortcuts import render
from kwue.DB_functions.consumption_history_db_functions import *
from unixtimestampfield.fields import UnixTimeStampField
from django.http import HttpResponse
import json
from kwue.helper_functions.time_helpers import *
from django.views.decorators.csrf import csrf_exempt


def get_consumption_page(req):
    return render(req, 'kwue/consumption_history.html', {})

def get_start_timestamp_date(timestamp, setting):
    return {
        'daily': timestamp - 86400,  # 24*60*60
        'weekly': timestamp - 604800,  # 7*24*60*60
        'monthly': timestamp - 2592000,  # 30*24*60*60
        'alltime': 0
    }.get(setting, 0)


def get_consumption_history(req):
    date = UnixTimeStampField()
    end_timestamp_date = date.get_timestampnow()
    setting = req.GET.dict()['setting']
    start_timestamp_date = get_start_timestamp_date(end_timestamp_date, setting)

    user_id = req.GET.dict()['user_id']
    results = db_search_consumption_records(start_timestamp_date, end_timestamp_date, user_id)

    foods = []
    nutritional_values_dict = {
        'protein_value': 0,
        'fat_value': 0,
        'carbohydrate_value': 0,
        'fiber_value': 0,
        'calorie_value': 0,
        'sugar_value': 0,
        'serving_weight_grams': 0,
        'vitamin_A': 0,
        'vitamin_C': 0,
        'vitamin_D': 0,
        'vitamin_E': 0,
        'vitamin_K': 0,
        'thiamin': 0,
        'riboflavin': 0,
        'niacin': 0,
        'vitamin_B6': 0,
        'folatem': 0,
        'vitamin_B12': 0,
        'pantothenic_acid': 0,
        'choline': 0,
        'calcium': 0,
        'copper': 0,
        'flouride': 0,
        'iron_Fe': 0,
        'magnesium': 0,
        'manganese': 0,
        'sodium_Na': 0,
        'phosphorus': 0,
        'selenium': 0,
        'zinc': 0
    }
    for result in results:
        food = result['food']  # food object
        date = result['date']  # timestamp

        food_dict = {
            'food_id': food.food_id,
            'food_name': food.food_name,
            'food_image': food.food_image,
            'time_added': show_date(date)
        }
        foods.append(food_dict)

        nutritional_values_dict['protein_value'] += "%.2f" % food.protein_value
        nutritional_values_dict['fat_value'] += "%.2f" % food.fat_value
        nutritional_values_dict['carbohydrate_value'] += "%.2f" % food.carbohydrate_value
        nutritional_values_dict['fiber_value'] += "%.2f" % food.fiber_value
        nutritional_values_dict['calorie_value'] += "%.2f" % food.calorie_value
        nutritional_values_dict['sugar_value'] += "%.2f" % food.sugar_value
        nutritional_values_dict['serving_weight_grams'] += "%.2f" % food.serving_weight_grams
        nutritional_values_dict['vitamin_A'] += "%.2f" % food.vitamin_A
        nutritional_values_dict['vitamin_C'] += "%.2f" % food.vitamin_C
        nutritional_values_dict['vitamin_D'] += "%.2f" % food.vitamin_D
        nutritional_values_dict['vitamin_E'] += "%.2f" % food.vitamin_E
        nutritional_values_dict['vitamin_K'] += "%.2f" % food.vitamin_K
        nutritional_values_dict['thiamin'] += "%.2f" % food.thiamin
        nutritional_values_dict['riboflavin'] += "%.2f" % food.riboflavin
        nutritional_values_dict['niacin'] += "%.2f" % food.niacin
        nutritional_values_dict['vitamin_B6'] += "%.2f" % food.vitamin_B6
        nutritional_values_dict['folatem'] += "%.2f" % food.folatem
        nutritional_values_dict['vitamin_B12'] += "%.2f" % food.vitamin_B12
        nutritional_values_dict['pantothenic_acid'] += "%.2f" % food.pantothenic_acid
        nutritional_values_dict['choline'] += "%.2f" % food.choline
        nutritional_values_dict['calcium'] += "%.2f" % food.calcium
        nutritional_values_dict['copper'] += "%.2f" % food.copper
        nutritional_values_dict['flouride'] += "%.2f" % food.flouride
        nutritional_values_dict['iron_Fe'] += "%.2f" % food.iron_Fe
        nutritional_values_dict['magnesium'] += "%.2f" % food.magnesium
        nutritional_values_dict['manganese'] += "%.2f" % food.manganese
        nutritional_values_dict['sodium_Na'] += "%.2f" % food.sodium_Na
        nutritional_values_dict['phosphorus'] += "%.2f" % food.phosphorus
        nutritional_values_dict['selenium'] += "%.2f" % food.selenium
        nutritional_values_dict['zinc'] += "%.2f" % food.zinc

    results_dict = {
        'foods': foods,
        'nutritional_values_dict': nutritional_values_dict
    }
    print(results_dict)
    return HttpResponse(json.dumps(results_dict), content_type='application/json')


@csrf_exempt
def mark_as_eaten(req):
    user_id = req.POST.dict()['user_id']
    food_id = req.POST.dict()['food_id']
    is_success = False
    reason = ""
    if db_insert_consumption_record(user_id, food_id):
        is_success = True
    else:
        reason = "Couldn't eat the food."
    return HttpResponse(json.dumps({"is_success": is_success, "reason": reason}))