from kwue.models.models import ConsumptionHistory,FoodModel
from kwue.DB_functions.food_db_functions import db_retrieve_food
from kwue.DB_functions.user_db_function import db_retrieve_user


def db_insert_consumption_record(user_id,food_id):
    try:
        user = db_retrieve_user(user_id)
        food = db_retrieve_food(food_id)
        record = ConsumptionHistory(
            food=food,
            user=user
        )
        record.save()
        return record
    except:
        return False


def db_search_consumption_records(start_timestamp_date,end_timestamp_date,user_id):
    try:
        user = db_retrieve_user(user_id)
        records = list(ConsumptionHistory.objects.filter(date__range=(start_timestamp_date,end_timestamp_date), user=user))
        history = []
        for record in records:
            history.append(dict(food=record.food_name, date=record.date))
        return history
    except:
        return False
