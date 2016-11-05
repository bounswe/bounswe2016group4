from django.contrib import admin

# Register your models here.

from django.contrib import admin
from .models.models import *

admin.site.register(UserModel)
admin.site.register(FoodModel)
admin.site.register(CommentModel)
admin.site.register(ListModel)
