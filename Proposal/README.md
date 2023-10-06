# CS151-Library-Management-System-Gamers
---Proposal---
Library Management System
Team #4 (Gamers)
John Phan, Munkh-Erdene Khuderbaatar, Nicholas Valencia
In less than 2 pages, propose a topic in object-oriented design as your project.  Your proposal must include (but not be limited to) the following sections: 

Problem: To manage books, one must physically search for books in our library. To make finding a book efficient, we will develop a program that will automatically sort and categorize books to streamline this process.

Assumptions: We will assume logic similar to the ARS in SCU that can retrieve books with a machine that drops the books off at a delivery window. This

For more info on Santa Clara University's automatic retrieval system, refer to this link. 
https://www.scu.edu/library/ars/

Operating environments: Will operate at the library to retrieve books faster.

Intended usage: A user will arrive at the library and interact with a machine hosting our service to search and retrieve books from the library automatically

High-Level Description: A user will be able to check in and check out books from the libraries database using their library cards. The OS will assign return dates and check if the user has too many overdue books as well. A librarian will be able to use the OS to update the library database.


Plan: 
We will have a database that stores objects of class ‘Book’
We will decide on an efficient search algorithm to find books by Name, ISBN, or Author
We will have two user classes, Librarian, and User
Librarian will be responsible for updating the database with new books.
User will be able to update whether or not a book is checked out or not when they retrieve or return a book. They will also be able to search the library’s database as well.


Functionality:

Database Integration:
-Store book and user details for easy retrieval.

Search Capabilities:
-Allow users to search for books by title, author, or ISBN.

Automated Retrieval:
-The system will search the Database for book retrieval

Check-in and Check-out:
-Users scan library cards to borrow or return books.

Overdue Management:
-Notify users of overdue books and restrict further borrowing.

User and Librarian Profiles:
-Separate access levels for librarians and users 

Inventory Management:
-The program tracks book availability and overdue status for librarians.


Operations:
Librarian: will be able to update the library database
User: will be able to check out and check in books, a max of 5 at a time. Should not be able to manage database, when a book is checked out a flag will switch to determine if it's available or not. If they have any overdue books they will not be able to return any more
