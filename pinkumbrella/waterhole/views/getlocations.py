import simplejson

from django.http import HttpResponse
from pinkumbrella.waterhole.utils import CreateOrGetUser
from pinkumbrella.waterhole.places import get_places

def getlocations(request):
#    if request.method is not "POST":
 #       return HttpResponse("You can only POST to this URL!")

    # for testing
    request.POST = request.GET

    required_fields = ["uid", "servicetype", "serviceid", "servicetoken", "lat", "long"]
    request_data = {}
    for field in required_fields:
        if field not in request.POST:
            return HttpResponse("Missing field in request: %s" % field)
        request_data[field] = request.POST[field]

    us = CreateOrGetUser(request_data)
    
    places = get_places(request_data["lat"], request_data["long"])

    return HttpResponse(simplejson.dumps((us.id, places)))
