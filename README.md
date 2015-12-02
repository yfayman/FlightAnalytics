# FlightAnalytics
Welcome to my repository for FlightAnalytics. This is a tool that aggregates flight price data
over time and generates a trend plot. To maintain privacy, each request is map to a user. The landing 
page is below. To do anything of note a user needs to register/log in. The registration requires an
email address which will be used to send alerts in future iterations of this program.
![Home Page](/..//screenshots/screenshots/fa1.png?raw=true "Landing Page")

# Make a Request
A logged in user will first make a request. This page gets a list of IATA codes from the back-end. You need
to have a database built in order for this to work.Create a MySQL database and execute the schemda in the 
main folder. After linking the new database to the datasource in spring configuration, you need to run the 
following program to build the database. https://github.com/yfayman/FlightAnalytics/tree/master/AirportDataBaseBuilder
You need to configure the Dao class to point to your database as well.
![Make a Request](/..//screenshots/screenshots/fa2.png?raw=true "Make Request")

#View Current Requests
You can view requests associated with your login. Raw data is passed to the client via JSON. Data is then processed
in success function of the AJAX call is chart.js . Feel free to add whatever statistical methods you would like. 
I kept it simple for now (min, max , average of each query)
![View Request](/..//screenshots/screenshots/fa3.png?raw=true "View Request")

#Getting Set Up
Clone my repo, set up a database schema that mimics mine, link it to your project's datasource. You will need to set up
a working query tool(see instructions below)

#Setting up Query Tool
You can create your own implementation by implementing the FlightQuery interface and adding a spring bean for your implementation.
The Request class manages which FlightQuery bean is autowired at runtime. You can use QPXFlightQuery, which uses Google's QPX Express API,
the current implementation of FlightQuery I am using. You will need to register an API key and insert it into QPXFlightQuery. 
QPX Express only allows 50 free queries a day. After that, you will be charged 3 cents a query.
