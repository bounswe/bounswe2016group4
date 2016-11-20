from kwue.models.models import *
from kwue.DB_functions.food_db_functions import db_retrieve_food
from kwue.DB_functions.user_db_function import db_retrieve_user


def db_insert_tag(tag_dict):
    generic_id = tag_dict['generic_id']
    if tag_dict["type"] == "Food":
        generic_object = db_retrieve_food(generic_id)
    else:
        generic_object = db_retrieve_user(generic_id)
    new_tag = TagModel(
        tag_label=tag_dict['tag_name'],
        semantic_tag_item=tag_dict['tag_id'],
        semantic_tag_item_label=tag_dict['tag_label'],
        semantic_tag_item_description=tag_dict['tag_description'],
        tagged_food=generic_object
    )
    try:
        new_tag.save()
        return True
    except:
        return False


def db_retrieve_tag(tag_id):
    tag = TagModel.objects.get(food_id=tag_id)
    return tag


def db_delete_food(tag_id):
    tag = TagModel.objects.filter(tag_id=tag_id)
    try:
        tag.delete()
        return True
    except:
        return False

