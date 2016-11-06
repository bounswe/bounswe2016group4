from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *

def get_home(req):
    food = db_retrieve_all_foods()
    #req.session['has_access']=True
    #req.session['id']=11
    #req.session['username'] = 'Doruk'
    return render(req, 'kwue/home.html', {'foods': food})