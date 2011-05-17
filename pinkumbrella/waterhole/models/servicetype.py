from django.db import models

class ServiceType(models.Model):
    name = models.CharField(max_length=256)

    class Meta:
        app_label = 'waterhole'
