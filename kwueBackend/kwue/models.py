from django.db import models
import uuid
# Create your models here.

from django.db import models
from django.utils import timezone


class Food(models.Model):
    food_id = models.AutoField(primary_key=True)
    food_description = models.CharField(max_length=300)
    food_name = models.TextField()
    food_created_date = models.DateTimeField(
        default=timezone.now)
    food_image = models.ImageField(upload_to='pic_folder/', default='pic_folder/None/no-img.jpg')
    food_id = models.IntegerField()

