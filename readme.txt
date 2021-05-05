The class design


NOTE:- A user may find some features not correctly implemented as per the assignment’s guidelines but they soon will be corrected as the progress is still incomplete. The main aim was to be able to implement a working prototype.

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




