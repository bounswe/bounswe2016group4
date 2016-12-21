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
    return User.objects.create_user(username="TestUser", email="testuser@testuser.com", password="123456",id= 3)

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


class foodManupulation(unittest.TestCase)

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






         #    class TestClient(Client):

#        def login_user(self, user):
#            """
#            Login as specified user, does not depend on auth backend (hopefully)

#            This is based on Client.login() with a small hack that does not
#           require the call to authenticate()
#            """
#            if not 'django.contrib.sessions' in settings.INSTALLED_APPS:
#                raise AssertionError("Unable to login without django.contrib.sessions in INSTALLED_APPS")
        #            user.backend = "%s.%s" % ("django.contrib.auth.backends",
        #                             "ModelBackend")
        #   engine = import_module(settings.SESSION_ENGINE)

            # Create a fake request to store login details.
        #   request = HttpRequest()
        #   if self.session:
        #       request.session = self.session
        #   else:
        #       request.session = engine.SessionStore()
        #   login(request)

    #            request.session.save()

# Create your tests here.
