from SPARQLWrapper import SPARQLWrapper, JSON

def get_semantic_tags(tag_name):
    sparql = SPARQLWrapper("https://query.wikidata.org/sparql")
    sparql.setQuery("""
            prefix wdt: <http://www.wikidata.org/prop/direct/>
            prefix wd: <http://www.wikidata.org/entity/>
            prefix wikibase: <http://wikiba.se/ontology#>
            prefix bd: <http://www.bigdata.com/rdf#>
            SELECT ?item ?itemLabel ?itemDescription WHERE {
            ?item ?label "%s"@en.
            SERVICE wikibase:label {
                 bd:serviceParam wikibase:language "en" .
             }
            }
            """ % tag_name)
    sparql.setReturnFormat(JSON)
    results = sparql.query().convert()
    semanticTags = []
    for result in results["results"]["bindings"]:
        semanticTag = dict(
            item=result['item']['value'],
            itemLabel=result['itemLabel']['value'],
            itemDescription=result['itemDescription']['value']
        )
        semanticTags.append(semanticTag)
    return semanticTags