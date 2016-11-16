from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *

def get_user(req):
    # 1 - Get user from session from db
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
    # 1 - From the tabs "last one day" "last one week" "last one month" "all time" the last one day is default.
    # 2 - Get the foods eaten last one day with the taken nutrition values from db.
    return render(req, 'kwue/food.html', {})

def sign_up(req):
    #DB ye user kaydedilsin
    return render(req, 'kwue/food.html', {})

def login(req):
    # DB den user ın var olup olmadığına dair bilgi gelsin
    # DB bana userın id sini versin ve o id ile session başlasın
    return render(req, 'kwue/food.html', {})