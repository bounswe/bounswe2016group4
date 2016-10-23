from django.shortcuts import render

def get_list(req):
    return render(req, 'kwue/food.html', {})

def add_item(req):
    return render(req, 'kwue/food.html', {})

def create_list(req):
    return render(req, 'kwue/food.html', {})
