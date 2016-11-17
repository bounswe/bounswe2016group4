from kwue.models.models import *
from kwue.DB_functions.ingredient_db_functions import db_retrieve_ingredient,db_insert_ingredient

def db_retrieve_user(user_id):
    user = UserModel.objects.get(user_id=user_id)
    return user


def db_retrieve_eating_preferences(user_id):
    try:
        user = UserModel.objects.get(user_id=user_id)
        wanted_list = list(user.wanted_ingredients.all())
        unwanted_list = list(user.unwanted_ingredients.all())
        eating_preferences_dict = dict(
            protein_lower_bound=user.protein_lower_bound,
            fat_lower_bound=user.fat_lower_bound,
            carbohydrate_lower_bound=user.carbohydrate_lower_bound,
            calorie_lower_bound=user.calorie_lower_bound,
            sugar_lower_bound=user.sugar_lower_bound,
            protein_upper_bound=user.protein_upper_bound,
            fat_upper_bound=user.fat_upper_bound,
            carbohydrate_upper_bound=user.carbohydrate_upper_bound,
            calorie_upper_bound=user.calorie_upper_bound,
            sugar_upper_bound=user.sugar_upper_bound,
            wanted_list=wanted_list,
            unwanted_list=unwanted_list,
        )
        return eating_preferences_dict
    except:
        return False


def db_validate_user(user_email, user_password):
    try:
        user = UserModel.objects.get(user_email_address=user_email, user_password=user_password)
        return user
    except:
        return False


def db_insert_user(user_dict):
    new_user = UserModel(
        user_name=user_dict["user_name"],
        user_nick=user_dict["user_nick"],
        user_email_address=user_dict["user_email_address"],
        user_password=user_dict["user_password"],
        user_image=user_dict["user_image"],
        user_type=user_dict["user_type"]
    )
    try:
        new_user.save()
    except:
        return False
    return new_user


def db_update_user_preferences(user_id, user_dict):
    user = UserModel.objects.filter(user_id=user_id)
    try:
        user.update(
            user_nick=user_dict["user_nick"],
            user_password=user_dict["user_password"],
            user_image=models.URLField(default=''),
            fat_lower_bound=user_dict["fat_lower_bound"],
            protein_lower_bound=user_dict["protein_lower_bound"],
            carbohydrate_lower_bound=user_dict[""],
            calorie_lower_bound=user_dict["calorie_lower_bound"],
            sugar_lower_bound=user_dict["sugar_lower_bound"],
            protein_upper_bound=user_dict["protein_upper_bound"],
            fat_upper_bound=user_dict["fat_upper_bound"],
            carbohydrate_upper_bound=user_dict["carbohydrate_upper_bound"],
            calorie_upper_bound=user_dict["calorie_upper_bound"],
            sugar_upper_bound=user_dict["sugar_upper_bound"]
        )
        return user
    except:
        return False


def db_insert_user_unwanted_ing(user_id,ing_name_list):
    user = db_retrieve_user(user_id)
    for ing_name in ing_name_list:
        try:
            ing = db_retrieve_ingredient(ing_name)
            user.unwanted_ingredients.add(ing)
            return True
        except:
            try:
                new_ing = db_insert_ingredient(ing_name)
                user.unwanted_ingredients.add(new_ing)
            except:
                return False


def db_insert_user_wanted_ing(user_id,ing_name_list):
    user = db_retrieve_user(user_id)
    for ing_name in ing_name_list:
        try:
            ing = db_retrieve_ingredient(ing_name)
            user.wanted_ingredients.add(ing)
            return True
        except:
            try:
                new_ing = db_insert_ingredient(ing_name)
                user.wanted_ingredients.add(new_ing)
            except:
                return False
