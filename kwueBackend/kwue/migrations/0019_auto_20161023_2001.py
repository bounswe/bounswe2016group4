# -*- coding: utf-8 -*-
# Generated by Django 1.10.2 on 2016-10-23 20:01
from __future__ import unicode_literals

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('kwue', '0018_auto_20161023_2000'),
    ]

    operations = [
        migrations.AlterField(
            model_name='commentmodel',
            name='content_type',
            field=models.ForeignKey(blank=True, default=None, on_delete=django.db.models.deletion.CASCADE, to='contenttypes.ContentType'),
        ),
    ]