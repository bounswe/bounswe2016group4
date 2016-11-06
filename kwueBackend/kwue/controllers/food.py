from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *
from kwue.helper_functions.nutrition_helpers import request_nutrition

def get_food(req, food_id):
    print(db_retrieve_food(food_id).__dict__)
    return render(req, 'kwue/food.html', {})


def add_food(req):

#Alperden bir dictionary alcagim varsayiyorum reqin icinde adi food_dict olsun
# entryleri affedersin
#
#         food_description=food_dict['food_description'],
#         food_name=food_dict['food_name'],
#         food_image=food_dict['food_image'],
#         #food_owner=food_dict['food_owner'],
#         food_owner=food_owner,
#         food_recipe=food_dict['food_recipe'],
#### dipnot front endden nasil geliyor variablelar bilmiyorum ben normal dictionary gibi farz etttim

    if req.method == 'POST':
        food_dict = req.POST.dict()
        raw_recipe = food_dict['food_recipe']
        nutrition_dict = request_nutrition(raw_recipe)
        if nutrition_dict is not None:
            if db_insert_food(food_dict, nutrition_dict):
                return render(req, 'kwue/add_food.html', {})
            else:
                print('Errororororo')
                return render(req, 'kwue/food.html', {})
        else:
            print('Errororororo')
            return render(req, 'kwue/food.html', {})
    else:
        return render(req, 'kwue/add_food.html', {})

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