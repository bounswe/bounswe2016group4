from django.db import models
from unixtimestampfield.fields import UnixTimeStampField


class FoodServerModel(models.Model):
    food_server_id = models.AutoField(primary_key=True)
    food_server_name = models.TextField()
    food_server_email = models.EmailField()
    food_server_password = models.CharField(max_length=25)

    def __str__(self):
        return 'MyModel: {}'.format(self.food_server_name)


class UserModel(models.Model):
    user_id = models.AutoField(primary_key=True)
    user_name = models.TextField()
    user_surname = models.TextField()
    user_email_address = models.EmailField()
    user_password = models.CharField(max_length=25)

    def __str__(self):
        return 'MyModel: {}'.format(self.user_name)


class FoodModel(models.Model):
    food_id = models.AutoField(primary_key=True)
    food_description = models.CharField(max_length=300)
    food_name = models.TextField()
    food_image = models.ImageField(upload_to='pic_folder', default='pic_folder/None/no-img.jpg')
    food_server_id = models.ForeignKey(FoodServerModel, on_delete=models.CASCADE)

    def __str__(self):
        return 'MyModel: {}'.format(self.food_name)


class CommentModel(models.Model):
    comment_id = models.AutoField(primary_key=True)
    comment_text = models.TextField()
    food_server_id = models.ForeignKey(FoodServerModel, on_delete=models.CASCADE)
    food_id = models.ForeignKey(FoodModel, on_delete=models.CASCADE)
    user_id = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    child_id = models.ForeignKey('self', default=None)
    commit_date = UnixTimeStampField(auto_now_add=True)

    def __str__(self):
        return 'MyModel: {}'.format(self.comment_text)


class ListModel(models.Model):
    food_list_id = models.AutoField(primary_key=True)
    list_is_menu = models.BooleanField()
    owner_user_id = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    list_name = models.TextField()
    list_description = models.TextField()
    list_follower = models.ManyToManyField(UserModel, related_name="followers")
    list_food = models.ManyToManyField(FoodModel, related_name="foods")


class ConsumedFood(models.Model):
    consumed_food = models.ForeignKey(FoodModel, on_delete=models.CASCADE)
    consumer_food = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    consumed_date = UnixTimeStampField(auto_now_add=True)

    def __str__(self):
        return 'MyModel: {}'.format(self.id)




