from django.shortcuts import render
from kwue.DB_functions.user_db_function import db_retrieve_eating_preferences
from django.http import HttpResponse

def get_user(req):
    # 1 - Get user from session from db
    return render(req, 'kwue/food.html', {})

def update_profile(req):
    return render(req, 'kwue/food.html', {})

def get_consumption_history(req):
    # 1 - From the tabs "last one day" "last one week" "last one month" "all time" the last one day is default.
    # 2 - Get the foods eaten last one day with the taken nutrition values from db.
    return render(req, 'kwue/food.html', {})

def get_eating_preferences(req):
    user_id = req.GET.dict('user_id')
    ep = db_retrieve_eating_preferences(user_id)
    return HttpResponse(json.dumps(ep), content_type='application/json')
