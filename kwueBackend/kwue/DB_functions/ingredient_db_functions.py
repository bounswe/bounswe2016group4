from kwue.models.models import IngredientModel


def db_insert_ingredient(ingredient):
    try:
        new_object = IngredientModel(
            ingredient_name=ingredient
                 )
        new_object.save()
        return db_retrieve_ingredient(ingredient)
    except:
        return False


def db_retrieve_ingredient(ingredient):
    try:
        return IngredientModel.objects.get(ingredient_name=ingredient)
    except:
        return False