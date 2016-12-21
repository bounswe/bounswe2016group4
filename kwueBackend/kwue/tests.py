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
    return User.objects.create_user(username="TestUser", email="testuser@testuser.com", password="123456")

class foodCreate(unittest.TestCase):

    def setUp(self):
        testuser = userCreate()
        testuser.id=99








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
