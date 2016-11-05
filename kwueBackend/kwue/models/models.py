from django.db import models
from unixtimestampfield.fields import UnixTimeStampField
from django.contrib.contenttypes.fields import GenericForeignKey
from django.contrib.contenttypes.models import ContentType
from mptt.models import MPTTModel, TreeForeignKey


class UserModel(models.Model):
    user_id = models.AutoField(primary_key=True)
    user_name = models.TextField()
    user_nick = models.TextField()
    user_email_address = models.EmailField()
    user_password = models.CharField(max_length=25)
    user_image = models.URLField()
    user_type = models.BooleanField(default=False)

    def __str__(self):
        return self.user_name


class FoodModel(models.Model):
    food_id = models.AutoField(primary_key=True)
    food_description = models.CharField(max_length=300)
    food_name = models.TextField()
    food_image = models.URLField()
    food_owner = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    food_rate = models.IntegerField(default=0)

    def __str__(self):
        return self.food_name


class CommentModel(MPTTModel):
    comment_id = models.AutoField(primary_key=True)
    comment_text = models.TextField(null=True)
    content_type = models.ForeignKey(ContentType, on_delete=models.CASCADE, default=None, blank=True)
    commented_object_id = models.PositiveIntegerField(default=0)
    commented_object = GenericForeignKey('content_type', 'commented_object_id')
    comment_date = UnixTimeStampField(auto_now_add=True)
    comment_image = models.URLField()
    comment_vote = models.IntegerField(default=0)
    comment_owner = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    parent = TreeForeignKey('self', null=True, blank=True, related_name='children')

    def save(self):
        if self.parent is not None:
            self.commented_object = self.parent
            super().save(self)

    def __str__(self):
        return self.comment_text

    class MPTTMeta:
        level_attr = 'mptt_level'
        order_insertion_by = ['comment_text']


class ListModel(models.Model):
    list_id = models.AutoField(primary_key=True)
    list_menu = models.BooleanField()
    list_owner = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    list_name = models.TextField()
    list_description = models.TextField()
    list_follower = models.ManyToManyField(UserModel, related_name="followers")
    list_content = models.ManyToManyField(FoodModel, related_name="foods")

    def __str__(self):
        return self.list_name


class ConsumptionHistory(models.Model):
    id = models.AutoField(primary_key=True)
    user = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    food = models.ForeignKey(FoodModel, on_delete=models.CASCADE)
    date = UnixTimeStampField(auto_now_add=True)

    def __str__(self):
        return self.id
