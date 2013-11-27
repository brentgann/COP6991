readme.txt

COP6991 Web Data Mining Project

Brent Gann
Christian Smith

To run this program, open up your Command Prompt on Windows or your Terminal on MacOSX/Linux. Navigate to the directory in which holds WebDataMiningProject.java, WebDataMiningProject.class, Calculate.java, Calculate.class, and sessions.txt. Then, type in "java WebDataMiningProject". The program will open up and ask you to specify a file. You can enter in the name of any file in that directory like the provided sessions.txt. The file does need to meet the file specifications located at the bottom of this readme. Once you have specified the file you can enter in a menu item 1 - 4.

On program opening: It will ask you to enter in a filename. This can be done using the provided "sessions.txt" or using a new file meeting the file specifications at the bottom of this page.

Menu item 1: "1" will be used to calculate the support of an itemset in the transactions within the file that was specified. Upon selecting item one, the program will ask for an itemset to be added one item at a time. Enter in each item followed by <ENTER> keystroke. When finished entering items enter in "#" and <ENTER> to finish and the support will be caulculated.

Menu item 2: "2" will be used to calculate the confidence of a rule X -> Y. Upon selecting item two, the program will ask for the itemset X first and will be entered one item at a time. Enter in each item followed by <ENTER> keystroke. When finished entering items enter in "#" and <ENTER> to finish and then the program will ask for itemset Y. Enter in each item followed by <ENTER> keystroke. When finished entering items enter in "#" and <ENTER> to finish and the confidence will be caulculated.

Menu item 3: "3" will be used to calculate the lift (improvement) of a rule X -> Y. Upon selecting item two, the program will ask for the itemset X first and will be entered one item at a time. Enter in each item followed by <ENTER> keystroke. When finished entering items enter in "#" and <ENTER> to finish and then the program will ask for itemset Y. Enter in each item followed by <ENTER> keystroke. When finished entering items enter in "#" and <ENTER> to finish and the lift (improvement) will be caulculated.

Menu item 4: "4" will be used to print out the set of unique items in all of the transactions presorted by the Java Collections library.

Menu item 5: "5" will be used to exit the program.


File Specifications: The file should be a list of comma delimited transactions. Each transaction should be a single line of information and start with the transaction id such that it appears as "transactionId, item1, item2, item3, ... , item(n-1), item(n)" for n items in the transaction. It should then read through the entire file, line by line, adding transactions for each step. It is a standard csv without the header, there should only be a list of transactions and their items.