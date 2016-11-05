from django.conf.urls import url
from kwue.controllers import food

urlpatterns = (
    url(r'^get_a_food/', food.get_food),
    url(r'^home/', food.add_food),
)