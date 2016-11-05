from django.shortcuts import render
api

def get_food(req):
    print("get_food called.")
    return render(req, 'kwue/food.html', {})

def add_food(req):
    # def calculate_nutritional_values():

    return render(req, 'kwue/home.html', {})

def remove_food(req):
    return render(req, 'kwue/food.html', {})

def rate_food(req):
    return render(req, 'kwue/food.html', {})

def comment_food(req):
    return render(req, 'kwue/food.html', {})

def mark_as_eaten(req):
    return render(req, 'kwue/food.html', {})