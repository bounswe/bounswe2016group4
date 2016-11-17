from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *
from kwue.DB_functions.user_db_function import *
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

    new_user_dict = req.POST.dict()

    #Check that the email adress has not been used before !!!!!!!
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