import json
import requests


def parse_nutrition(data):
    data = json.loads(data)
    full_nutrients_dict = parse_full_nutrients(data)
    nutrition_dict = dict(protein_value=data['foods'][0]['nf_protein'], fat_value=data['foods'][0]['nf_total_fat'],
                          carbohydrate_value=data['foods'][0]['nf_total_carbohydrate'],
                          fiber_value=data['foods'][0]['nf_dietary_fiber'],
                          calorie_value=data['foods'][0]['nf_calories'], sugar_value=data['foods'][0]['nf_sugars'],
                          serving_weight_grams=data['foods'][0]['serving_weight_grams'])
    for key, value in full_nutrients_dict.items():
        nutrition_dict[key] = value
    print(nutrition_dict)
    return nutrition_dict


def parse_full_nutrients(data):
    full_nutr_dict = dict(
        vitamin_A=318,
        vitamin_C=401,
        vitamin_D=328,
        vitamin_E=573,
        vitamin_K=430,
        thiamin=404,
        riboflavin=405,
        niacin=406,
        vitamin_B6=415,
        folatem=432,
        vitamin_B12=418,
        pantothenic_acid=410,
        biotin=410,  ###################
        choline=421,
        calcium=301,
        chromium=301,  ####################
        copper=312,
        clouride=312,  ############
        flouride=313,
        iodine=313,  ##################
        iron_Fe=303,
        magnesium=304,
        manganese=315,
        molybdenum=315,  ##############
        sodium_Na=307,
        phosphorus=305,
        selenium=317,
        zinc=309
    )
    for entry in data['foods'][0]["full_nutrients"]:
            nutrient_id = entry['attr_id']
            value = entry['value']
            for key, number in full_nutr_dict.items():
                if nutrient_id == number:
                    full_nutr_dict[key] = value
    return full_nutr_dict


def request_nutrition(recipe, num_servings=1):
    url = 'https://trackapi.nutritionix.com/v2/natural/nutrients'
    app_id = '24f13571'
    app_key = '0acc5c00e3aa18a297a3455338ffac28'
    headers = {'Content-Type': 'application/json', 'x-app-id': app_id, 'x-app-key': app_key}
    sent_data = json.dumps(
        {'query': recipe, "aggregate": "recipe", "line_delimited": True, "num_servings": num_servings})
    r = requests.post(url=url, headers=headers, data=sent_data)
    print(r.text)
    if r.status_code == 200:
        return parse_nutrition(r.text)
    else:
        print('requests cannot handle', r.status_code)
        return None
