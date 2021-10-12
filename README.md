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

![Inicio de la aplicacion](https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Principal.jpg =250x)

### Home (Characters RecyclerView Fragment)
This is where we can see all the superheroes provided by the Marvel API. Each super hero will be displayed in a cardview and all of these will be grouped and displayed within a recyclerview

![Inicio de la aplicacion](https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/Home1.jpg)

### Home (Technical Information)
If the application is downloaded for the first time or if data is being loaded from the database, it will always load 40 characters. The user will be able to obtain more results if he scrolls to the end of the list, there will appear a loading and the respective useCase to obtain more characters to bring from DB or from API more characters. Something interesting is that all data sources are synchronized so network consumption is optimized by sacrificing the size of the application, this means that as the user requires more characters, these will be saved in the database . 

![Inicio de la aplicacion](https://github.com/essebas/MarvelApp/blob/master/README_IMAGES/HomeLoad2.jpg)

### Home (Data Sources)
There are 3 types of data sources, the main one is the API, which is where we can initially obtain the data. The second is the bd, built through the room library, this will be fed into the registry as the app downloads information in batches of 40 characters. Finally we will have a cache storage to show data quickly every time the user resumes the application. 

--insert image

### Search Function (SearchView)
The application has a searchview at the top of the recyclerview, this can be used for the user to search for a specific superhero name (eg Wolverine) or search for the first initials of a group of superheroes (eg Spi) 

--insert image

### Search Function (Data Sources)
For searches we will have 2 types of data sources, the use of one or the other will depend on whether the user has an internet connection. Therefore, if the user has an internet connection, the searches will come from the API. However, if the user does not have a network connection, a search will be made with the data that the database has so far. 

--insert image
