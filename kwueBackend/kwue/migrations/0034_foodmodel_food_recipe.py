# -*- coding: utf-8 -*-
# Generated by Django 1.10.2 on 2016-11-06 11:42
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('kwue', '0033_auto_20161106_1130'),
    ]

    operations = [
        migrations.AddField(
            model_name='foodmodel',
            name='food_recipe',
            field=models.TextField(default=2),
            preserve_default=False,
        ),
    ]