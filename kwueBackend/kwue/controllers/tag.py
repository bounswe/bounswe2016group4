from django.http import HttpResponse
import json
from kwue.helper_functions.semantic_tag_helpers import get_semantic_tags

def search_semantic_tags(req):
    semanticTags = get_semantic_tags(req.GET.dict()['tag_name'])
    return HttpResponse(json.dumps(semanticTags), content_type='application/json')
