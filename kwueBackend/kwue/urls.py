from django.conf.urls import url
from kwue.controllers import food
from kwue.controllers import home
from kwue.controllers import tag
from kwue.controllers import search
from kwue.controllers import user
from kwue.controllers import consumption_history

urlpatterns = (

    ### REQUEST
    # POST
    # 'tagged_user_id'
    # 'tag_name'
    # 'tag_id'
    # 'tag_label'
    # 'tag_description'
    #
    ### RESPONSE
    #
    #
    #
    url(r'^tag_user', tag.tag_user),

    ### REQUEST
    # POST
    # 'tagged_food_id'
    # 'tag_name'
    # 'tag_id'
    # 'tag_label'
    # 'tag_description'
    #
    ### RESPONSE
    #
    #
    #
    url(r'^tag_food', tag.tag_food),

    ### REQUEST
    # GET
    # 'user_id'
    # 'setting' : 'daily', 'weekly', 'monthly', 'alltime' available so far.
    #
    ### RESPONSE
    #
    # 'foods' : Array of 'food_id', 'food_name', 'food_image' and 'time_added'
    # 'nutritional_values_dict' : Array of nutritional values ->
    #     'protein_value'
    #     'fat_value'
    #     'carbohydrate_value'
    #     'fiber_value'
    #     'calorie_value'
    #     'sugar_value'
    #     'serving_weight_grams'
    #     'vitamin_A'
    #     'vitamin_C'
    #     'vitamin_D'
    #     'vitamin_E'
    #     'vitamin_K'
    #     'thiamin'
    #     'riboflavin'
    #     'niacin'
    #     'vitamin_B6'
    #     'folatem'
    #     'vitamin_B12'
    #     'pantothenic_acid'
    #     'choline'
    #     'calcium'
    #     'copper'
    #     'flouride'
    #     'iron_Fe'
    #     'magnesium'
    #     'manganese'
    #     'sodium_Na'
    #     'phosphorus'
    #     'selenium'
    #     'zinc'
    #
    url(r'^get_consumption_history', consumption_history.get_consumption_history),

    ### REQUEST
    # POST
    # 'user_id'
    # 'food_id'
    #
    ### RESPONSE
    #
    # nothing
    #
    url(r'^mark_as_eaten', consumption_history.mark_as_eaten),

    ### REQUEST
    # GET
    # 'user_id'
    #
    ### RESPONSE
    #
    # 'user_name'
    # 'user_nick'
    # 'user_email_address'
    # 'user_image'
    # 'user_type'
    # 'tag_list' : Array of 'tag_name', 'tag_id', 'tag_label', 'tag_description's.
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
    url(r'^get_user', user.get_user),  # Renders 'user_profile_page.html'

    ### REQUEST
    # GET
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
    # GET
    # 'user_id'
    # 'search_text'
    #
    ### RESPONSE
    #
    # 'food_set' : Food dicts filtered by search_text and user's eating preferences
    # 'food_server_set' : Users, may be either a user or food server, filtered by search_text
    # 'semantic_foods' : Foods semantically found and filtered by user's eating preferences
    # 'semantic_users' : Semantically found users, may be either a user or food server
    #
    url(r'^basic_search', search.basic_search),  # Renders 'search.html'

    ### REQUEST
    # GET
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
    # 'food_set' : Food dicts filtered by search_text and user's eating preferences==(food_name, food_id , food_image, calori value)
    # 'user_set' : Users, may be either a user or food server, filtered by search_text==(food_name, food_id , food_image , calori_value)
    # 'semantic_food_set' : Foods semantically found and filtered by user's eating preferences==(user_id,user_name,user_image)
    # 'semantic_user_set' : Semantically found users, may be either a user or food server==(user_id,user_name,user_image)
    #
    url(r'^advanced_search', search.advanced_search),  # Renders 'search.html'

    ### REQUEST
    # GET
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
    # POST
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
    # GET
    # 'tag_name'
    ### RESPONSE
    # Array of semantic tags
    # 'tag_id'
    # 'tag_label'
    # 'tag_description'
    url(r'^search_semantic_tags', tag.search_semantic_tags),

    ### REQUEST
    # GET
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
    # 'tag_list' : Array of 'tag_name', 'tag_id', 'tag_label', 'tag_description's.
    url(r'^get__food', food.get_food, name='get_food'),  # Renders "food.html"

    ### REQUEST
    # GET
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
    # POST
    # 'food_description'
    # 'food_name'
    # 'food_image' : should be a url
    # 'food_owner'
    # 'ingredients' : should be in json format [{"ingredient":ing1, "value":val1},{"ingredient":ing2, "value": val2}]"
    # 'food_tags' : should be array of "tag_name" and "tag_id" "tag_label" "tag_description" given from semantic tag api.
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    url(r'^add_food', food.add_food, name='add_food'),

    ### REQUEST
    # empty
    ### RESPONSE
    # empty
    url(r'^new_food', food.get_add_food_page, name='new_food'), # Renders "add_food.html"

    ### REQUEST
    # POST
    # 'food_id'
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    # NOT USED. url(r'^remove__food', food.remove_food),

    url(r'^$', home.get_home, name='home'),
)