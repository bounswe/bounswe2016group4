from kwue.models.models import *
from kwue.DB_functions.user_db_function import db_retrieve_user
from kwue.DB_functions.ingredient_db_functions import db_insert_ingredient,db_retrieve_ingredient


def db_insert_food(food_dict, nutrition_dict, ingredient_list):
    food_owner_id = food_dict['food_owner']
    food_owner = db_retrieve_user(food_owner_id)
    ingredient_object_list = []
    print(ingredient_list)
    for ingredient in ingredient_list:
        ing_object = db_retrieve_ingredient(ingredient)
        if ing_object == False:
            print(ingredient, 'DOES NOT Exists')
            new_object = db_insert_ingredient(ingredient)
            if new_object is not False:
                ingredient_object_list.append(new_object)
        else:
            ingredient_object_list.append(ing_object)
            print(ingredient, 'Exists')
    print(ingredient_object_list)
    new_food = FoodModel(
        food_description=food_dict['food_description'],
        food_name=food_dict['food_name'],
        food_image=food_dict['food_image'],
        food_owner=food_owner,
        food_recipe=food_dict['food_recipe'],
        protein_value=nutrition_dict['protein_value'],
        fat_value=nutrition_dict['fat_value'],
        carbohydrate_value=nutrition_dict['carbohydrate_value'],
        fiber_value=nutrition_dict['fiber_value'],
        calorie_value=nutrition_dict['calorie_value'],
        sugar_value=nutrition_dict['sugar_value'],
        serving_weight_grams=nutrition_dict['serving_weight_grams'],
        vitamin_A=nutrition_dict['vitamin_A'],
        vitamin_C=nutrition_dict['vitamin_C'],
        vitamin_D=nutrition_dict['vitamin_D'],
        vitamin_E=nutrition_dict['vitamin_E'],
        vitamin_K=nutrition_dict['vitamin_K'],
        thiamin=nutrition_dict['thiamin'],
        riboflavin=nutrition_dict['riboflavin'],
        niacin=nutrition_dict['niacin'],
        vitamin_B6=nutrition_dict['vitamin_B6'],
        folatem=nutrition_dict['folatem'],
        vitamin_B12=nutrition_dict['vitamin_B12'],
        pantothenic_acid=nutrition_dict['pantothenic_acid'],
        choline=nutrition_dict['choline'],
        calcium=nutrition_dict['calcium'],
        copper=nutrition_dict['copper'],
        flouride=nutrition_dict['flouride'],
        iron_Fe=nutrition_dict['iron_Fe'],
        magnesium=nutrition_dict['magnesium'],
        manganese=nutrition_dict['manganese'],
        sodium_Na=nutrition_dict['sodium_Na'],
        phosphorus=nutrition_dict['phosphorus'],
        selenium=nutrition_dict['selenium'],
        zinc=nutrition_dict['zinc']
    )
    new_food.save()
    try:
        for ing in ingredient_object_list:
            new_food.ingredient_list.add(ing)
            print(ing, ' IS ADDED')
        return new_food.food_id
    except:
        print('cannot handle saving food')
        return False


def db_retrieve_food(food_id):
    food = FoodModel.objects.get(food_id=food_id)
    return food


## to be deleted later if unused
def db_retrieve_all_foods():
    foods = FoodModel.objects.all()
    return foods


def db_delete_food(food_id):
    food = FoodModel.objects.filter(food_id=food_id)
    try:
        food.delete()
        return True
    except:
        return False


def db_update_food(food_id, food_tuple):
    try:
        FoodModel.objects.filter(food_id=food_id).update(
            food_description=food_tuple[0],
            food_name=food_tuple[1],
            food_image=food_tuple[2],
            food_owner=food_tuple[3]
        )
        return False
    except:
        return True


def db_rate_food(food_id,rate_value):
    try:
        food = FoodModel.objects.get(food_id=food_id)
        rate = food.food_rate
        count = food.food_rate_count
        total = rate * count + rate_value
        count += 1
        rate = total / count
        food.update(food_rate=rate, food_rate_count=count)
        return rate
    except:
        return False


def db_add_consumption_history(user_id, food_id, rate=0):
    try:
        food = db_retrieve_food(food_id)
        user = db_retrieve_user(user_id)
        new_history = ConsumptionHistory(user=user, food=food, food_rate=rate)
        new_history.save()
        return True
    except:
        return False
