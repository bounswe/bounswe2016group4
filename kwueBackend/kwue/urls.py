from django.conf.urls import url
from kwue.controllers import food
from kwue.controllers import home

urlpatterns = (
    url(r'^get_a_food/(?P<food_id>[0-9]+)', food.get_food),
    url(r'^remove_a_food/(?P<food_id>[0-9]+)', food.remove_food),
    url(r'^add_food/', food.add_food),
    url(r'^up_rate_food/(?P<food_id>[0-9]+)', food.up_rate),
    url(r'^down_rate_food/(?P<food_id>[0-9]+)', food.down_rate),
    url(r'^$', home.get_home),
)