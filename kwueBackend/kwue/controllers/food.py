from django.shortcuts import render
from django.http import HttpResponse

from kwue.DB_functions.food_db_functions import *

def get_food(req, food_id):
    print(db_retrieve_food(food_id).__dict__)

    return render(req, 'kwue/food.html', {})

def add_food(req):
    # def calculate_nutritional_values():

    return render(req, 'kwue/home.html', {})

def remove_food(req):
    return render(req, 'kwue/food.html', {})

def rate_food(req):
    return render(req, 'kwue/home.html', {})

def comment_food(req):
    return render(req, 'kwue/food.html', {})

def mark_as_eaten(req):
    return render(req, 'kwue/food.html', {})