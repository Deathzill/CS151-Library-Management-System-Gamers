# Library Management System - Team 3 - Gamers

John Phan, Nick Valencia, Munkh-Erdene Khuderbaatar

# Proposal

## Overview
We have developed a comprehensive library management system that addresses key needs in user authentication, book management, and dynamic data handling. Our solution emphasizes ease of use, security, and effective data management, suitable for both patrons and librarians.

**Nick:**
- Contributed to the original Read Me File
- Created the class diagram

**John:**
- Created GitHub Repository
- Contributed to the original Read Me File
- Created the sequence diagram

**Munkh:**
- Created the state diagram
- Created the use case diagram

**Presentation:**

**Nick:**
Presented the general application including some of the functionalities such as the search menu and add/remove book features. Also provided a quick and general overview of some of the inner workings of the application and planned features.

**John:**
Presented the general process of a user’s account creation and sign-up in addition to the different accounts that are able to be created, whether that was a patron or a librarian. Also presented the general exception handling that was implemented and the layout managers used.

**Munkh:**
Presented features and changes that were planned but not yet fully implemented or completed in the main project, but were already coded.


# Code contributions

**John:**
- Created Skeleton Code
- Created the entire initial UI layout
- Created design for UI
- Created edge case logic for UI panel sizing
- Created Session logic for logged-in user
- Created the ability to add books, checkout, and return books
- Created the ability to sign up and sign in as different types of users
- Added ability for users to return books
- Added ability for librarians to add books
- Added logic to check out books
- Added logic to reject checkout based on overdue books
- Added overdue book logic
- Added menu to display user info
- Added a search table where users can search for books
- Created sign-up logic
- Created logic to generate a user ID
- Created the ability to search for specific books based on all of their fields
- Created initial back-end logic for the Patron class
- Created initial back-end logic for Tables (before `JSON`)
- Created initial back-end logic for the User

**Nick:** 
- Wrote initial read me within the proposal folder
- Put together the final readME for the project with help from John
- Integrated JUnit4 and Unit tests to the back end
- Created `.JAR` file for the program and instructions
- Converted program to a MAVEN project with a `pom.xml` file
- Wrote a script within `pom.xml` to create the project environment
- Converted project to `JSON` format for data persistence after closing the program
- Added comments to code for easy-to-follow logic
- Created Delete account logic
- Rehashed return book logic to allow users to return individual books
- Added feature for librarians to remove books
- Created default database initialization so the database is not initially empty
- Created Default users
- Modified all classes to support loading and saving to JSON
- Fleshed out back-end logic for User, Tables, Librarian, and Patron

**Munkh:** 
- Handled class exceptions for email fields
- Handled exceptions for password requirements

# Problems/Issues
To manage books, one must physically search for books in our library. To make finding a book efficient, we will develop a program that will automatically sort and categorize books to streamline this process.

# Assumptions:
We will assume logic similar to the ARS in SCU that can retrieve books with a machine that drops the books off at a delivery window. This

# Operating Environments

This system can be used at a library kiosk to streamline the usuer experience. The library staff can also use this system so that they do not have to manually keep track of the books in their database

## Description
This Java project is a Library Management System designed to manage book lending and user accounts in a library setting. It features a user-friendly interface for both patrons and librarians, enabling tasks like book checkouts, returns, and account management.

# Diagrams
[LibraryStateDiagram](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/blob/main/diagrams/LibraryStateDiagram.png) 
- This file contains a state diagram of our project denoting the behavior of our system with users.
[LibraryClassDiagram](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/blob/main/diagrams/LibraryClassDiagram.png)
- This file contains a class diagram which, will provide a model structure of our programming code.
[LibrarySequenceDiagram](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/blob/main/diagrams/LibrarySequenceDiagram.png)
- This file contains a sequence diagram that provides an explanation as to how operations are carried out by different objects.
[LibraryUseCaseDiagram](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/blob/main/diagrams/LibraryUseCaseDiagram.png)
- This file contains a use case diagram that identifies the interactions of the system and its users. 

### Functionality
Our system provides several functionalities, ensuring a robust and user-friendly experience:

