from django.conf.urls import url
from kwue.controllers import food
from kwue.controllers import home
from kwue.controllers import tag
from kwue.controllers import search
from kwue.controllers import user
from kwue.controllers import consumption_history

urlpatterns = (

    ### REQUEST
    #
    # 'user_id'
    # 'setting' : 'daily', 'weekly', 'monthly', 'alltime' available so far.
    #
    ### RESPONSE
    #
    # under construction
    #
    url(r'^get_consumption_history', consumption_history.get_consumption_history),

    ### REQUEST
    #
    # 'user_id'
    # 'food_id'
    #
    ### RESPONSE
    #
    # nothing
    #
    url(r'^mark_as_eaten', consumption_history.mark_as_eaten),

    ### REQUEST
    #
    # 'user_id'
    #
    ### RESPONSE
    #
    # 'user_name'
    # 'user_nick'
    # 'user_email_address'
    # 'user_image'
    # 'user_type'
    # 'unwanted_ingredients'
    # 'wanted_ingredients'
    # 'protein_lower_bound'
    # 'fat_lower_bound'
    # 'carbohydrate_lower_bound'
    # 'calorie_lower_bound'
    # 'sugar_lower_bound'
    # 'protein_upper_bound'
    # 'fat_upper_bound'
    # 'carbohydrate_upper_bound'
    # 'calorie_upper_bound'
    # 'sugar_upper_bound'
    #
    url(r'^user_profile_page', user.get_user),  # Renders 'user_profile_page.html'

    ### REQUEST
    #
    # 'user_id'
    #
    ### RESPONSE
    #
    # 'user_name'
    # 'user_nick'
    # 'user_email_address'
    # 'user_image'
    # 'user_type'
    # 'unwanted_ingredients'
    # 'wanted_ingredients'
    # 'protein_lower_bound'
    # 'fat_lower_bound'
    # 'carbohydrate_lower_bound'
    # 'calorie_lower_bound'
    # 'sugar_lower_bound'
    # 'protein_upper_bound'
    # 'fat_upper_bound'
    # 'carbohydrate_upper_bound'
    # 'calorie_upper_bound'
    # 'sugar_upper_bound'
    #
    url(r'^user_profile_page', user.get_user_profile_page),


    ### REQUEST
    #
    # 'user_id'
    # 'search_text'
    #
    ### RESPONSE
    #
    # 'food_set' : Food dicts filtered by search_text and user's eateing preferences
    # 'food_server_set' : Users, may be either a user or food server, filtered by search_text
    # 'semantic_foods' : Foods semantically found and filtered by user's eating preferences
    # 'semantic_users' : Semantically found users, may be either a user or food server
    #
    url(r'^basic_search', search.basic_search),  # Renders 'search.html'

    ### REQUEST
    #
    # 'search_text'
    # 'protein_lower_bound'
    # 'fat_lower_bound'
    # 'carbohydrate_lower_bound'
    # 'calorie_lower_bound'
    # 'sugar_lower_bound'
    # 'protein_upper_bound'
    # 'fat_upper_bound'
    # 'carbohydrate_upper_bound'
    # 'calorie_upper_bound'
    # 'sugar_upper_bound'
    # 'wanted_list'
    # 'unwanted_list'
    #
    ### RESPONSE
    #
    # 'food_set' : Food dicts filtered by search_text and user's eating preferences
    # 'food_server_set' : Users, may be either a user or food server, filtered by search_text
    # 'semantic_foods' : Foods semantically found and filtered by user's eating preferences
    # 'semantic_users' : Semantically found users, may be either a user or food server
    #
    url(r'^advanced_search', search.advanced_search),  # Renders 'search.html'

    ### REQUEST
    #
    # 'user_id'
    #
    ### RESPONSE
    #
    # 'protein_lower_bound'
    # 'fat_lower_bound'
    # 'carbohydrate_lower_bound'
    # 'calorie_lower_bound'
    # 'sugar_lower_bound'
    # 'protein_upper_bound'
    # 'fat_upper_bound'
    # 'carbohydrate_upper_bound'
    # 'calorie_upper_bound'
    # 'sugar_upper_bound'
    # 'wanted_list'
    # 'unwanted_list'
    url(r'^get_eating_preferences', user.get_eating_preferences),


    ### REQUEST
    #
    # 'user_id'
    # 'protein_lower_bound'
    # 'fat_lower_bound'
    # 'carbohydrate_lower_bound'
    # 'calorie_lower_bound'
    # 'sugar_lower_bound'
    # 'protein_upper_bound'
    # 'fat_upper_bound'
    # 'carbohydrate_upper_bound'
    # 'calorie_upper_bound'
    # 'sugar_upper_bound'
    # 'wanted_list'
    # 'unwanted_list'
    #
    ### RESPONSE
    #
    # ''
    #
    url(r'^update_eating_preferences', user.update_eating_preferences),

    ### REQUEST
    # 'tag_name'
    ### RESPONSE
    # Array of semantic tags
    # 'item'
    # 'itemLabel'
    # 'itemDescription'
    url(r'^search_semantic_tags', tag.seach_semantic_tags),

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
    url(r'^get__food', food.get_food, name='get_food'),  # Renders "food.html"

    ### REQUEST
    # 'ingredients' : should be in json format [{ing1, val1},{ing2, val2},{ing3, val3},..]"
    ### RESPONSE
    # 'calorie_value'
    # 'serving_weight_grams'
    # 'fat_value'
    # 'fiber_value'
    # 'carbohydrate_value'
    # 'sugar_value'
    # 'protein_value'
    url(r'^get_nutritional_values', food.get_nutritional_values),

    ### REQUEST
    # 'food_description'
    # 'food_name'
    # 'food_image' : should be a url
    # 'food_owner'
    # 'ingredients' : should be in json format [{ing1, val1},{ing2, val2},{ing3, val3},..]"
    # 'food_tags' : should be array of "tag_name" "item" "itemLabel" "itemDescription" given from semantic tag api.
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    url(r'^add_food', food.add_food, name='add_food'),

    ### REQUEST
    #
    #   to be filled
    #
    ### RESPONSE
    #
    #   to be filled
    #
    url(r'^new_food', food.get_add_food_page, name='new_food'), # Renders "add_food.html"

    ### REQUEST
    # 'food_id'
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    url(r'^remove__food', food.remove_food),

    url(r'^$', home.get_home, name='home'),
)
