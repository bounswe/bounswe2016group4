from django.conf.urls import url
from kwue.controllers import food
from kwue.controllers import home
from kwue.controllers import tag

urlpatterns = (
    url(r'^search_semantic_tags/(?P<tag_name>.+)', tag.search_semantic_tags),
    url(r'^get_a_food/(?P<food_id>[0-9]+)', food.get_food),
    url(r'^remove_a_food/(?P<food_id>[0-9]+)', food.remove_food),
    url(r'^add_food/', food.add_food),
    url(r'^$', home.get_home),
)