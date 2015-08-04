# **Requirements Document -- Alexander Milkov**

##1 User Requirements

###1.1 User Characteristics

The users will be college students whose technical backgrounds vary from little to no programming experience to being quite proficient in the field. Their personal hardware/software varies as well

###1.2 System's Functionality

The user would like to input their essay file (with a `.txt` file extension) and recieve as output the average words per sentence in their essay.

###1.3 User Interfaces

The user will evoke the application through a command line interface (CLI) using the `java` command. The application will take a filename as its primary input and subsequently output the average number of words per sentence in that file in a numerical format only. This result will be displayed on the screen to the end user. The application will accept the following two flags as arguments as well and the user can specify the flags simultaneously
* -d `<string>`
	* The delimiter flag will specify which characters the users would like to qualify as sentence terminators. The characters will be enclosed in double quotes. The double quote can be used as a sentence delimiter if it is escaped properly. If the flag is not specified the default set of delimiters simply be: "."

* -l `<integer>`
	* The length flag will specify the mininum number of characters in a word for it to be counted in determining average sentence length. In other words, if the user inputs 4 then all words of length 4 and above will be counted as words by the application. If this flag is not specified, the default will be 3

If there is an exception or error along the way, the user will be notified of this via a friendly output to the screen in the following format:

`Uh-oh: <detailed description of what went wrong and how the user can fix it>`

##2 System Requirements

###2.1 Functional Requirements

* The user should be able to provide a filepath to the file they would like to be analyzed 
* The system should output the average sentence length rounded down to the nearest integer
* The user should be able to specify delimiters with the -d flag
* The user should be able to specify a lower bound on word length via the -l flag
* The system should be able to handle missing or invalid arguments and procure a corresponding error message to the end user

###2.2 Non-Functional Requirements

* The system must be written off of a vanilla installation of java 1.6
* The system must be executable from the command line
* The system must compile on the command line using the `javac` command without any additional options
* The user may specify only one target file per application invocation
* The system should be able to process files at a reasonable speed
* The system may only have read capabilities with regard to file processing

