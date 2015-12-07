For build this project without IDE need to use command mvn package and then run jar file (for more information look http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-first-application-executable-jar)

All requests go to localhost:8080/..
Every request begin from {login} - login of the user. It's need to very simple security.
DB in memory H2.
In DB stubs some data:
1. Users: 'admin' and 'user'. They have different roles Admin can do all except vote, user can vote and get daily-menu info.
All Path:
1. daily menu
	a. GET http://localhost:8080/{login}/daily-menu - get daily menus of all restaurants
	b. GET http://localhost:8080/{login}/daily-menu/{restaurantId} - get daily menu of restaurant with id
	c. POST and PUT http://localhost:8080/{login}/daily-menu - add or change new menu and delete previouse on thi day if it was.
		for example: { "restaurant" : {"id" : "1"}, "dishes" : []} date will be set auto.
	d. DELETE http://localhost:8080/{login}/daily-menu/{restaurantId} - delete daily menu for this restaurant.
2. restaurant
	a. POST and PUT http://localhost:8080/{login}/restaurant - add or change new restaurant
		for example { "id" : "1", "name": "test"}
	b. GET http://localhost:8080/{login}/restaurant - get all restaurant
	c. GET http://localhost:8080/{login}/restaurant/{restaurantId} - get restaurant with id
	c. DELETE http://localhost:8080/{login}/restaurant/{restaurantId} - delete restaurant with id
3. user
	a. POST and PUT http://localhost:8080/{login}/user - add or change new user
		for example { "login" : "user2", "name": "User2", "haveAdminRole" : false}
	b. GET http://localhost:8080/{login}/user - get all users
	c. GET http://localhost:8080/{login}/user/{userLogin} - get user with login
	c. DELETE http://localhost:8080/{login}/user/{userLogin} - delete user with login
4. vote
	a. POST and PUT http://localhost:8080/{login}/vote/{restaurantId} - add or change vote 
		!!! can be changed onlybewfore set other in application.properties timeover.hour!!! If need for test just change value.
	b. GET http://localhost:8080/{login}/vote - get user vote with login
	c. DELETE http://localhost:8080/{login}/vote/ - delete user with login
5. result
	a. GET http://localhost:8080/{login}/result - return results of vote for this day.
	
DB is place in http://localhost:8080/h2-console 
	login sa
	password sa
	
Logging is made to console only.
