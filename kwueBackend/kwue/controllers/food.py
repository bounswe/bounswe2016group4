from django.shortcuts import render


def add_food(req):
    return render(req, 'kwue/food.html', {})

