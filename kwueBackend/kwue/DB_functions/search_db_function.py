from kwue.models.models import FoodModel


# def allergic_excluder(foods, ingredient):
#     foods.exclude(ingredient_list__in=[ingredient])
#     return foods


def allergic_search(ingredient_list, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return list(foods.exclude(ingredient_list__in=ingredient_list))

