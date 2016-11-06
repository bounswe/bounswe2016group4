from django.conf.urls import url
from kwue.controllers import food
from kwue.controllers import home

urlpatterns = (
    url(r'^get_a_food/', food.get_food, name='get_food'),
    url(r'^remove_a_food/', food.remove_food, name='remove_food'),
    url(r'^add_food/', food.add_food, name='add_food'),
    url(r'^$', home.get_home, name='home'),
)