from kwue.models.models import FoodModel, TagModel, UserModel
from django.contrib.postgres.search import TrigramSimilarity


# def allergic_excluder(foods, ingredient):
#     foods.exclude(ingredient_list__in=[ingredient])
#     return foods


def basic_search(focus_string):
    similar_foods = FoodModel.objects.filter(food_name__icontains=focus_string)
    from_tag = TagModel.objects.filter(tag_label__icontains=focus_string) | TagModel.objects.filter(
        semantic_tag_item_label__icontains=focus_string)
    from_tag_food = from_tag.filter(content_type__model="FoodModel")
    foods = FoodModel.objects.filter(food_id__in=from_tag_food.values('tagged_object_id')) | similar_foods
    similar_food_servers = UserModel.objects.filter(user_nick__icontains=focus_string)
    from_tag_food_server = from_tag.filter(content_type__model='UserModel').distinct()
    food_servers = UserModel.objects.filter(user_id__in=from_tag_food_server.values('tagged_object_id')) | similar_food_servers
    search_dict = dict(
        food=foods,
        food_server=food_servers
    )
    return search_dict


def unwanted_search(ingredient_list, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return foods.exclude(ingredient_list__in=ingredient_list)


def wanted_search(ingredient_list, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return foods.filter(ingredient_list__in=ingredient_list)


def protein_search(lower_bound=0, upper_bound=1000, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return foods.filter(protein_value__range=(lower_bound, upper_bound))


def fat_search(lower_bound=0, upper_bound=1000, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return foods.filter(fat_value__range=(lower_bound, upper_bound))


def carbohydrate_search(lower_bound=0, upper_bound=1000, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return foods.filter(carbohydrate_value__range=(lower_bound, upper_bound))


def calorie_search(lower_bound=0, upper_bound=10000, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return foods.filter(calorie_value__range=(lower_bound, upper_bound))


def rate_search(lower_bound=0, upper_bound=5, foods=None):
    if foods is None:
        foods = FoodModel.objects.all()
    return foods.filter(food_rate__range=(lower_bound, upper_bound))
