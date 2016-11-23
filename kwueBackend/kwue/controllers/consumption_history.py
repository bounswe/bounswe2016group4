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

        nutritional_values_dict['protein_value'] += round(food.protein_value, 3)
        nutritional_values_dict['fat_value'] += round(food.fat_value, 3)
        nutritional_values_dict['carbohydrate_value'] += round(food.carbohydrate_value, 3)
        nutritional_values_dict['fiber_value'] += round(food.fiber_value, 3)
        nutritional_values_dict['calorie_value'] += round(food.calorie_value, 3)
        nutritional_values_dict['sugar_value'] += round(food.sugar_value, 3)
        nutritional_values_dict['serving_weight_grams'] += round(food.serving_weight_grams, 3)
        nutritional_values_dict['vitamin_A'] += round(food.vitamin_A, 3)
        nutritional_values_dict['vitamin_C'] += round(food.vitamin_C, 3)
        nutritional_values_dict['vitamin_D'] += round(food.vitamin_D, 3)
        nutritional_values_dict['vitamin_E'] += round(food.vitamin_E, 3)
        nutritional_values_dict['vitamin_K'] += round(food.vitamin_K, 3)
        nutritional_values_dict['thiamin'] += round(food.thiamin, 3)
        nutritional_values_dict['riboflavin'] += round(food.riboflavin, 3)
        nutritional_values_dict['niacin'] += round(food.niacin, 3)
        nutritional_values_dict['vitamin_B6'] += round(food.vitamin_B6, 3)
        nutritional_values_dict['folatem'] += round(food.folatem, 3)
        nutritional_values_dict['vitamin_B12'] += round(food.vitamin_B12, 3)
        nutritional_values_dict['pantothenic_acid'] += round(food.pantothenic_acid, 3)
        nutritional_values_dict['choline'] += round(food.choline, 3)
        nutritional_values_dict['calcium'] += round(food.calcium, 3)
        nutritional_values_dict['copper'] += round(food.copper, 3)
        nutritional_values_dict['flouride'] += round(food.flouride, 3)
        nutritional_values_dict['iron_Fe'] += round(food.iron_Fe, 3)
        nutritional_values_dict['magnesium'] += round(food.magnesium, 3)
        nutritional_values_dict['manganese'] += round(food.manganese, 3)
        nutritional_values_dict['sodium_Na'] += round(food.sodium_Na, 3)
        nutritional_values_dict['phosphorus'] += round(food.phosphorus, 3)
        nutritional_values_dict['selenium'] += round(food.selenium, 3)
        nutritional_values_dict['zinc'] += round(food.zinc, 3)

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