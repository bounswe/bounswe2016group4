from kwue.models.models import *
from kwue.DB_functions.food_db_functions import db_retrieve_food


def db_insert_tag(tag_dict):
    food_id = tag_dict['food_id']
    food = db_retrieve_food(food_id)

    new_tag = TagModel(
        tag_label=tag_dict['tag_label'],
        semantic_tag_item=tag_dict['semantic_tag_item'],
        semantic_tag_item_label=tag_dict['semantic_tag_item_label'],
        semantic_tag_item_description=tag_dict['semantic_tag_description'],
        tagged_food=food
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

