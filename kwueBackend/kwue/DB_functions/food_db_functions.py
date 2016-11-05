from kwue.models.models import *


def db_insert_food(food_tuple):
    new_food = FoodModel(
        food_description=food_tuple[0],
        food_name=food_tuple[1],
        food_image=food_tuple[2],
        food_owner=food_tuple[3]
    )
    FoodModel.save(new_food)


def db_retrieve_food(id):
    food = FoodModel.objects.get(food_id=id)
    return food
