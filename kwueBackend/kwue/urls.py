from django.conf.urls import url
from kwue.controllers import food
from kwue.controllers import home
from kwue.controllers import tag

urlpatterns = (
    url(r'^search_semantic_tags', tag.search_semantic_tags),
    url(r'^get_a_food', food.get_food),
    url(r'^remove_a_food', food.remove_food),
    url(r'^add_food', food.add_food),
    url(r'^$', home.get_home),
)