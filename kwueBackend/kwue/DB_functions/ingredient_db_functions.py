from kwue.models.models import IngredientModel


def db_insert_ingredient(ingredient_name):
    try:
        new_object = IngredientModel(
            ingredient_name=ingredient_name
                 )
        new_object.save()
        return new_object
    except:
        return False


def db_retrieve_ingredient(ingredient_name):
    try:
        return IngredientModel.objects.get(ingredient_name_iexact=ingredient_name)
    except:
        return False