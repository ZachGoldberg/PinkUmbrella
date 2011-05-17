from django.db import models
from pinkumbrella.waterhole.models.servicetype import ServiceType

class Service(models.Model):
    sid = models.CharField(max_length=256)
    token = models.CharField(max_length=1024)
    type = models.ForeignKey ( ServiceType )

    class Meta:
        app_label = 'waterhole'

    @classmethod
    def get_or_create(clazz, typename, sid, token):
        type = None
        service = None
        try:
            type = ServiceType.objects.get(name=typename)                
        except ServiceType.DoesNotExist:
            type = ServiceType(name=typename)
            type.save()
        
        try:
            service = Service.objects.get_or_create(sid=sid, type=type, token=token)[0]
        except Service.DoesNotExist:
            return None

        return service
