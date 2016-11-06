import json
import requests


def parse_nutrition(data):
    data = json.loads(data)
    nutrition_dict = dict(protein_value=data['foods'][0]['nf_protein'], fat_value=data['foods'][0]['nf_total_fat'],
                          carbonhydrate_value=data['foods'][0]['nf_total_carbohydrate'],
                          fiber_value=data['foods'][0]['nf_dietary_fiber'],
                          calorie_value=data['foods'][0]['nf_calories'], sugar_value=data['foods'][0]['nf_sugars'],
                          serving_weight_grams=data['foods'][0]['serving_weight_grams'])
    return nutrition_dict


def request_nutrition(recipe):
    url = 'https://trackapi.nutritionix.com/v2/natural/nutrients'
    app_id = '24f13571'
    app_key = '0acc5c00e3aa18a297a3455338ffac28'
    headers = {'Content-Type': 'application/json', 'x-app-id': app_id, 'x-app-key': app_key}
    sent_data = json.dumps({'query':recipe, "aggregate": "recipe"})
    r = requests.post(url=url, headers=headers, data=sent_data)
    if r.status_code == 200:
        return parse_nutrition(r.text)
    else:
        print('requests cannot handle', r.status_code)
        return None

