from kwue.models.models import *


def db_retrieve_user(user_id):
    user = UserModel.objects.get(user_id=user_id)
    return user
