Please read this outline text document for detail of implementation, it could be necessary to understand all functionality.

All required tasks were completed, plus the bonus. Assumptions are also listed in the word document, as well as location and
details of unit tests.

Thank you and have a great week!

GitHub Link: https://github.com/AlexAWyatt/Deliverable_3.git

Outline of Implementation

------5 Unit test cases relevant to this deliverable.-------

Similar to Deliverable 2, the details of our implementations makes traditional unit testing as we learnt in class, difficult. We worked
to test all methods that were possible using traditional unit tests, and included addition unit tests through the virtual device. For deliverable
3 our test cases were as follows:

ClassDatabaseTest class
- 3 cases for checkFull() method which tests whether a class has reached its capacity and therefore can have no further enrollments

EnrollClassTest
- 1 case testing if new 'enrollment' table allows insertion of new entries using method implemented throughout deliverable 3

SearchClassTest
- 1 case testing ability to query instructorClasses table testing whether retrieved data is as expected when retrieved

UnenrollClassTest
- 1 case testing whether deleting entries as implemented in unenroll() method effectively deletes entries

InstructorEditClassTest
- 7 cases testing checkConflict() method, testing different class times relative to eachother to ensure accurate conflict detection 
when editing classes (newly implemented method for deliverable 3)

MemberHomeActivityTest
- 7 cases testing checkTime() method, similar to checkConflict tests and method, testing doifferent class times relative to eachother
to accurately identify conflicts when members enroll into new classes

TimeConversionTest
- 5 cases testing timeConversion() method in MainActivity, implemented in deliverable 3 to convert string start-times for classes and lengths of classes to
int's, allowing checking for conflicts when members enroll in classes.



------Member can view all scheduled fitness classes-------

Implemented in the same way as deliverable two. Press the "View All Classes" button and the Search Results area shows all classes.



------Search by Class Name of Day of the Week------

- Implemented the same as deliverable two, except for the difference where search is by class name or day of the week instead of class name or instructor name.
- Fill out relevant field -> Press Button corresponding to said field -> results appear in the "Search Results" section



-------Member can enroll into a fitness class------

After signing in, any class in the listview under search results (by default, after searching, at any point), if tapped on provides the option to "Enroll"
or to "Confirm". By clicking enroll, the application will try to enroll the member in the class, succeeding or failing based on checked conflicts detailed later



------Member can see a list of all fitness classes they are enrolled in and can unenroll from a fitness class------

From the landing page following sign in, if a member clicks the button "VIEW ENROLLED CLASSES" a new page is opened with a list of all classes the user is 
presently enrolled in. By clicking one of these classes in the listview, a popup is shown which displays all detail of the class. Pressing the bottom left
button from this screen labelled "UNENROLL" unenrolls the member from the respective course, and removes the course from their list of all presently enrolled
courses.



------Time Conflicts and Full Class Capacity Prevent Enrollment------

These aspects are the conflicts that are automatically checked for when a member enrolls in a course. If either conflict is found, a message is shown using Toast
which explains the relevant conflict. For example, if the time conflicts the message "A time conflict occurred! Operation Failed." or if a class is already at
capacity "Class is Full! Operation Failed.". Additionally, if the member is already enrolled, the messsage "Already enrolled, cannot enroll again." appears.



------BONUS - Instructor can view a list of all enrolled gym members------

This was implemented within the Instructor Role abilities. An instructor signs in, then from the landing page clicks the button "MANAGE MY CLASSES" which opens a
new page with a listview of all the signed in instructors classes. By tapping on any class, the user is given the option to edit any of the class info, to
remove the class, or to view all enrolled members by clicking "VIEW ENROLLED". This opens a new page which shows a listview of all member currently enrolled in
the selected course. The previous page can then be returned to using the default android back button on the bottom shelf.
