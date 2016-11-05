from django.conf.urls import url
from kwue.controllers import food

urlpatterns = (
    url(r'^get_a_food/(?P<food_id>[0-9]+)', food.get_food),
    url(r'^home/', food.rate_food),
    url(r'^', food.rate_food),
)