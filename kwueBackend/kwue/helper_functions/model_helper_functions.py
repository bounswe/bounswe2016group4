def food_model_to_dict(food):
    food_dict = food.dict()
    del food_dict['_state']
    return food_dict


def food_model_to_search(food_list):
    search_elements = []
    for food in food_list:
        food_dict = dict(
            food_id=food.food_id,
            food_name=food.food_name
        )
        search_elements.append(food_dict)
    return search_elements
