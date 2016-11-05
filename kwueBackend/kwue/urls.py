from django.conf.urls import url
from kwue.controllers import food

urlpatterns = (
    url(r'^get_a_food/', food.get_food),
    url(r'^$', food.dummy_home),
    url(r'^get_a_food/(?P<food_id>[0-9]+)', food.get_food),
    url(r'^remove_a_food/(?P<food_id>[0-9]+)', food.remove_food),
    url(r'^add_food/', food.add_food)
)