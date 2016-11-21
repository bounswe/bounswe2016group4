# from SPARQLWrapper import SPARQLWrapper, JSON
#
# def get_semantic_tags(tag_name):
#     sparql = SPARQLWrapper("https://query.wikidata.org/sparql")
#     sparql.setQuery("""
#             prefix wdt: <http://www.wikidata.org/prop/direct/>
#             prefix wd: <http://www.wikidata.org/entity/>
#             prefix wikibase: <http://wikiba.se/ontology#>
#             prefix bd: <http://www.bigdata.com/rdf#>
#             SELECT ?item ?itemLabel ?itemDescription WHERE {
#             ?item ?label "%s"@en.
#             SERVICE wikibase:label {
#                  bd:serviceParam wikibase:language "en" .
#              }
#             }
#             LIMIT 10
#             """ % tag_name)
#     sparql.setReturnFormat(JSON)
#     results = sparql.query().convert()
#     semanticTags = []
#     for result in results["results"]["bindings"]:
#         semanticTag = dict(
#             item=result['item']['value'],
#             itemLabel=result['itemLabel']['value'],
#             itemDescription=result['itemDescription']['value'] if 'itemDescription' in result else ""
#         )
#         semanticTags.append(semanticTag)

import requests


def get_semantic_tags(tag_name):
    url = 'https://www.wikidata.org/w/api.php?action=wbsearchentities&search=%s&format=json&language=en&type=item&continue=0' % tag_name
    r = requests.get(url)
    tags = r.json()['search']
    semantic_tags = []
    for tag in tags:
        semantic_tag = dict(
            tag_name=tag_name,
            tag_id=tag['id'],
            tag_label=tag['label'],
            tag_description=tag['description'] if 'description' in tag else ""
        )
        semantic_tags.append(semantic_tag)
    return semantic_tags


def most_common(lst):
        return max(set(lst), key=lst.count)