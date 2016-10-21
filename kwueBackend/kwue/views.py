from django.shortcuts import render

def food(req):
    return render(req, 'kwue/food.html', {})