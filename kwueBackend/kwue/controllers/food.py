from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *
from kwue.helper_functions.nutrition_helpers import request_nutrition
import json
from django.http import HttpResponse
from kwue.controllers.tag import tag_food


def get_food(req):
    food_id = req.GET.dict['food_id']
    food_dict = db_retrieve_food(food_id).__dict__
    del food_dict['_state'] # alptekin fix FacePalm
    food_json = json.dumps(food_dict)
    return render(req, 'kwue/food.html', food_json)


def add_food(req):
    food_dict = req.POST.dict()

    # get nutrition values from api
    ingredients = food_dict['ingredients']
    food_recipe = ""
    ingredient_list = []
    for ingredient in ingredients:
        food_recipe += ingredient[0] + " " + ingredient[1] + "\n"
        ingredient_list.append(ingredient[0])
    nutrition_dict = request_nutrition(food_recipe)

    is_success = False
    reason = ""
    if nutrition_dict is not None:
        # insert food
        new_food_id = db_insert_food(food_dict, nutrition_dict, ingredient_list)

        if new_food_id:
            # add tags
            tag_dict = {}
            tag_dict['generic_id'] = new_food_id
            tag_dict["Type"] = "Food"
            tag_list = food_dict['food_tags']
            for tag_item in tag_list:
                tag_dict['tag_label'] = tag_item['tag_name']
                tag_dict['semantic_tag_item'] = tag_item['item']
                tag_dict['semantic_tag_item_label'] = tag_item['itemLabel']
                tag_dict['semantic_tag_description'] = tag_item['itemDescription']
                tag_food(tag_dict)

            print(req.session['username'] + " has added a food successfully.")
            is_success = True
        else:
            reason = 'Adding food failed.'
    else:
        reason = 'Nutritional value calculation failed.'
    return HttpResponse(json.dumps({'is_success': is_success, 'reason': reason}), content_type='application/json')


def get_add_food_page(req):
    return render(req, 'kwue/add_food.html', {})


def get_nutritional_values(req):
    ingredients = req.GET.dict['ingredients']
    food_recipe = ""
    for ingredient in ingredients:
        food_recipe += ingredient[0] + " " + ingredient[1] + "\n"
    nutrition_dict = request_nutrition(food_recipe)
    return HttpResponse(json.dumps(nutrition_dict))


def remove_food(req):
    food_id = req.GET.dict['food_id']
    is_success = False
    reason = ""
    if db_delete_food(food_id):
        is_success = True
    else:
        reason = 'Removing food failed.'
    return HttpResponse(json.dumps({'is_success': is_success, 'reason': reason}), content_type='application/json')

def rate_food(req):
    return render(req, 'kwue/food.html', {})

def comment_food(req):
    return render(req, 'kwue/food.html', {})

def update_food(req):
    return render(req, 'kwue/food.html', {})