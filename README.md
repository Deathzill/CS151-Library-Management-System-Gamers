# Library Management System

## Description
This Java project is a Library Management System designed to manage book lending and user accounts in a library setting. It features a user-friendly interface for both patrons and librarians, enabling tasks like book checkouts, returns, and account management.

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
