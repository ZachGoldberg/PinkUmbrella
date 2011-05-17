from django.db import models

class Service(models.Model):
    uid = models.CharField(max_length=256)
    token = models.CharField(max_length=1024)
    
    class Meta:
        app_label = 'waterhole'
