from itty import *
import urllib2
import json


def sendPOST(url):
    """
    This method is used for:
    - sending POST request to summarizer
    """
    request = urllib2.Request(url, json.dumps(data),
                            headers={"Accept" : "application/json",
                                     "Content-Type":"application/json"})
    #request.add_header("Authorization", "Bearer "+bearer) # trying to disable authorization right now
    contents = urllib2.urlopen(request).read()
    return contents

def sendSparkGET(url):
    """
    This method is used for:
        -retrieving message text, when the webhook is triggered with a message
        -Getting the username of the person who posted the message if a command is recognized
    """
    request = urllib2.Request(url,
                            headers={"Accept" : "application/json",
                                     "Content-Type":"application/json"})
    request.add_header("Authorization", "Bearer "+bearer)
    contents = urllib2.urlopen(request).read()
    return contents
   
def sendSparkPOST(url, data):
    """
    This method is used for:
        -posting a message to the Spark room to confirm that a command was received and processed
    """
    request = urllib2.Request(url, json.dumps(data),
                            headers={"Accept" : "application/json",
                                     "Content-Type":"application/json"})
    request.add_header("Authorization", "Bearer "+bearer)
    contents = urllib2.urlopen(request).read()
    return contents
   


@post('/')
def index(request):
    """
    When messages come in from the webhook, they are processed here.  The message text needs to be retrieved from Spark,
    using the sendSparkGet() function.  The message text is parsed.  If an expected command is found in the message,
    further actions are taken. i.e.
    /batman    - replies to the room with text
    /batcave   - echoes the incoming text to the room
    /batsignal - replies to the room with an image
    """
    webhook = json.loads(request.body)
    print webhook['data']['id']
    result = sendSparkGET('https://api.ciscospark.com/v1/messages/{0}'.format(webhook['data']['id']))
    result = json.loads(result)
    msg = None
    if webhook['data']['personEmail'] != bot_email:
        in_message = result.get('text', '').lower()
        in_message = in_message.replace(bot_name, '')

        if return_code in in_message:
            msg = "The message is summarized as follows: "
        else:
            # TODO: call the summarizer API - update "http://"                                                                                                                                              
            #sendPOST("http://SOMEMSG", {"link": in_message})                                                                                                                                           
            msg = "Waiting for message"

        if msg != None:
            print msg
            sendSparkPOST("https://api.ciscospark.com/v1/messages", {"roomId": webhook['data']['roomId'], "text": msg})

    return "true"


####CHANGE THESE VALUES#####
return_code = "0lL39yQPeUblzUbUOfKomz6t51s22cLX!$Ft"
bot_email = "simpl@sparkbot.io"
bot_name = "simpl"
bearer = "NjNhNWY1YjQtZjhhNy00MDM1LTkxYjEtNjI3NTMwYzI1Y2FlNWYyMzBlNmEtNGNk"
bat_signal  = "https://upload.wikimedia.org/wikipedia/en/c/c6/Bat-signal_1989_film.jpg"
run_itty(server='wsgiref', host='localhost', port=10010)