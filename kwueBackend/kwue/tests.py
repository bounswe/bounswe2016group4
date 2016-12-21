import unittest
from django.test import TestCase
from django.conf import settings
from django.contrib.auth import login
from django.http import HttpRequest
from django.test.client import Client

from kwue.models.models import IngredientModel, UserModel, FoodModel, TagModel, CommentModel, ListModel, ConsumptionHistory, SimpleComment

from django.contrib.auth.models import AnonymousUser, User
from django.test.utils import setup_test_environment

def userCreate():
    return User.objects.create_user(username="TestUser", email="testuser@testuser.com", password="123456",id= 3, user_type=True)

class foodCreate(unittest.TestCase):

    def settingUp(self):
       self.user = userCreate()
       self.user.is_authenticated = True

    def test_add_food(self):
        data = {'food_name': 'food1', 'food_id' : 1 , 'food_owner': 'TestUser', 'food_owner_id': 3}

        self.assertEqual(FoodModel.objects.count(), 1)
        self.assertEqual(FoodModel.objects.get().name, 'food1')
        self.assertEqual(FoodModel.objects.get().food_id, 1)
        self.assertEqual(FoodModel.objects.get().food_owner, 'TestUser')
        self.assertEqual(FoodModel.objects.get().food_owner_id, 3)


class foodManupulation(unittest.TestCase):

    def settingUp(self):
       self.user = userCreate()
       self.user.is_authenticated = True

     def add_description(self):
         data = {'food_id':1, 'food_description':'This is a description.'}

         self.assertEqual(FoodModel.objects.count(), 1)
         self.assertEqual(FoodModel.objects.get().food_id, 1)
         self.assertEqual(FoodModel.objects.get().food_description, "This is a description.")

    def rate_food(self):
        data = {'food_rate': 5}

        self.assertEqual(FoodModel.objects.get().food_rate, 5)

class Searching(unittest.TestCase):

    def settingUp(self):
       self.user = userCreate()
       self.user.is_authenticated = True

    def search_food(self):

        data = {'food_name': 'food1'}

        self.assertEqual(FoodModel.objects.count(), 1)
        self.assertEqual(FoodModel.objects.get().food_name, 'food1')

    def search_food_owner(self):

        data = {'food_owner': 'owner'}

        self.assertEqual(FoodModel.objects.get().food_owner, 'owner')

class Tagging(unittest.TestCase):

    def settingUp(self):
       self.user = userCreate()
       self.user.is_authenticated = True

    def add_tag(self):

        data= {'tag_label': 'labl1', 'tagged_object': 'food1'}

        self.assertEqual(TagModel.objects.get().tag_label,'labl1')
        self.assertEqual(TagModel.objects.get().tagged_object,'food1')



