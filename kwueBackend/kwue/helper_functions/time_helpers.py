from datetime import datetime


def timestamp_to_datetime(timestamp):
    dt_obj = datetime.fromtimestamp(timestamp)
    return dt_obj


def date_beautify(dt_obj):
    date_str = dt_obj.strftime("%Y-%m-%d %H:%M:%S")
    return date_str


def show_date(timestamp):
    return date_beautify(timestamp_to_datetime(timestamp))