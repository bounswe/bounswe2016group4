import json
from kwue.DB_functions.ingredient_db_functions import *

# def ingredients_from_list_to_dict(ep):
#     wanted_list = ep['wanted_list']
#     unwanted_list = ep['unwanted_list']
#     wanted_list_dict = []
#     unwanted_list_dict = []
#     for wanted_item in wanted_list:
#         wanted_list_dict.append({'name': wanted_item})
#     for unwanted_item in unwanted_list:
#         unwanted_list_dict.append({'name': unwanted_item})
#     ep['wanted_list'] = wanted_list_dict
#     ep['unwanted_list'] = unwanted_list_dict
#     return ep
#
# def ingredients_from_dict_to_list(ep):
#     wanted_list_dict = json.loads(ep['wanted_list'])
#     unwanted_list_dict = json.loads(ep['unwanted_list'])
#     wanted_list = []
#     unwanted_list = []
#     for wanted_item in wanted_list_dict:
#         wanted_list.append(wanted_item['name'])
#     for unwanted_item in unwanted_list_dict:
#         unwanted_list.append(unwanted_item['name'])
#     ep['wanted_list'] = wanted_list
#     ep['unwanted_list'] = unwanted_list
#     return ep


def ingredient_list_to_ingredient_object(list):
    obj_list = []
   # list = json.loads(list)
    for ing in list:
        result = db_retrieve_ingredient(ing)
        if result is not False:
            obj_list.append(result)
    return obj_list

def ingredient_from_object_to_list(list):
    wanted_ing_list = []
    unwanted_ing_list = []
    for ing in list.wanted_ingredients.values_list('ingredient_name'):
        wanted_ing_list.append(ing[0])
    for ing in list.unwanted_ingredients.values_list('ingredient_name'):
        unwanted_ing_list.append(ing[0])
    dict = list.__dict__
    dict.update(wanted_list=wanted_ing_list, unwanted_list=unwanted_ing_list)
    return dict