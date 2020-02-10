Application for football playing fields reservations is an app that is developed for lowering mistakes in communication between
user and the people that are in charge for appointments. 
First screen is home page that contains informations about the pitch(location, gallery with slideshow, contact informations and prices).
From home page you can navigate through the app. On the 'prijavi' button, you get login page. From there, if you are already user that
has account, you can login and then your session will stay all the way until you logout. In case that you don't have account,
there is a navigating link for registration. When you click on it, that layout contains the informations that you need to insert in 
order to be registered user of the application. Those fields are username, e-mail and password. From there you will get Toast notification
whether you are sucessfuly logged in, or there are some mistakes with the registration (case that there is a field missing, that the username
already exists in a database). Once you are sucessfuly logged in, you can then navigate to login page where you insert your acreditations.
If the username and password are right, you get logged in screen that will stay there and you will be logged in when the app closes and then open again.
From there you can navigate back to home page. This is done by using the sqlite android database, with the database named login.db and table users.
Once you have finished with this part(getting you acredited so you can further reservate on your account), you can proced to make reservation.
On button 'rezervacija termina' you get input fields that are required in order to get your reservation right. There is a small info on the bottom
of the screen that says 'time needs to be inputed 00-24 format (example 15)'. There is a description input field where you put the value of 
either afternoon reservation or night reservation. (Afternoon include 14-17, this field is required because there is a price difference 
depending on the hour of your reservation). Other input is time, with time of the reservation that you want. 
Once you sucessfuly finish this(the time needs to be non taken - if it is taken then you get toast that it is unavailable
message that disables the input in the database). Last input is username of the user that is making reservation.
With sucessfuly finished create of reservation, it will be then added on page 'mojerezervacije'. You can make more reservations.
Once the day is over, or you want to delete your reservation, there is a button 'izbrisi sve rezervacije' that deletes all the reservations 
from the database.
Main goal of this application is to make easier approach and reduce the human factor error, which is desired in these days of tehnology development.