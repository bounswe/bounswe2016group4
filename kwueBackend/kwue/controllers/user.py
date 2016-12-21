from kwue.DB_functions.tag_db_functions import *
from django.views.decorators.csrf import csrf_exempt
from kwue.controllers.home import *


def get_user(req):

    user_id = req.GET.dict()['user_id']
    user = db_retrieve_user(user_id)

    user_dict = ingredient_from_object_to_list(user)

    del user_dict['_state'] # alptekin fix FacePalm
    tag_list = return_tags(user_id, "User")
    user_dict['tag_list'] = tag_list
    user_dict['foods'] = db_get_user_foods(user_id)
    return HttpResponse(json.dumps(user_dict), content_type='application/json')


def get_user_profile_page(req):

    if req.session['user_id'] != -2:
        user_id = req.session['user_id']
    else:
        user_id = req.GET.dict()['user_id']
    user = db_retrieve_user(user_id)

    user_dict = ingredient_from_object_to_list(user)

    del user_dict['_state']  # alptekin fix FacePalm
    tag_list = return_tags(user_id, "User")
    user_dict['tag_list'] = tag_list

    return render(req, 'kwue/user_profile_page.html', user_dict)


def update_diet_page(req):
    user_name = db_retrieve_user(req.session['user_id']).user_name
    return render(req, 'kwue/update_diet.html', {'user_name': user_name})


def get_eating_preferences(req):
    user_id = req.session['user_id']
    ep = db_retrieve_eating_preferences(user_id)

    return HttpResponse(json.dumps(ep), content_type='application/json')


@csrf_exempt
def update_eating_preferences(req):
    ep = req.POST.dict()
    user_id = req.session['user_id']

    db_update_user_preferences(user_id, ep)
    user = db_retrieve_user(user_id)
    user.unwanted_ingredients.clear()
    user.wanted_ingredients.clear()
    db_insert_user_unwanted_ing(user_id, json.loads(ep['unwanted_list']))
    db_insert_user_wanted_ing(user_id, json.loads(ep['wanted_list']))
    return HttpResponse(json.dumps({'is_success': True}), content_type='application/json')

@csrf_exempt
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
    return render(req, 'kwue/login.html', {'user_name': 'Guest'})


def signup_page(req):
    return render(req, 'kwue/signup.html', {'user_name': 'Guest'})




@csrf_exempt
def login(req):
    # DB den user ın var olup olmadığına dair bilgi gelsin
    # DB bana userın id sini versin ve o id ile session başlasın

    user_dict = req.POST.dict()
    user_email_address = user_dict['user_email_address']
    user_password = user_dict["user_password"]

    user = db_validate_user(user_email_address, user_password)

    if user:
        print("User"+user_email_address+ "exists")
        req.session['user_id']= user.user_id
        user_type = user.user_type
        user_id = user.user_id
        user_name = user.user_name
        user_image = user.user_image
        if user_type is False:
            return render(req, 'kwue/home.html', {'recommendations': suggest(user_id), 'user_type': 0, 'user_name': user_name, 'user_id': user_id})
        else:
            return render(req, 'kwue/home.html', {'analysis_report': analyze(user_id), 'user_type': 1, 'user_name': user_name, 'user_image': user_image})
    else:
        print("User does not exists.")
        return render(req, 'kwue/login.html', {'user_name': 'Guest'})


def get_login(req):
    return render(req, 'kwue/login.html', {'user_name': 'Guest'})


def logout(req):
    foods = db_retrieve_all_foods()
    req.session['user_id'] = -2
    return render(req, 'kwue/home.html', {'recommendations': foods, 'user_type': 0, 'user_name': 'Guest'})


@csrf_exempt
def android_login(req):
    user_dict = req.GET.dict()
    user_email_address = user_dict['user_email_address']
    user_password = user_dict["user_password"]

    user = db_validate_user(user_email_address, user_password)

    if user:
        req.session['user_id'] = user.user_id
        return HttpResponse(json.dumps({'user_id': user.user_id}), content_type='application/json')
    else:
        req.session['user_id'] = -2
        return HttpResponse(json.dumps({'user_id': -2}), content_type='application/json')


def android_logout(req):
    req.session['user_id'] = -2
    return HttpResponse(json.dumps({'is_success': True}), content_type='application/json')

