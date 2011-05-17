import simplejson, urllib2

API_TOKEN = "AIzaSyBAsRqfCX7h7YXyUSdw61Z3LTHc-KT1EZY"
BASE_URL = "https://maps.googleapis.com/maps/api/place/search/json?sensor=true&types=bar&radius=5000"
#location=-33.8670522,151.1957362&radius=500&types=bar&name=harbour&sensor=false&key=


def get_places(lat, long):
    url = ("%s&key=%s&location=%s,%s" % (BASE_URL,
                                         API_TOKEN,
                                         lat,
                                         long))
    print url
    f = urllib2.urlopen(url)

    raw_data = f.read()

    data = simplejson.loads(raw_data)

    if data["status"] != 'OK':
        return data["status"]
    else:
        return data["results"]




