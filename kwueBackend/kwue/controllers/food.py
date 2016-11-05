from django.shortcuts import render

def get_food(req):
    return render(req, 'kwue/food.html', {})

def add_food(req):
    def calculate_nutritional_values():
        pass

    return render(req, 'kwue/food.html', {})

def remove_food(req):
    return render(req, 'kwue/food.html', {})

def rate_food(req):
    return render(req, 'kwue/food.html', {})

def comment_food(req):
    return render(req, 'kwue/food.html', {})

def mark_as_eaten(req):
    return render(req, 'kwue/food.html', {})