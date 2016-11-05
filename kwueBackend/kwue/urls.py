from django.conf.urls import url
from kwue.controllers import food

urlpatterns = (
    url(r'^get_a_food/', food.get_food),
    url(r'^$', food.dummy_home),
    url(r'^add_food/', food.add_food)
)