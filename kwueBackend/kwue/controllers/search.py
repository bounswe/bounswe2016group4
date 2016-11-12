from django.shortcuts import render

def get_search_info(req):
    return render(req, 'kwue/food.html', {})

def basic_search(req):
    # 1 - get user eating preferences from db with session
    # 2 - get foods and food servers with same text (or the text is in the food/server) considering eating preferences)
    return render(req, 'kwue/food.html', {})

def advanced_search(req):
    # 1 - check if the user checkboxed the "ignore my eating preferences"
    # 2 - get user eating preferences from db with session
    # 3 - get all filters
    # 4 - get foods and food servers with the same text (or the text is in the food/server) considering filters)
    return render(req, 'kwue/food.html', {})

