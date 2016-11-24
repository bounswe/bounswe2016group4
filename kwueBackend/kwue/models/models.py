from django.db import models
from unixtimestampfield.fields import UnixTimeStampField
from django.contrib.contenttypes.fields import GenericForeignKey
from django.contrib.contenttypes.models import ContentType
from mptt.models import MPTTModel, TreeForeignKey
from kwue.helper_functions.time_helpers import show_date
import time

class IngredientModel(models.Model):
    ingredient_name = models.CharField(max_length=100, primary_key=True)

    def __str__(self):
        return self.ingredient_name+" "


class UserModel(models.Model):
    user_id = models.AutoField(primary_key=True)
    user_name = models.TextField()
    user_nick = models.TextField(blank=True)
    user_email_address = models.EmailField()
    user_password = models.CharField(max_length=25)
    user_image = models.URLField(default='',blank=True)
    user_type = models.BooleanField(default=False) ###### False is normal user.
    unwanted_ingredients = models.ManyToManyField(IngredientModel, related_name='wantedIngs', blank=True)
    wanted_ingredients = models.ManyToManyField(IngredientModel, related_name='unwantedIngs', blank=True)
    #######################
    protein_lower_bound = models.FloatField(default=0)
    fat_lower_bound = models.FloatField(default=0)
    carbohydrate_lower_bound = models.FloatField(default=0)
    calorie_lower_bound = models.FloatField(default=0)
    sugar_lower_bound = models.FloatField(default=0)
    #########################
    protein_upper_bound = models.FloatField(default=1000)
    fat_upper_bound = models.FloatField(default=100000)
    carbohydrate_upper_bound = models.FloatField(default=100000)
    calorie_upper_bound = models.FloatField(default=10000)
    sugar_upper_bound = models.FloatField(default=10000)

    def __str__(self):
        return self.user_name


class FoodModel(models.Model):
    food_id = models.AutoField(primary_key=True)
    food_description = models.CharField(max_length=300)
    food_name = models.TextField()
    food_image = models.URLField(blank=True)
    food_owner = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    food_rate = models.FloatField(default=0)
    food_rate_count = models.IntegerField(default=0)
    food_recipe = models.TextField(default=0)
    ingredient_list = models.ManyToManyField(IngredientModel)
    protein_value = models.FloatField(default=0)
    fat_value = models.FloatField(default=0)
    carbohydrate_value = models.FloatField(default=0)
    fiber_value = models.FloatField(default=0)
    calorie_value = models.FloatField(default=0)
    sugar_value = models.FloatField(default=0)
    serving_weight_grams = models.FloatField(default=0)
    vitamin_A = models.FloatField(default=0)
    vitamin_C = models.FloatField(default=0)
    vitamin_D = models.FloatField(default=0)
    vitamin_E = models.FloatField(default=0)
    vitamin_K = models.FloatField(default=0)
    thiamin = models.FloatField(default=0)
    riboflavin = models.FloatField(default=0)
    niacin = models.FloatField(default=0)
    vitamin_B6 = models.FloatField(default=0)
    folatem = models.FloatField(default=0)
    vitamin_B12 = models.FloatField(default=0)
    pantothenic_acid = models.FloatField(default=0)
    choline = models.FloatField(default=0)
    calcium = models.FloatField(default=0)
    copper = models.FloatField(default=0)
    flouride =  models.FloatField(default=0)
    iron_Fe = models.FloatField(default=0)
    magnesium = models.FloatField(default=0)
    manganese = models.FloatField(default=0)
    sodium_Na = models.FloatField(default=0)
    phosphorus = models.FloatField(default=0)
    selenium = models.FloatField(default=0)
    zinc = models.FloatField(default=0)

    def __str__(self):
        return self.food_name


class TagModel(models.Model):
    tag_id = models.AutoField(primary_key=True)
    tag_label = models.TextField(blank=True)
    semantic_tag_item = models.TextField(blank=True)
    semantic_tag_item_label = models.TextField(blank=True)
    semantic_tag_item_description = models.TextField(blank=True)
    content_type = models.ForeignKey(ContentType, on_delete=models.CASCADE, default=None, blank=True)
    tagged_object_id = models.PositiveIntegerField()
    tagged_object = GenericForeignKey('content_type', "tagged_object_id")

    def __str__(self):
        if self.content_type.model=="usermodel":
            return str(self.tagged_object.user_name) + ' <<<===== ' + self.semantic_tag_item_label
        #elif self.content_type.model=="foodmodel":
        else:    return str(self.tagged_object.food_name) + ' <<<===== ' + self.semantic_tag_item_label
        #else:
        #    return "Tagged Object is deleted"



class CommentModel(MPTTModel):
    comment_id = models.AutoField(primary_key=True)
    comment_text = models.TextField(null=True)
    content_type = models.ForeignKey(ContentType, on_delete=models.CASCADE, default=None, blank=True)
    commented_object_id = models.PositiveIntegerField()
    commented_object = GenericForeignKey('content_type', 'commented_object_id')
    comment_date = UnixTimeStampField(auto_now_add=True)
    comment_image = models.URLField(blank=True)
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
    list_follower = models.ManyToManyField(UserModel, related_name="followers", blank=True)
    list_content = models.ManyToManyField(FoodModel, related_name="foods", blank=True)

    def __str__(self):
        return self.list_name


class ConsumptionHistory(models.Model):
    history_id = models.AutoField(primary_key=True)
    user = models.ForeignKey(UserModel, on_delete=models.CASCADE)
    food = models.ForeignKey(FoodModel)
    date = models.IntegerField(default=time.time(), editable=True)


    def __str__(self):
        return show_date(self.date)

