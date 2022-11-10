# DisneyProject

This is the sample app I have created for Disney's take home evaluation. 
It is very barebones and not complex, simply grabs a Comic and its details from the service endpoint based on a provided ID (hardcoded right now). 
An activity written with Compose takes comic data from a backing ViewModel class which uses Retrofit to retrieve the data provided by the developer endpoint. 
By observing the LiveData object for the comic, the Composables can launch a loading spinner if the data is not available yet, and then recompose when the state of the LiveData has changed. 
Future state would involve fleshing out the Composable LazyColumn to create a list view to contain multiple comics provided by the comics endpoint, and then allowing users
to click on individual comics in that list, in turn calling the service with that ID to then display the detailed comic data. For extensibility, dependency injection such as Dagger would be added (not necessary with this small app). 
Testing needs to be fleshed out, as well as error handling and refining the data being returned from the service - I think I am using some incorrect data types for things like dates, as those are not being parsed correctly by GSON. 
The UI needs a LOT OF WORK.

Libraries used: 
* ViewModel
* LiveData
* Compose
* Retrofit
* Glide
