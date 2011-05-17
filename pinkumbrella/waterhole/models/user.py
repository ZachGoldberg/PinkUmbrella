from django.db import models
from pinkumbrella.waterhole.models.service import Service


class User(models.Model):
    uid = models.CharField(max_length=256)
    service = models.ForeignKey ( Service )

    class Meta:
        app_label = 'waterhole'

