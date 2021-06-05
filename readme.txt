Package Info

Package:main

The superclass Person has been inherited by employee as every employee is a person and is also inherited by resident for the same reason.

Manager, Nurse, Doctor are employees which carry certain features such as a unique ID.

A patient belongs to the resident class as he/she is not an employee.

A manager is the one supervising all of them with the power to add/modify any person. He can also view them unlike a doctor and nurse who can only view patients.

Similarly the doctor and the nurse have their own functionality in their respective classes.


Package:CustomExceptions

This contains the exceptions or the validations required in input. A name , for example, can be inputted as an integer is where a string is expected, so to handle these input type validations and some other exceptions the class InputValidation was created along with other exceptions.



Package:CommonSnippets

There are some pieces of code which are repeated again and again are defined in a class to promote code reusability. This class contains the code to input integers, strings,long , etc. With some basic validation to prevent invalid input.

Also, a class named as DisplayMenu is present to take all the menu options to be displayed at a single place in order to make the code cleaner and nicer. It would also help to edit the menu options easily.


Package:Junit Test

As the name suggests, this package contains the tests in 2 different files, the ResidentCareTest and the InputTests – the first one contains the tests for all functionalities present in the main program and the second test file is designed specifically to test various input types.




Package:Ward

This one contains the classes such as Ward, Room, Bed and hence as the names suggests, a package dedicated to the classes to operate wards, rooms, beds respectively.


Package application
Contains all GUI related files.

NOTE:- To run this masterpiece, you need to run the Main.java file in package application. I have used hsqldb version 2.6.0, java version 11.0.11 , sjavafx version 11.0.2 and JUnit 5.

Manager Credentials

Username 7730000
Password 1234

Doctor Credentials

Username 6830000
Password 1234

Nurse Credentials

Username 7830000
Password 1234

Theses are some of the employees who can be accessible for testing the programs , others will be created and can log in under the business rules provided by the assignment.






