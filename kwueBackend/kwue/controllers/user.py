from django.shortcuts import render
from kwue.DB_functions.user_db_function import *
from django.http import HttpResponse
import json

def get_user(req):
    user_id = req.GET.dict['user_id']
    user_dict = db_retrieve_user(user_id).__dict__
    del user_dict['_state'] # alptekin fix FacePalm
    return HttpResponse(json.dumps(user_dict), content_type='application/json')

def get_user_profile_page(req):
    user_id = req.GET.dict['user_id']
    user_dict = db_retrieve_user(user_id).__dict__
    del user_dict['_state']  # alptekin fix FacePalm
    return render(req, 'kwue/user_profile_page.html', json.dumps(user_dict))

def update_profile(req):
    return render(req, 'kwue/food.html', {})

def get_consumption_history(req):
    # 1 - From the tabs "last one day" "last one week" "last one month" "all time" the last one day is default.
    # 2 - Get the foods eaten last one day with the taken nutrition values from db.
    return render(req, 'kwue/food.html', {})

def get_eating_preferences(req):
    user_id = req.GET.dict['user_id']
    ep = db_retrieve_eating_preferences(user_id)
    return HttpResponse(json.dumps(ep), content_type='application/json')

def update_eating_preferences(req):
    dict = req.POST.dict()
    user_id = dict['user_id']
    db_update_user_preferences(user_id, dict)
    db_insert_user_unwanted_ing(user_id, dict['unwanted_list'])
    db_insert_user_wanted_ing(user_id, dict['wanted_list'])

def sign_up(req):
    #DB ye user kaydedilsin

    new_user_dict = req.POST.dict()

    #Check that the email adress has not been used before !!!!!!!!!!
    user_email_address = new_user_dict['user_email_address']

    user_information_dict = dict(
        user_name=new_user_dict["user_name"],
        user_nick=new_user_dict["user_nick"],
        user_email_address=new_user_dict["user_email_address"],
        user_password=new_user_dict["user_password"],
        user_image=new_user_dict["user_image"],
        user_type=new_user_dict["user_type"]
    )
    db_insert_user(user_information_dict)
    return render(req, 'kwue/food.html', {})

def login(req):
    # DB den user ın var olup olmadığına dair bilgi gelsin
    # DB bana userın id sini versin ve o id ile session başlasın

    user_dict = req.POST.dict()
    user_email_address = user_dict['user_email_address']
    user_password = user_dict["user_password"]

    if db_validate_user(user_email_address, user_password):
        print("User"+user_email_address+ "exists")
        req.session['email_adress']= user_email_address #SESSION STARTS
    else:
        print("User does not exists.")

    return render(req, 'kwue/food.html', {})