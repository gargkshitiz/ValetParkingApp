Design and assumptions
==========================
Resources in the system have been identified and translated to java classes directly to design this valet parking system. Composition has been used extensively for enabling re-usability, de-coupled classes, and easy unit-testability. No. of Unit test cases give us a net to rapidly refactor, add frameworks and extend the application. Spring, Hibernate, Maven have not been used as the intention here is to just show the design aspects and to deliver the prod ready code in a very quick time. Database/Dao layer is not present but application's entities could be directly mapped to DB tables. Because of composition's heavy usage, it would be a matter of couple of hours to convert it into a full fledged spring/hibernate based application. At last, Concurrency aspect has been left out to keep things simple at this point of time. A simple Runnable 'ParkTheCarTask' is present however just to show the intention of not keeping the customer waiting for the valet token after giving the car to the ValetParkingManager.

Packages/classes under src/main/java folder(Test cases are in src/test/java)
================================================================================

"com.kshitiz.parking.entities"

	1. Customer: Represents a customer. It 'has-a' Vehicle, ValetParkingManager and a ValetToken.
	2. Key: Represents a Vehicle Key. It 'has-a' chipNumber and its color.
	3. ParkingLot: Represents a ParkingLot. It 'has-a' list of parkingSpots, and provides a suitable parking spot for the given Vehicle (provided the parking spot is free and given vehicle can fit in that parking spot).
	4. ParkingSpot: Represents a ParkingSpot. It 'has-a' size, number and the parked vehicle. A vehicle can be parked in this parking spot using its 'park' behaviour. It exposes getVehicle(Key key) and a isFree() method as well.
	5. Size: Represents a Size. It is an enum containing SMALL(1), MEDIUM(2) and LARGE(3) where numbers in bracket implies rank of the size. 
	6. ValetPerson: Represents a ValetPerson. It takes commands from ValetParkingManager to park and get a vehicle and executes them unconditionally. It exposes its own's availability using its isWorking() method.
	7. ValetToken: Represents a ValetToken. It 'has-a' number and its isAssigned property.
	8. Vehicle: Represents a Vehicle. It 'has-a' size, registrationNumber and its key.
	   
"com.kshitiz.parking.management"

	1. ValetParkingManager: Provides an interface to park and get a vehicle. It collaborates with ValetPersonManager and ValetTokenManager for managing ValetPersons and ValetTokens. Its park method throws ValetTokenExhaustedException, ValetPersonUnavailableException and ParkingSpotUnavailableException. Its getVehicle method throws TheftDetectedException, ValetPersonUnavailableException and IllegalValetTokenException.
	2. ValetPersonManager: Provides an interface to get an available ValetPerson. Throws ValetPersonUnavailableException.
	3. ValetTokenManager: Provides an interface to get an available ValetToken. Throws ValetTokenExhaustedException.

"com.kshitiz.parking.management.impl"

	1. ValetParkingManagerImpl, ValetPersonManagerImpl and ValetTokenManagerImpl classes.
	   
"com.kshitiz.parking.task"
	
	1. ParkTheCarTask: A Runnable used to quickly assign a valet token to a customer after taking his/her vehicle for parking. It helps ValetParkingManager to 'free-up' the customer by assigning a valet token as soon as possible.

"com.kshitiz.parking.exception"

	1. It contains all the exceptions which could be raised at various points within this application.
	

Unit Testing 
==============
TestNG library has been used to create and run unit tests. Mockito has been used to mock objects and their behaviours to unit-test all possible scenarios. There are 33 test cases in total (including an integration test) which test various aspects of the app.


Building the application 
=========================
An ant file titled 'build.xml' containing a target called 'build' has been provided. This 'build' target cleans up and recreates all directories, compiles the code, runs unit tests and finally creates app's distibutable jar.
