from django.http import HttpResponse
import json
from kwue.DB_functions.tag_db_functions import *
from kwue.helper_functions.semantic_tag_helpers import get_semantic_tags

def search_semantic_tags(req):
    semantic_tags = get_semantic_tags(req.GET.dict()['tag_name'])
    return HttpResponse(json.dumps(semantic_tags), content_type='application/json')

def tag_food(req):
    dict = req.POST.dict()
    dict['generic_id'] = dict['tagged_food_id']
    dict["type"] = "Food"
    db_insert_tag(dict)

def tag_user(req):
    dict = req.POST.dict()
    dict['generic_id'] = dict['tagged_user_id']
    dict["type"] = "User"
    db_insert_tag(dict)
