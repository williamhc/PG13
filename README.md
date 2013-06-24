Group 4 - Puzzle Games 2013 (PG13)
-------------------------------------------------------------------------------
#### Paymahn M., Eric H., Will H-C., Lauren S.

**Version 2.00**

**Iteration 2:** 06/25/2013

**Project state:** stable

Project description
-------------------------------------------------------------------------------
PG13 is a social puzzle making and playing software. In this iteration you, our users, can create a puzzle by clicking the create button. Currently, we only have cryptograms. The preview of your puzzle is automatically generated as you type your plain text in. 

"Save this Puzzle" will allow you to store the puzzle you made in a database. You can also click the "Find and Play" button, which will display all the puzzles that have been created (including yours, if you saved it). You have a few dummy puzzles that populate this screen right now from our database.

The "Connect" button has been made uninteractable for this iteration as it is not fully implemented.

Enjoy the program :)

Getting the code
-------------------------------------------------------------------------------
The repository is a git repository under the root directory of an Amazon EC2 instance. The authorized user is ubuntu. The SSH key 

required will be included in the .zip - `comp3350.pem`.

To clone the repo, use the ssh (via `chmod` and `ssh-add`) key and then:
`git clone ubuntu@ec2-54-245-216-243.us-west-2.compute.amazonaws.com:PG13.git`

The project can also simply be downloaded from Dropbox:
`https://www.dropbox.com/s/gnrtwoen3d4b995/PG13.zip`

Getting up and running
-------------------------------------------------------------------------------
**Note:** you must have javac and java in your PATH

 1. Run 'Compile.bat'.
 2. Run 'Run.bat'.
 3. To run tests, run 'RunAllTests.bat'
 4. To restore the database to its original state, run 'RestoreDB.bat'. This bat file can be found in the 'database' folder

Project File Contents
-------------------------------------------------------------------------------
Our project is contained in the package, pg13. Our main function can be found in the pg13.app package. The file our main is contained in is PG13.java. pg13.presentation contains the GUI components of our project It also contained 2 java files that list String and Message Constants used throughout the GUI. pg13.business contains all the data processing and logic. pg13.persistence contains the controller and the SQL implementation file for the persistant database. pg13.models is our domain specific data objects, puzzles and users. We also have test package that contains all of our JUNIT tests. tests.models contains the files that thoroughly test our data objects. tests.business tests all the data processing and logic. tests.persistance tests the persistance interface using the Stub database (StubDB.java is in this package).

Additional Notes
-------------------------------------------------------------------------------
Included in our electronic handin are the following:

 - our source code
 - this readme file
 - a copy of our log file
 - a copy of our user stories implemented during this iteration
 - an excel spreadsheet file outlining our developer tasks for this iteration their current state, and estimated times
 - AllTests.txt that contains the output of our RunTests script
