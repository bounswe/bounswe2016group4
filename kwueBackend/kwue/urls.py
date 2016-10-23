from django.conf.urls import url
from kwue.controllers import food

urlpatterns = (
    url(r'^$', food.add_food),
)