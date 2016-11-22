from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *
from kwue.helper_functions.nutrition_helpers import request_nutrition
import json
from django.http import HttpResponse
from kwue.DB_functions.tag_db_functions import *
from django.views.decorators.csrf import csrf_exempt

def get_food(req):
    food_id = req.GET.dict()['food_id']
    food_dict = db_retrieve_food(food_id).__dict__
    del food_dict['_state']  # alptekin fix FacePalm
    tag_list = return_tags(food_id, "Food")
    food_dict['tag_list'] = tag_list
    food_json = json.dumps(food_dict)
    return render(req, 'kwue/food.html', food_json)


@csrf_exempt
def add_food(req):
    food_dict = req.POST.dict()

    # get nutrition values from api
    ingredients = json.loads(food_dict['ingredients'])

    food_recipe = ""
    ingredient_list = []
    for ingredient in ingredients:
        food_recipe += ingredient["value"] + " " + ingredient["ingredient"] + "\n"
        ingredient_list.append(ingredient["ingredient"])
    nutrition_dict = request_nutrition(food_recipe)
    food_dict['food_recipe'] = food_recipe

    is_success = False
    reason = ""
    if nutrition_dict is not None:
        # insert food
        new_food_id = db_insert_food(food_dict, nutrition_dict, ingredient_list)

        if new_food_id:
            # add tags
            tag_dict = {}
            tag_dict['generic_id'] = new_food_id
            tag_dict["type"] = "Food"
            tag_list = json.loads(food_dict['food_tags'])
            for tag_item in tag_list:
                tag_dict['tag_name'] = tag_item['tag_name']
                tag_dict['tag_id'] = tag_item['tag_id']
                tag_dict['tag_label'] = tag_item['tag_label']
                tag_dict['tag_description'] = tag_item['tag_description']
                db_insert_tag(tag_dict)

            #print(req.session['username'] + " has added a food successfully.")
            is_success = True
        else:
            reason = 'Adding food failed.'
    else:
        reason = 'Nutritional value calculation failed.'
    return HttpResponse(json.dumps({'is_success': is_success, 'reason': reason}), content_type='application/json')


def get_add_food_page(req):
    return render(req, 'kwue/add_food.html', {})


@csrf_exempt
def get_nutritional_values(req):
    ingredients = json.loads(req.POST.dict()['ingredients'])
    food_recipe = ""
    for ingredient in ingredients:
        food_recipe += ingredient["value"] + " " + ingredient["ingredient"] + "\n"
    nutrition_dict = request_nutrition(food_recipe)
    return HttpResponse(json.dumps(nutrition_dict))


# def remove_food(req):
#     food_id = req.GET.dict()['food_id']
#     is_success = False
#     reason = ""
#     if db_delete_food(food_id):
#         is_success = True
#     else:
#         reason = 'Removing food failed.'
#     return HttpResponse(json.dumps({'is_success': is_success, 'reason': reason}), content_type='application/json')

def rate_food(req):
    return render(req, 'kwue/food.html', {})

def comment_food(req):
    return render(req, 'kwue/food.html', {})

def update_food(req):
    return render(req, 'kwue/food.html', {})