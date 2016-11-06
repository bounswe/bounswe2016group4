from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *

def get_user(req):
    return render(req, 'kwue/food.html', {})

#dummy function for log in
def login_user(req,user):
    req.session['has_access'] = True
    req.session['id'] = user.user_id
    req.session['username'] = user.user_name
    return render(req, 'kwue/food.html', {})

def update_profile(req):
    return render(req, 'kwue/food.html', {})

def get_consumption_history(req):
    return render(req, 'kwue/food.html', {})
