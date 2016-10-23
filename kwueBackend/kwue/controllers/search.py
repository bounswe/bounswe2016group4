from django.shortcuts import render

def get_search_info(req):
    return render(req, 'kwue/food.html', {})

def search(req):
    return render(req, 'kwue/food.html', {})

def adv_search(req):
    return render(req, 'kwue/food.html', {})

