from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *
from kwue.DB_functions.tag_db_functions import *
from kwue.helper_functions.nutrition_helpers import request_nutrition

def get_food(req, food_id):
    print(db_retrieve_food(food_id).__dict__)
    return render(req, 'kwue/food.html', {})

def add_food(req):
    food_dict = req.GET.dict
    # request variables for adding a food should include these fields:
    # 'food_description'
    # 'food_name'
    # 'food_image'
    # 'food_owner'
    # 'food_recipe'
    # 'food_tags'

    raw_recipe = food_dict['food_recipe']
    nutrition_dict = request_nutrition(raw_recipe)
    if nutrition_dict is not None:
        if db_insert_food(food_dict, nutrition_dict):
            return True
        else:
            print('Adding food failed.')
            return False
    else:
        print('Nutritional value calculation failed..')
        return False

def remove_food(req,food_id):
    db_delete_food(food_id)
    return render(req, 'kwue/home.html', {})

def rate_food(req):
    return render(req, 'kwue/food.html', {})

def comment_food(req):
    return render(req, 'kwue/food.html', {})

def mark_as_eaten(req):
    return render(req, 'kwue/food.html', {})

def update_food(req,food_id):
    return render(req, 'kwue/food.html', {})