from pinkumbrella.waterhole.models import Service, User

def CreateOrGetUser(data):
    service = Service.get_or_create(typename=data['servicetype'], sid=data['serviceid'], token=data['servicetoken'])
    user = User.objects.get_or_create(uid=data["uid"], service=service)[0]
    return user
