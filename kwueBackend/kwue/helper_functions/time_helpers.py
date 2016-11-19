from datetime import datetime
import time


def timestamp_to_datetime(timestamp):
    dt_obj = datetime.fromtimestamp(timestamp)
    return dt_obj


def date_beatify(dt_obj):
    date_str = dt_obj.strftime("%Y-%m-%d %H:%M:%S")
    return date_str


def show_date(timestamp):
    return date_beatify(timestamp_to_datetime(timestamp))