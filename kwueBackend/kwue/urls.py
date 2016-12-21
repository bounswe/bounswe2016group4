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
    # 'is_success'
    # 'reason'
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
    # 'search_text'
    #
    ### RESPONSE
    #
    # 'foods': Array of 'food_id', 'food_name', 'food_image'
    # 'users': Array of 'user_id', 'user_name', 'user_image'
    #
    url(r'^shortcut_semantic_search', search.shortcut_sementic_search),

    ### REQUEST
    # GET
    # 'user_id'
    # 'setting' : 'daily', 'weekly', 'monthly', 'alltime' available so far.
    #
    ### RESPONSE
    #
    # 'foods' : Array of 'food_id', 'food_name', 'food_image' and 'time_added'
    # 'nutritional_values_dict' : Array of nutritional values ->
    #     'protein_value' gr
    #     'fat_value' gr
    #     'carbohydrate_value' gr
    #     'fiber_value' gr
    #     'calorie_value' cal
    #     'sugar_value' gr
    #     'serving_weight_grams' gr
    #     'vitamin_A' IU
    #     'vitamin_C' mg
    #     'vitamin_D' IU
    #     'vitamin_E' mg
    #     'vitamin_K' mcg
    #     'thiamin' mg
    #     'riboflavin' mg
    #     'niacin' mg
    #     'vitamin_B6' mg
    #     'folatem' mcg
    #     'vitamin_B12' mcg
    #     'pantothenic_acid' mg
    #     'choline' mcg
    #     'calcium' mg
    #     'copper' mg
    #     'flouride' mg
    #     'iron_Fe' mg
    #     'magnesium' mg
    #     'manganese' mg
    #     'sodium_Na' mg
    #     'phosphorus' mg
    #     'selenium' mcg
    #     'zinc' mg
    # 'graph_dict': Array of monthly nutritional value graph on daily basis ->
    #     'day_number'
    #     'calorie_value'
    #     'sugar_value'
    #     'protein_value'
    #     'carbohydrate_value'
    #     'fat_value'
    url(r'^get_consumption_history', consumption_history.get_consumption_history),

    ### REQUEST
    # POST
    # 'user_id'
    # 'food_id'
    #
    ### RESPONSE
    #
    # 'is_success'
    # 'reason'
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
    # 'foods' : Array of 'food_id', 'food_image', 'food_name's.
    #
    url(r'^get_user', user.get_user),


    ### REQUEST
    # GET
    # 'user_id'
    # 'search_text'
    #
    ### RESPONSE
    #
    # 'food_set' : Food dicts filtered by search_text and user's eating preferences
    # 'user_set' : Users, may be either a user or food server, filtered by search_text
    # 'semantic_food_set' : Foods semantically found and filtered by user's eating preferences
    # 'semantic_user_set' : Semantically found users, may be either a user or food server
    #
    url(r'^basic_search', search.basic_search),

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
    # 'rate_lower_bound'
    # 'rate_upper_bound'
    # 'wanted_list' : ['pepper','apple'...]
    # 'unwanted_list' : ['pepper','apple'...]
    #
    ### RESPONSE
    #
    # 'food_set' : Food dicts filtered by search_text and user's eating preferences==(food_name, food_id , food_image, calori value)
    # 'user_set' : Users, may be either a user or food server, filtered by search_text==(food_name, food_id , food_image , calori_value)
    # 'semantic_food_set' : Foods semantically found and filtered by user's eating preferences==(user_id,user_name,user_image)
    # 'semantic_user_set' : Semantically found users, may be either a user or food server==(user_id,user_name,user_image)
    #
    url(r'^advanced_search', search.advanced_search),

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
    # 'wanted_list' ['pepper','apple'...]
    # 'unwanted_list' ['pepper','apple'...]
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
    # 'wanted_list' : ['pepper','apple'...]
    # 'unwanted_list' : ['pepper','apple'...]
    #
    ### RESPONSE
    #
    # ''
    #
    url(r'^update_eating_preferences', user.update_eating_preferences),

    ### REQUEST
    # GET
    # empty
    ### RESPONSE
    #
    # renders update eating preference page
    #
    url(r'update_diet_page', user.update_diet_page),

    ### REQUEST
    # GET
    # 'tag_name'
    ### RESPONSE
    # Array of semantic tags
    # 'tag_name'
    # 'tag_id'
    # 'tag_label'
    # 'tag_description'
    url(r'^search_semantic_tags', tag.search_semantic_tags),

    ### REQUEST
    # POST
    # 'food_id'
    # 'rate_value' : Any value from 1.0 to 5.0
    ### RESPONSE
    # 'is_success'
    # 'reason'
    url(r'^rate_food', food.rate_food),

    ### REQUEST
    # POST
    # 'food_id'
    # 'comment_text'
    ### RESPONSE
    # 'is_success'
    # 'reason'
    url(r'^comment_food', food.comment_food),

    ### REQUEST
    # GET
    # 'food_id'
    ### RESPONSE
    # 'serving_weight_grams'
    # 'protein_value' g
    # 'calorie_value' g
    # 'fiber_value' g
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
    # 'comments' : Array of 'user_id', 'user_name', 'comment_text's.
    url(r'^get__food', food.get_food, name='get_food'),


    ### REQUEST
    # GET
    # 'food_id'
    #
    ### RESPONSE
    #
    # renders food page
    #
    url(r'^food_page', food.get_food_page),

    ### REQUEST
    # GET
    # 'ingredients' : should be in json format [{"ingredient":ing1, "value":val1},{"ingredient":ing2, "value": val2}]"
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
    # 'number_of_servings' : Identifies how many people are these ingredients of the food for.
    # 'ingredients' : should be in json format [{"ingredient":ing1, "value":val1},{"ingredient":ing2, "value": val2}]"
    # 'food_tags' : should be array of "tag_name" and "tag_id" "tag_label" "tag_description" given from semantic tag api.
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    url(r'^add_food', food.add_food, name='add_food'),


    ### REQUEST
    # POST
    # 'food_id'
    ### RESPONSE
    # 'is_success' : if adding food process is successful or not
    # 'reason' : if not successful, the reason of it
    # NOT USED. url(r'^remove__food', food.remove_food),

    ### REQUEST
    #
    # empty
    #
    ### RESPONSE
    #
    # empty
    #
    url(r'^consumption_history', consumption_history.get_consumption_page, name='consumption_history'),

    ### REQUEST
    # empty
    ### RESPONSE
    # empty
    url(r'^new_food', food.get_add_food_page, name='new_food'),  # Renders "add_food.html"

    ### REQUEST
    # GET
    # 'user_id'
    #
    ### RESPONSE
    #
    #
    #
    url(r'^user_profile_page', user.get_user_profile_page, name='user_profile_page'),

    ### REQUEST
    # GET
    #
    #
    ### RESPONSE
    # if user is not food server
    #  'recommendation' -> Array of JSONs of
    #      'food_image'
    #      'food_id'
    #      'calorie_value'
    #      'food_rate'
    #      'food_name'
    # if user is food server
    #  'analysis_report'
    #     -> 'most_commented_foods' -> Array of JSONs of
    #             'food_image'
    #             'food_id'
    #             'food_name'
    #     -> 'high_rated_foods' -> Array of JSONs of
    #             'food_image'
    #             'food_id'
    #             'food_name'
    #     -> 'consumed_number' -> An integer meaning how many times, foods of this food server are eaten.
    #     -> 'most_consumed_foods' -> Array of JSONs of
    #             'food_image'
    #             'food_id'
    #             'food_name'
    #     -> 'last_comments' -> Array of JSONs of
    #             'comment_id'
    #             'comment_text'
    #             'food_id'
    #             'food_image'
    #             'food_name'
    #             'user_id'
    #             'user_image'
    #             'user_name'
    #     -> 'comment_number' -> An integer meaning how many times, foods of this food server are commented.
    url(r'^$', home.get_home, name='home'),

    ### REQUEST
    # GET
    #
    #
    ### RESPONSE
    # if user is not food server
    #  'recommendation' -> Array of JSONs of
    #      'food_image'
    #      'food_id'
    #      'calorie_value'
    #      'food_rate'
    #      'food_name'
    # if user is food server
    #  'analysis_report'
    #     -> 'most_commented_foods' -> Array of JSONs of
    #             'food_image'
    #             'food_id'
    #             'food_name'
    #     -> 'high_rated_foods' -> Array of JSONs of
    #             'food_image'
    #             'food_id'
    #             'food_name'
    #     -> 'consumed_number' -> An integer meaning how many times, foods of this food server are eaten.
    #     -> 'most_consumed_foods' -> Array of JSONs of
    #             'food_image'
    #             'food_id'
    #             'food_name'
    #     -> 'last_comments' -> Array of JSONs of
    #             'comment_id'
    #             'comment_text'
    #             'food_id'
    #             'food_image'
    #             'food_name'
    #             'user_id'
    #             'user_image'
    #             'user_name'
    #     -> 'comment_number' -> An integer meaning how many times, foods of this food server are commented.
    url(r'^get_home', home.get_home_url),


    ### REQUEST
    #
    #  empty
    #
    ### RESPONSE
    #
    #  renders login page
    #
    url(r'^login', user.get_login, name='login'),


    ### REQUEST
    # POST
    # 'user_email_address'
    # 'user_password'
    #
    ### RESPONSE
    #
    #
    #
    ## DISCLAIMER: THIS METHOD MAY BE CHANGED. JUST FOR TEST FOR NOW.
    url(r'^validate_login', user.login),

    ### REQUEST
    #
    #
    ### RESPONSE
    #
    #
    ## DISCLAIMER: THIS METHOD MAY BE CHANGED. JUST FOR TEST FOR NOW.
    url(r'^logout', user.logout, name='logout'),

    ### REQUEST
    #
    # empty
    #
    ### RESPONSE
    #
    # renders signup page
    #
    url(r'^signup', user.signup_page, name='signup'),


    ### REQUEST
    # POST
    # 'user_name'
    # 'user_nick'
    # 'user_email_address'
    # 'user_password'
    # 'user_image'
    # 'user_type'
    ### RESPONSE
    #
    # renders home page
    #
    url(r'^sign_request', user.sign_up),

    ### REQUEST
    #
    # empty
    #
    ### RESPONSE
    #
    # 'is_success
    #
    url(r'^create_session', home.create_session),

   ### REQUEST
   # GET
   # 'user_email_address'
   # 'user_password'
   #
   ### RESPONSE
   #
   # 'user_id'
   #
    url(r'^android_login', user.android_login),

   ### REQUEST
   # GET
   # empty
   #
   ### RESPONSE
   #
   # 'is_success'
   #
    url(r'^android_logout', user.android_logout),
)

