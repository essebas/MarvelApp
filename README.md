# Aplicación Android  - Consuming [Marvel Developers API](https://developer.marvel.com/) 
***v.1.0.0-betha-release***

## Description
In the following application a user can see and consult all the marvel superheroes, as well as being able to see the details of each one. 

### Gathering information - UML Client Logic

![Inicio de la aplicacion](https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/MarvelUseCase.png)

### Project Architecture

![Inicio de la aplicacion](https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/MarvelArchitecture.png)

## Technologies used
- Retrofit (v2.9.0)
- Gson (v2.9.0)
- Coroutines (v1.5.2)
- ViewModel (v2.3.1)
- LiveData (v2.3.1)
- Lifecycle (v2.3.1)
- Hilt (v2.38.1)
- Navigation (v2.3.5)
- Room Persistence (v2.3.0)

### Others Libraries
- Material Design
- Glide
- MockWebServer
- Truth

###### *For security reasons the API KEY was not exposed in the codes, this was managed through the Grandle Properties file to avoid its disclosure. To make the application work, put your Marvel API Keys in gradle properties in the following build configs names* #####
- ###### *PUBLIC_KEY = "YOUR_PUBLIC_KEY_KERE"* #####
- ###### *PRIVATE_KEY = "YOUR_PRIVATE_KEY_KERE"* #####

## App overview

We will give a quick view to know the functionalities of the application 

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Principal.jpg" width="200">

### Home (Characters RecyclerView Fragment)
This is where we can see all the superheroes provided by the Marvel API. Each super hero will be displayed in a cardview and all of these will be grouped and displayed within a recyclerview

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Home1.jpg" width="200">

### Home (Technical Information)
If the application is downloaded for the first time or if data is being loaded from the database, it will always load 40 characters. The user will be able to obtain more results if he scrolls to the end of the list, there will appear a loading and the respective useCase to obtain more characters to bring from DB or from API more characters. Something interesting is that all data sources are synchronized so network consumption is optimized by sacrificing the size of the application, this means that as the user requires more characters, these will be saved in the database . 

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/HomeLoad2.jpg" width="200">

### Home (Data Sources)
There are 3 types of data sources, the main one is the API, which is where we can initially obtain the data. The second is the bd, built through the room library, this will be fed into the registry as the app downloads information in batches of 40 characters. Finally we will have a cache storage to show data quickly every time the user resumes the application. 

--insert image

### Search Function (SearchView)
The application has a searchview at the top of the recyclerview, this can be used for the user to search for a specific superhero name (eg Wolverine) or search for the first initials of a group of superheroes (eg Spi) 

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Search1.jpg" width="200">

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Search3.jpg" width="200">

### Search Function (Data Sources)
For searches we will have 2 types of data sources, the use of one or the other will depend on whether the user has an internet connection. Therefore, if the user has an internet connection, the searches will come from the API. However, if the user does not have a network connection, a search will be made with the data that the database has so far. 

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Search4.jpg" width="200">

### Search Function (Faster Update RecyclerView)
Every time the user interacts with the Searchview, a request will be sent to obtain the data as the user's characters change. However, if the data is called in this way, the application would not be efficient, and it consumes resources unnecessarily, so there is a delay of 3 seconds that the user has to wait for the search to be sent and the results are obtained. API or DB data as the case may be.

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Search5.jpg" width="200">

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Search6.jpg" width="200">


### Search Function (Complete Search)
This search is carried out when the user needs something much more specific. This is launched when the user enters a complete keyword and hits the enter key on the keyboard.

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/SearchTotal2.jpg" width="200">

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/SearchTotal1.jpg" width="200">

### Character Deatil (WebView)
The user will be able to see the details of each superhero directly from the Marvel page through a webview and a url that each character brings from the API.

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/CharacterDetail.jpg" width="200">


### Offline Home (With Room Persistence)

The application can also continue to function even if the user is not connected to the internet. This is thanks to the fact that the app stores the data of the characters in each consumption to the API, to avoid duplication in the data, 2 counters were incorporated, one for the API and the other for the DB. These counters are stored on a private SharedPreferences of the app to always have the position of each datasource. A simple example of how it works is the following:
Suppose that the user has seen 160 (4 consumptions to the MARVEL API), this data is saved in SQLite immediately we have the answer so there is 160 data in the database.
The application always returns the data as follows:

Cache -> Local Data (Room) -> Remote (REST API)

The cache only works in case the user places the application in the background and returns to it a few minutes later. If the app is destroyed, as there is no data in the cache, it will look for resources from the local DB. These will be provided the same as what the API does, at 40 characters per "page", this to optimize performance and show you the data that the user will actually see. If at some point all the data stored in the database is shown, it will be used to consume the API, if the application does not have an internet, an error message will be displayed.

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/OfflineHome.jpg" width="200">

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/OfflineHome2.jpg" width="200">

### Offline Search (With Room Persistence)

The user can also do offline searches, but these will depend on the data that the user has stored in SQLite.

<img src="https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/OfflineSearch.jpg" width="200">
