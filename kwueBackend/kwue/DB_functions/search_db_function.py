from kwue.models.models import FoodModel


# def allergic_excluder(foods, ingredient):
#     foods.exclude(ingredient_list__in=[ingredient])
#     return foods


def allergic_search(ingredient_list, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return list(foods.exclude(ingredient_list__in=ingredient_list))


def protein_search(lower_bound=0,upper_bound=1000,foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return list(foods.filer(protein_value__range=(lower_bound,upper_bound)))


def fat_search(lower_bound=0,upper_bound=1000,foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return list(foods.filer(fat_value__range=(lower_bound,upper_bound)))


def carbohydrate_search(lower_bound=0,upper_bound=1000,foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return list(foods.filer(carbohydrate_value__range=(lower_bound,upper_bound)))


def calorie_search(lower_bound=0,upper_bound=1000,foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return list(foods.filer(calorie_value__range=(lower_bound,upper_bound)))