- **User Authentication**: We enable users to securely sign up or sign in. The system distinguishes between patrons and librarians, providing tailored functionalities for each.
- **Book Management**: Librarians are empowered to add or remove books, while patrons can check out and return books. This feature is supported by a user-friendly GUI.
- **Dynamic Data Handling**: We employ a JSON file for data persistence, enabling efficient retrieval and updating of user and book records.
- **Search and Filter**: Our search functionality allows users to quickly locate books using various filters, such as title, author, or ISBN.
- **Security and Validation**: We incorporate password and email validation to ensure the security of user data and the integrity of the system.

### Operations

#### Patrons
Patrons can perform a variety of operations in our system:

- **Sign Up/Sign In**: Users can create a new patron account or sign in to an existing one.
- **Search Books**: Patrons have the ability to search for books using a dedicated search screen.
- **Check Out Books**: Users can select and check out books, adhering to a limit of five books at a time.
- **Return Books**: Borrowed books can be returned using the return books feature.
- **Account Management**: Patrons can view their account details, log out, or delete their account.
-  **Search and Filter Books**: Patrons can use advanced search and filter capabilities to find books they need

#### Librarians
Librarians have access to enhanced functionalities, including:

- **Sign Up/Sign In**: Creation of new librarian accounts and signing in.
- **Add Books**: Librarians can add new books to the system by providing necessary details like title, author, and ISBN.
- **Remove Books**: This feature allows librarians to remove books from the library's collection.
- **Search and Filter Books**: Librarians can use advanced search and filter capabilities similar to patrons.
- **Account Management**: In addition to standard account management features, librarians have access to book management tools.

Our solution integrates various components like UI design, database management, and user-specific functionalities into a seamless library management experience. We focus on addressing the fundamental operations of library management and user interactions.


## Prerequisites
Before you begin, ensure you have met the following requirements:
- You have installed JDK 19, if you'd like to use a newer version you can modify the JDK in the Pom XML.
- You have a Java IDE (e.g., IntelliJ IDEA, Eclipse) installed.
- You have Maven installed for dependency management.

## Installation
Follow these steps to install the Library Management System:
1. Clone the repository to your local machine using `git clone [URL]`.
2. Open the project in your Java IDE.
3. Ensure that Maven successfully imports and resolves all dependencies listed in the `pom.xml` file.

## Running the Application
To run the Library Management System, follow these steps:
1. In your IDE, navigate to the `LibraryUI` class in the `frontend` package.
2. Run the `main` method in the `LibraryUI` class.
3. The application should start, and a new window titled "Library Management System" will appear.

## Running the application from JAR
If you'd like to run the application from a JAR file instead
1. Navigate to CS151-Library-Management-System-Gamers>out>artifacts>CS151_Library_Management_System_Gamers_jar
2. Run the JAR
3. This jar will create its own .json database independent from the one created if you run it manually. It will be stored in the same folder

## Usage
Here's a quick guide to using the Library Management System:

### For Patrons
- **Logging In:** Enter your username and password in the Sign In page.
- **Searching for Books:** Use the search bar to filter books by title, author, or ISBN.
- **Checking Out Books:** Select books and click the "Check Out" button. Note: Maximum of 5 books can be checked out.
- **Returning Books:** Navigate to the "Return" tab, select the books you wish to return, and click "Return Selected Books".

### For Librarians
- **Adding Books:** Navigate to the "Add Book" page, enter book details, and click "Add".
- **Removing Books:** Select books in the search page and click "Remove Book".

### Account Management
- **Signing Up:** Click the "Sign Up" button on the sign-in page and fill in your details.
- **Important Detail:**When signing up, write down the username generated for you
- **Deleting Account:** Click "Delete Account" in the main menu.

## Debugging
For debugging purposes, 4 Default users have been created each with the password "Pass" you can use these or delete them as you wish their user names are 1 and 2 for Patrons and 3 and 4 for Librarians

### Screenshots

![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/add712c6-d844-44a4-9a16-eee08f95da20)
![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/e12c7f01-1012-48e0-8807-a6374bb66613)
![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/9a60fa19-0231-4976-b9f7-be65a2ccd177)
![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/417de36e-1321-4a5f-b44b-c3d434d5d79a)
![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/c12239a7-acd0-462f-ad19-c490824154ae)
![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/ea1be9f5-116c-438b-9058-6b813449d85c)
![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/2bfa73b4-f912-46f6-ab71-d937bdff1282)
![image](https://github.com/Deathzill/CS151-Library-Management-System-Gamers/assets/113661519/058bb36a-5fea-49ff-b5d4-4ac9674b8fc3)





