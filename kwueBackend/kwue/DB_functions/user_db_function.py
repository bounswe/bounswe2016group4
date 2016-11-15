from kwue.models.models import *


def db_retrieve_user(user_id):
    user = UserModel.objects.get(user_id=user_id)
    return user


def db_retrieve_eating_preferences(user_id):
    user = UserModel.objects.get(user_id=user_id)
    allergic_list = list(user.allergic_ingredients.all)
    tag_list = list(user.preference_tags)
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
        allergic_list=allergic_list,
        tag_list=tag_list
    )
