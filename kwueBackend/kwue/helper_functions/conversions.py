import json

def ingredients_from_list_to_dict(ep):
    wanted_list = ep['wanted_list']
    unwanted_list = ep['unwanted_list']
    wanted_list_dict = []
    unwanted_list_dict = []
    for wanted_item in wanted_list:
        wanted_list_dict.append({'name': wanted_item})
    for unwanted_item in unwanted_list:
        unwanted_list_dict.append({'name': unwanted_item})
    ep['wanted_list'] = wanted_list_dict
    ep['unwanted_list'] = unwanted_list_dict
    return ep

def ingredients_from_dict_to_list(ep):
    wanted_list_dict = json.loads(ep['wanted_list'])
    unwanted_list_dict = json.loads(ep['unwanted_list'])
    wanted_list = []
    unwanted_list = []
    for wanted_item in wanted_list_dict:
        wanted_list.append(wanted_item['name'])
    for unwanted_item in unwanted_list_dict:
        unwanted_list.append(unwanted_item['name'])
    ep['wanted_list'] = wanted_list
    ep['unwanted_list'] = unwanted_list
    return ep