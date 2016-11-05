from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *

def get_food(req):
    print(db_retrieve_food(2).__dict__)
    return render(req, 'kwue/food.html', {})

def add_food(req):
    if req.method == 'GET':
        print("get methodu")
        getData = req.GET.dict()
        if bool(getData):
            searchValuesu = getData['search']
            print(searchValuesu)
            return render(req, 'kwue/add_food.html', {'s': searchValuesu})
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




def dummy_home(req):
    food = db_retrieve_all_foods()
    return render(req, 'kwue/home.html', {'foods': food})