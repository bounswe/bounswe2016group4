from django.shortcuts import render

def get_user(req):
    return render(req, 'kwue/food.html', {})

def update_profile(req):
    return render(req, 'kwue/food.html', {})

def get_consumption_history(req):
    return render(req, 'kwue/food.html', {})
