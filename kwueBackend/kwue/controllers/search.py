from django.shortcuts import render
from kwue.DB_functions.search_db_function import *
from kwue.DB_functions.user_db_function import db_retrieve_eating_preferences
import json


def basic_search(req):
    # do not know how to get userId from session yet, to be resolved
    user_id = req.GET.dict()['userId']
    ep = db_retrieve_eating_preferences(user_id)

    search_results = search_alg(req.GET.dict(), ep)

    return render(req, 'kwue/search.html', json.dumps(search_results))


def advanced_search(req):
    # ignore my eating preferences will be handled in frontend

    dict = req.GET.dict()
    ep = dict
    ep['wanted_list'] = json.loads(ep['wanted_list'])
    ep['unwanted_list'] = json.loads(ep['unwanted_list'])

    search_results = search_alg(dict, ep)

    return render(req, 'kwue/food.html', json.dumps(search_results))


def search_alg(dict, ep):
    text = dict['search_text']
    results = search_by_text(text)
    food_set = results['food_set']
    food_server_set = results['food_server_set']
    semantic_foods = results['semantic_food_set']
    semantic_users = results['semantic_user_set']

    filtered_food_set = search_by_parameters(ep, food_set)
    filtered_semantic_foods = search_by_parameters(ep, semantic_foods)

    filtered_food_set_result = filtered_food_set.distinct().values("food_name", "food_id", "food_image", "calorie_value")
    filtered_semantic_foods_result = filtered_semantic_foods.distinct().values("food_name", "food_id", "food_image", "calorie_value")
    food_server_set_result = food_server_set.distinct().values("user_id", "user_name", "user_image")
    semantic_users_result = semantic_users.distinct().values("user_id", "user_name", "user_image")

    search_results = {'food_set': filtered_food_set_result,
                      'user_set': food_server_set_result,
                      'semantic_food_set': filtered_semantic_foods_result,
                      'semantic_user_set': semantic_users_result}


    return search_results


def search_by_parameters(ep, foods):
    foods = unwanted_search(ep['unwanted_list'], foods)
    foods = wanted_search(ep['wanted_list'], foods)
    foods = protein_search(ep['protein_lower_bound'], ep['protein_upper_bound'], foods)
    foods = fat_search(ep['fat_lower_bound'], ep['fat_upper_bound'], foods)
    foods = carbohydrate_search(ep['carbohydrate_lower_bound'], ep['carbohydrate_upper_bound'], foods)
    foods = calorie_search(ep['calorie_lower_bound'], ep['calorie_upper_bound'], foods)
    foods = sugar_search(ep['sugar_lower_bound'], ep['sugar_upper_bound'], foods)
    return foods
