from django.shortcuts import render
from kwue.DB_functions.consumption_history_db_functions import *
from unixtimestampfield.fields import UnixTimeStampField
from django.http import HttpResponse
import json

def get_start_timestamp_date(timestamp, setting):
    return {
        'daily': timestamp - 86400,  # 24*60*60
        'weekly': timestamp - 604800,  # 7*24*60*60
        'monthly': timestamp - 2592000,  # 30*24*60*60
        'alltime': 0
    }.get(setting, 0)

def get_consumption_history(req):
    date = UnixTimeStampField()
    end_timestamp_date = date.get_timestampnow()
    setting = req.GET.dict['setting']
    start_timestamp_date = get_start_timestamp_date(end_timestamp_date, setting)

    user_id = req.GET.dict['user_id']
    results = db_search_consumption_records(start_timestamp_date, end_timestamp_date, user_id)

    return HttpResponse(json.dumps(results), content_type='application/json')

def mark_as_eaten(req):
    user_id = req.GET.dict['user_id']
    food_id = req.GET.dict['food_id']
    db_insert_consumption_record(user_id, food_id)