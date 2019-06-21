# Food trucks
[link](http://104.248.43.78/index.htm)

## What it does
The web page displays the food trucks in San Francisco on a map, the data is
from
[DataSF](https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat).
It only displays the trucks within the map bounds. Each time the map is moved or
zoomed it sends a new request to the server and update the points. If a food
truck is clicked there will be a pop up with the food truck name and food items.
On the upper right there is an input field where it is possible to search for
food trucks all over the map, when typing an auto completion box appears beneath
and when clicked on goes to that food truck and opens the pop-up.

## How it does it
### Front-end
The front-end uses the following libraries:
* [leaflet.js](https://leafletjs.com/)  is used to display the map
* [jQuery/jQuery UI](https://jquery.com/) is used for autocompletion
* [Toastify](https://apvarun.github.io/toastify-js/) is used for notifying the user

Every time the map is moved or there is a zoom, the 'moveend' event is
triggered. When that event is triggered  the code reads the current bounds of
the map and sends a request to the server. Then the server returns a list of
points. 
If there is no food trucks in the current view, the server returns
status 1 and we notify the user that there are no food trucks in the
current view.
If there is more than maxDisplayed (this variable is defined in the config)
points, the server returns status 2 meaning too many points. No food trucks are
loaded and the user gets a notification about it.

### Back-end
The back-end is implemented in Java 1.8, and uses the following libraries:
* [jUnit](https://junit.org/) - is used for testing 
* [Spring Boot](https://spring.io/) - as webserver (it uses Tomcat)
* [GSON](https://github.com/google/gson) - is used to parse the configuration file
* [JsonPath](https://github.com/json-path/JsonPath) - is used to make integration tests more readable

When the server is started we create
a context that holds the configuration which we load from a file and we create
two data structures as well to put on the context. The two data structures, one
for auto completion and one for map view search, are created by calling the
data layer object DataLayer which retrieves the data from the API and gives it to
the two data structures creating them.
When we call our service we hit the presentation layer which calls the business
layer that prepares the data for the data structures and calls them and returns
the result to the presentation layer which returns it to the user

There are three kinds of objects to pass data around:
* VMData - used for data coming from the SF-data API
* DTO - used internally for storing the data and doing logic on it.
* VM - used for sending data to the user

Whenever an error is thrown  with the (except TooManyElementsException which is
used for communicating with the presentation layer) we log the error message and
throws it onward.  In the presentation layer we catch all exception returning a
JSON with status 100 meaning something went wrong internally


## Testing
The main logic is extracted into the two classes SimpleAutocompletion.java and
SimpleRectangleSearch.java which makes them easy to test. The testing of
SimpleAutocompletion was done using Test-driven development

Then we have an integration test file which boots the application with faked
data and mainly tests to see if the correct status is returned

## Building the project
Just clone the repository and open the folder in IntelliJ IDEA. Note that you
need to create a folder called logs in the root of the project

A Jar file can be build using `mvn package -DSkipTests`


## Missing things
* Integration tests only work when out commenting @configuration on line 11 in
  the AppConfig.java class. The reason for this is that the Integration.java
  class was not able to find the main file, so I had to include it using
  @Import(main.java). But then spring can't figure out which @configuration
  class to use and exits with an error


## Things that could be looked at
* Do more tests. JavaScript tests are missing and we should also test parsing strings from SF-data
* Make sure the correct headers are sent in the http calls.
* Do different kind of logging
* Add a database back-end
* Make sure that the algorithm for filtering coordinates works around 180/-180
* There might be a race condition if you move the map rapidly, an earlier request might arrive later than the current and you end up with old data.
* FoodtruckDTO has missing fields
* Log JavaScript errors (using [onerror](https://developer.mozilla.org/en-US/docs/Web/API/GlobalEventHandlers/onerror))
* Investigate if a JAR-file is the best way to ship an app
* Might be better to use GSON.parse rather than Gson.fromJSON
* Close connections properly
* Use clustering of markers on map [link](https://github.com/Leaflet/Leaflet.markercluster)
* Minifying JavaScript and CSS files

## Technical choices
* I chose to do the back-end in Java even though it is some years since I
  wrote in Java the last time, it is the language that I am most familiar with. 
* I chose leaflet since I have read about some problems regarding API-keys for Google maps
* The design choice to put the data structures on the context was made because
  then you are able to make data structures that are more effective without
  rebuilding them every time. E.g. you could make a k-d tree instead of simpleRectangleSearch.java

## Linkedin profile
[link](https://www.linkedin.com/in/nickbakkegaard/)
