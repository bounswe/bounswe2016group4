# -*- coding: utf-8 -*-
# Generated by Django 1.10.2 on 2016-10-22 13:28
from __future__ import unicode_literals

from django.db import migrations, models
import django.db.models.deletion
import unixtimestampfield.fields


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='CommentModel',
            fields=[
                ('comment_id', models.AutoField(primary_key=True, serialize=False)),
                ('comment_text', models.TextField()),
                ('commit_date', unixtimestampfield.fields.UnixTimeStampField(auto_now_add=True)),
                ('child_id', models.ForeignKey(default=None, on_delete=django.db.models.deletion.CASCADE, to='kwue.CommentModel')),
            ],
        ),
        migrations.CreateModel(
            name='EatingPreferenceModel',
            fields=[
                ('eating_preference_id', models.AutoField(primary_key=True, serialize=False)),
            ],
        ),
        migrations.CreateModel(
            name='FoodModel',
            fields=[
                ('food_id', models.AutoField(primary_key=True, serialize=False)),
                ('food_description', models.CharField(max_length=300)),
                ('food_name', models.TextField()),
                ('food_image', models.ImageField(default=b'pic_folder/None/no-img.jpg', upload_to=b'pic_folder')),
            ],
        ),
        migrations.CreateModel(
            name='FoodServerModel',
            fields=[
                ('food_server_id', models.AutoField(primary_key=True, serialize=False)),
                ('food_server_name', models.TextField()),
            ],
        ),
        migrations.CreateModel(
            name='UserModel',
            fields=[
                ('user_id', models.AutoField(primary_key=True, serialize=False)),
                ('user_name', models.TextField()),
                ('user_surname', models.TextField()),
                ('user_email_address', models.EmailField(max_length=254)),
                ('user_password', models.CharField(max_length=25)),
            ],
        ),
        migrations.AddField(
            model_name='foodmodel',
            name='food_server_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='kwue.FoodServerModel'),
        ),
        migrations.AddField(
            model_name='commentmodel',
            name='food_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='kwue.FoodModel'),
        ),
        migrations.AddField(
            model_name='commentmodel',
            name='food_server_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='kwue.FoodServerModel'),
        ),
        migrations.AddField(
            model_name='commentmodel',
            name='user_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='kwue.UserModel'),
        ),
    ]