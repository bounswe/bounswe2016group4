from django.conf.urls import url
from kwue.controllers import food
from kwue.controllers import home
from kwue.controllers import tag

urlpatterns = (

    ### REQUEST
    # 'tag_name'
    ### RESPONSE
    # Array of semantic tags
    # 'item'
    # 'itemLabel'
    # 'itemDescription'
    url(r'^search_semantic_tags', tag.search_semantic_tags),

    ### REQUEST
    # 'food_id'
    ### RESPONSE
    # 'serving_weight_grams'
    # 'protein_value'
    # 'calorie_value'
    # 'fiber_value'
    # 'food_owner_id'
    # 'food_recipe'
    # 'food_description'
    # 'food_image'
    # 'food_name'
    # 'fat_value'
    # 'sugar_value'
    # 'food_rate'
    # 'food_id'
    # 'carbohydrate_value'
    url(r'^get__food', food.get_food),  # Renders "food.html"

    ### REQUEST
    # 'food_description'
    # 'food_name'
    # 'food_image' : should be a url
    # 'food_owner'
    # 'food_recipe' : should be in format "<ingredient> <value> \n <ingredient> <value> \n .."
    # 'food_tags' : should be array of "tag_name" "item" "itemLabel" "itemDescription" given from semantic tag api.
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    url(r'^add_food', food.add_food),

    ### REQUEST
    # 'food_id'
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    url(r'^remove__food', food.remove_food),

    url(r'^$', home.get_home),
)