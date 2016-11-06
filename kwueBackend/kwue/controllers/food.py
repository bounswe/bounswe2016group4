from django.shortcuts import render
from kwue.DB_functions.food_db_functions import *

def get_food(req, food_id):
    print(db_retrieve_food(food_id).__dict__)
    return render(req, 'kwue/food.html', {})

def add_food(req):

    ## if'li kısmın değişmesi gerekiyor bir işe yaramıyor şu an
    ## isterseniz debug için tutun
    if req.method == 'GET':
        print("get methodu")
        getData = req.GET.dict()
        if bool(getData):
            searchValuesu = getData['search']
            print(searchValuesu)
            return render(req, 'kwue/add_food.html', {'s': searchValuesu})
    elif req.method == 'POST':
        data = req.POST.dict()
        ## food'u database'e ekleme kısmı...
        ##
        ##
        ## ekledikten sonra render edilecek sayfa.
        return render(req, 'kwue/home.html', {})

    return render(req, 'kwue/add_food.html', {})

def remove_food(req,food_id):
    db_delete_food(food_id)
    return render(req, 'kwue/home.html', {})

def rate_food(req):
    return render(req, 'kwue/food.html', {})

def comment_food(req):
    return render(req, 'kwue/food.html', {})

def mark_as_eaten(req):
    return render(req, 'kwue/food.html', {})

def update_food(req,food_id):
    return render(req, 'kwue/food.html', {})