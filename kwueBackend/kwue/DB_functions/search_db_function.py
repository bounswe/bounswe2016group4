from kwue.models.models import FoodModel, TagModel, UserModel
from django.contrib.postgres.search import TrigramSimilarity
from kwue.helper_functions.semantic_tag_helpers import most_common
from collections import Counter
# def allergic_excluder(foods, ingredient):
#     foods.exclude(ingredient_list__in=[ingredient])
#     return foods


def basic_search(focus_string):
    semantic_relation_food = FoodModel.objects.filter(food_name__iexact=focus_string).values_list('food_id')
    semantic_tags = list(TagModel.objects.filter(tagged_object_id__in=semantic_relation_food,content_type__model="foodmodel").values_list('semantic_tag_item_description'))

    semantic_relation_user = UserModel.objects.filter(user_name__iexact=focus_string).values_list('user_id')
    semantic_tags.extend(list(TagModel.objects.filter(tagged_object_id__in=semantic_relation_user,content_type__model="usermodel").values_list('semantic_tag_item_description')))

    print(semantic_tags)
    mcommon = [ite[0] for ite, it in Counter(semantic_tags).most_common(5)]
    semantic_objects = TagModel.objects.filter(semantic_tag_item_description__in=mcommon)
    semantic_food = []
    semantic_user = []
    print(semantic_objects)
    for x in list(semantic_objects):
        if x.content_type.model == "foodmodel":
            semantic_food.append(x.tagged_object)
        else:
            semantic_user.append(x.tagged_object)
    similar_foods = FoodModel.objects.filter(food_name__icontains=focus_string)
    from_tag = TagModel.objects.filter(tag_label__icontains=focus_string) | TagModel.objects.filter(
        semantic_tag_item_label__icontains=focus_string)
    print(from_tag)
    from_tag_food = from_tag.filter(content_type__model="foodmodel")
    foods = FoodModel.objects.filter(food_id__in=from_tag_food.values('tagged_object_id')) | similar_foods
    similar_food_servers = UserModel.objects.filter(user_name__icontains=focus_string)
    print(similar_food_servers)
    from_tag_food_server = from_tag.filter(content_type__model='usermodel')
    food_servers = UserModel.objects.filter(user_id__in=from_tag_food_server.values('tagged_object_id')) | similar_food_servers
    return dict(food_set=foods, food_server_set=food_servers,semantic_foods=semantic_food,semantic_users=semantic_user)



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
