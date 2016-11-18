from django.shortcuts import render
from kwue.DB_functions.search_db_function import *
from kwue.DB_functions.user_db_function import db_retrieve_eating_preferences
import json

def basic_search(req):
    # 1 - get user eating preferences from db with session
    # 2 - get foods and food servers with same text (or the text is in the food/server) considering eating preferences)

    # do not know how to get userId from session yet, to be resolved
    user_id = req.GET.dict['userId']
    ep = db_retrieve_eating_preferences(user_id)

    text = req.GET.dict['search_text']
    results = searrch_by_text(text)
    food_set = results['food_set']
    food_server_set = results['food_server_set']
    semantic_foods = results['semantic_foods']
    semantic_users = results['semantic_users']

    filtered_food_set = search_by_parameters(ep, food_set)
    filtered_semantic_foods = search_by_parameters(ep, semantic_foods)

    search_results = {'food_set': filtered_food_set,
                      'food_server_set': food_server_set,
                      'semantic_foods': filtered_semantic_foods,
                      'semantic_users': semantic_users}

    return render(req, 'kwue/search.html', json.dumps(search_results))

def advanced_search(req):
    # 1 - check if the user checkboxed the "ignore my eating preferences"
    # 2 - get user eating preferences from db with session
    # 3 - get all filters
    # 4 - get foods and food servers with the same text (or the text is in the food/server) considering filters)
    return render(req, 'kwue/food.html', {})

def search_by_parameters(ep, foods):
    foods = unwanted_search(ep['unwanted_list'], foods)
    foods = wanted_search(ep['wanted_list'], foods)
    foods = protein_search(ep['protein_lower_bound'], ep['protein_upper_bound'], foods)
    foods = fat_search(ep['fat_lower_bound'], ep['fat_upper_bound'], foods)
    foods = carbohydrate_search(ep['carbohydrate_lower_bound'], ep['carbohydrate_upper_bound'], foods)
    foods = calorie_search(ep['calorie_lower_bound'], ep['calorie_upper_bound'], foods)
    return foods
