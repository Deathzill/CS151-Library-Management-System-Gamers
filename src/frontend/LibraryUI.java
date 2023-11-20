package frontend;

import backend.Patron;
import backend.Tables;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LibraryUI {
    private JFrame frame;
    private JPanel createdSignInPage;
    private JPanel createdSignUpPage;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private CardLayout card;
    private JPanel cardContainer;
    private CardLayout usernameCard;
    private JPanel usernameScreen;
    private JTable table;
    private JTextField filterText;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton addBookButton;
    private backend.Tables libraryDataBase;
    private int currentUserID;
    private int runner = 0;
    private static final String JSON_FILE_PATH = "library_data.json"; // Path to the JSON file

    public LibraryUI() {
        // Initialize cardContainer first
        cardContainer = new JPanel(new CardLayout());

        // Initialize libraryDataBase
        libraryDataBase = new Tables();

        // Now call createDataBase
        this.createDataBase();

        // Load data from JSON
        this.loadDatabase();

        // Retrieve the CardLayout instance from cardContainer
        card = (CardLayout)(cardContainer.getLayout());

        frame = new JFrame("Social Network - Sign In Page");
        frame.setLayout(new BorderLayout());
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add sign-in and sign-up pages first
        createdSignInPage = this.createSignInPage(); //creating sign in page
        createdSignUpPage = this.createSignUpPage(); //creating sign up page

        cardContainer.add(createdSignInPage, "signin");
        cardContainer.add(createdSignUpPage, "signUpPage");


        // Add other pages
        cardContainer.add(this.createSearchScreen(), "searchScreen");
        cardContainer.add(this.addBooksPage(), "addBook");

        frame.add(cardContainer);
        frame.setVisible(true);

        // Set the sign-in page as the default visible panel
        card.show(cardContainer, "signin");

        // Setup window closing event
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Save data to JSON before exiting
                saveDatabase();
            }
        });
    }


    public JPanel createSignInPage(){
        // Initialize components for the sign-in page
        JLabel username = new JLabel();
        username.setText("Username:");
        username.setBounds(50, 50, 20, 40);

        JLabel password = new JLabel();
        password.setText("Password:");
        password.setBounds(200, 105, 20, 40);

        JLabel text = new JLabel();
        text.setBounds(100, 100, 100, 100);

        // Text fields for user input
        usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(40, 20));

        passwordInput = new JPasswordField();
        passwordInput.setPreferredSize(new Dimension(40, 20));

        // Setup the panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setPreferredSize(new Dimension(250, 150));
        loginPanel.setLayout(new GridBagLayout());

        // Buttons for sign-in and sign-up actions
        JButton signin = new JButton("Sign In");
        JButton signup = new JButton("Sign Up");

        // Layout constraints for components in the GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipady = 5;
        loginPanel.add(username , c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        loginPanel.add(usernameInput, c);

        c.ipady = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(password, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        loginPanel.add(passwordInput, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        loginPanel.add(signin, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;
        loginPanel.add(signup, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 0;
        c.gridy = 3;
        loginPanel.add(text, c);

        // Setting up borders for aesthetics
        loginPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        // Additional layout setup for branding and alignment
        c = new GridBagConstraints();
        Color PURPLE = new Color(102, 0, 153);
        JLabel myspaceLogo = new JLabel("Library Management");
        myspaceLogo.setFont(new Font("Serof", Font.BOLD + Font.ITALIC, 40));
        myspaceLogo.setForeground(PURPLE);

        JPanel loginWrapper = new JPanel(); //Wrapper panel to contain the login panel
        loginWrapper.setBackground(Color.WHITE);
        loginWrapper.setLayout(new GridBagLayout());
        loginWrapper.setPreferredSize(new Dimension(100, 100));

        c.gridx = 1;
        c.gridy = 1;
        loginWrapper.add(loginPanel, c);

        c.gridy = 0;
        loginWrapper.add(myspaceLogo, c);
        loginWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true), BorderFactory.createLineBorder(Color.BLACK, 2, true)));

        JPanel finalFrontPage = new JPanel(); //A second wrapper panel for the first wrapper panel. Used to implement BorderLayout
        finalFrontPage.setBackground(Color.WHITE);
        finalFrontPage.setLayout(new BorderLayout());
        finalFrontPage.add(loginWrapper, BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel1.setPreferredSize(new Dimension(150,100));
        panel2.setPreferredSize(new Dimension(150,100));
        panel3.setPreferredSize(new Dimension(0,100));
        panel4.setPreferredSize(new Dimension(100,100));
        panel1.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel3.setBackground(PURPLE);
        panel4.setBackground(PURPLE);

        finalFrontPage.add(panel1,BorderLayout.WEST);
        finalFrontPage.add(panel2,BorderLayout.EAST);
        finalFrontPage.add(panel3,BorderLayout.NORTH);
        finalFrontPage.add(panel4,BorderLayout.SOUTH);

        // Event listeners for sign-in
        signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = Integer.parseInt(usernameInput.getText());
                    backend.User user = libraryDataBase.getUser(userId);
                    System.out.println("Retrieved user: " + user); // Debugging line

                    if (user != null && user.getPassword().equals(passwordInput.getText())) {
                        System.out.println("Login successful for user: " + userId); // Debugging line

                        // Create login page
                        String page = "loggedInPage" + user.getUserID();
                        JPanel loggedInPage = createLoggedInScreen(user);
                        cardContainer.add(loggedInPage, page);
                        card.show(cardContainer, page); // Display Login Page
                        currentUserID = userId;

                        usernameInput.setText("");
                        passwordInput.setText("");
                        text.setText("");
                    } else {
                        System.out.println("Login failed for user: " + userId); // Debugging line
                        text.setText("Incorrect Login...");
                    }
                } catch(NumberFormatException ex){
                    System.out.println("Error parsing user ID: " + usernameInput.getText()); // Debugging line
                    text.setText("Input is empty or non-numerical...");
                }
            }
        });


        //eventListeners for sign up
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "signUpPage");
                usernameCard.show(usernameScreen, "signingUp"); //Display different screen
                usernameInput.setText(""); //clear text
                passwordInput.setText(""); //clear text
                text.setText(""); //clear text
            }
        });

        return finalFrontPage;
    }

    public JPanel createSignUpPage(){
        // Initialize the sign-up page panel with GridBagLayout for flexible component layout
        JPanel signUpPage = new JPanel(new GridBagLayout());
        signUpPage.setPreferredSize(new Dimension(400, 200));
        signUpPage.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));
        signUpPage.setBackground(Color.WHITE);

        // Labels and text fields for user input during sign up
        JLabel firstName = new JLabel("Enter First Name:");
        JLabel lastName = new JLabel("Enter Last Name:");
        JLabel email = new JLabel("Enter Email:");
        JLabel createNewPassword = new JLabel("Enter Password:");
        JButton createAccount = new JButton("Create Account");
        JLabel passwordError = new JLabel();
        JLabel emailError = new JLabel();

        JTextField newFirstName = new JTextField();
        newFirstName.setPreferredSize(new Dimension(40, 20));

        JTextField newLastName = new JTextField();
        newLastName.setPreferredSize(new Dimension(40, 20));

        JTextField newEmail = new JTextField();
        newEmail.setPreferredSize(new Dimension(40, 20));

        JTextField newPassword = new JPasswordField();
        newPassword.setPreferredSize(new Dimension(40, 20));

        GridBagConstraints constraints = new GridBagConstraints(); //Reference variable to add constraints to the GridBagLayout

        //Adding specific constraints for the GridBagLayout
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        signUpPage.add(firstName, constraints);

        constraints.gridy = 1;
        signUpPage.add(lastName, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        signUpPage.add(newFirstName, constraints);

        constraints.gridy = 1;
        signUpPage.add(newLastName, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        signUpPage.add(email, constraints);

        constraints.gridx = 3;
        signUpPage.add(newEmail, constraints);

        constraints.gridy = 3;
        signUpPage.add(newPassword, constraints);

        constraints.gridx = 0;
        signUpPage.add(createNewPassword, constraints);

        // Checkbox for selecting if the account is for a librarian
        JCheckBox librarian = new JCheckBox("Check if Librarian Account");
        librarian.setBackground(Color.WHITE);
        constraints.gridy = 4;
        constraints.gridx = 3;
        signUpPage.add(librarian, constraints);

        constraints.gridy = 5;
        constraints.gridx = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        signUpPage.add(createAccount, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        signUpPage.add(passwordError, constraints);

        constraints.gridy = 6;
        constraints.gridx = 0;
        signUpPage.add(emailError, constraints);

        JLabel signUpHeader = new JLabel("- - - - - - Sign Up - - - - - -");
        signUpHeader.setVisible(true);
        JPanel signUpPageWrapper = new JPanel(new GridBagLayout()); //Wrapper for the sign up panel
        Color PURPLE = new Color(102, 0, 153);
        signUpPageWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        signUpPageWrapper.setBackground(Color.LIGHT_GRAY);

        usernameCard = new CardLayout(); //New cardLayout for the specific panel. Switches center panel between the signup page to the page that displays username
        usernameScreen = new JPanel(usernameCard);
        usernameScreen.add(signUpPage, "signingUp");

        constraints = new GridBagConstraints(); //New constraints
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        signUpPageWrapper.add(usernameScreen, constraints);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        signUpPageWrapper.add(signUpHeader);

        JPanel loggedIn = new JPanel(); //Logged in panel that'll display username
        loggedIn.setBackground(Color.WHITE);
        loggedIn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false), BorderFactory.createLineBorder(Color.WHITE, 2, true)));
        loggedIn.setLayout(new GridBagLayout());
        JLabel username = new JLabel();
        username.setHorizontalAlignment(SwingConstants.CENTER);
        constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        loggedIn.add(username, constraints);

        constraints.ipady = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        JButton logoutButton = new JButton("Logout");

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "signin");
            }
        });

        loggedIn.add(logoutButton, constraints);

        usernameScreen.add(loggedIn, "loginUsername");

        // Event listener for the "Create Account" button
        createAccount.addActionListener(new ActionListener() {
            // Event handler for the 'Create Account' button click
            public void actionPerformed(ActionEvent e){

                try{
                    // Validate the entered password and email requirements
                    LibraryUI.checkPasswordRequirements(newPassword.getText());
                    LibraryUI.checkEmailRequirements(newEmail.getText());

                    // Hide the sign-up header upon successful account creation
                    signUpHeader.setVisible(false);
                    String pageName;

                    // Determine the type of account to create based on the librarian checkbox
                    if(librarian.isSelected()){ //If Librarian, create librarian object. Else make Patron object
                        Date date = new Date();
                        backend.Librarian newUser = new backend.Librarian(LibraryUI.createUsername(),
                                newFirstName.getText() + " " + newLastName.getText(), newEmail.getText(), newPassword.getText(), date, date);

                        // Set up account details and add the new user to the database
                        currentUserID = newUser.getUserID();
                        pageName = "loggedInPage" + newUser.getUserID();
                        username.setText("Generated UserID: " + newUser.getUserID());
                        libraryDataBase.dbAddUser(newUser);
                    }
                    else{  // If not selected, create a Patron account
                        Date date = new Date();
                        backend.Patron newUser = new backend.Patron(LibraryUI.createUsername(),
                                newFirstName.getText() + " " + newLastName.getText(), newEmail.getText(), newPassword.getText(), date);

                        // Set up account details and add the new user to the database
                        currentUserID = newUser.getUserID();
                        pageName = "loggedInPage" + newUser.getUserID();
                        username.setText("Generated UserID: " + newUser.getUserID());
                        libraryDataBase.dbAddUser(newUser);
                    }

                    // Clear form fields after account creation
                    newEmail.setText("");
                    newFirstName.setText("");
                    newLastName.setText("");
                    newPassword.setText("");
                    librarian.setSelected(false);

                    // Switch to a screen showing the created username
                    usernameCard.show(usernameScreen, "loginUsername");

                    // Create a personalized screen for the logged-in user
                    JPanel logginPanel = LibraryUI.this.createLoggedInScreen(); //Generating screen with user info
                    cardContainer.add(logginPanel, pageName); //Adding screen with user info

                    // Clear any error messages
                    passwordError.setText("");
                    emailError.setText("");
                    // Handle specific password validation errors
                } catch(UpperCaseCharacterMissing error){
                    passwordError.setText(error.getMessage());
                } catch(LowerCaseCharacterMissing error){
                    passwordError.setText(error.getMessage());
                } catch(Minimum8CharactersRequired error){
                    passwordError.setText(error.getMessage());
                } catch(SpecialCharacterMissing error){
                    passwordError.setText(error.getMessage());
                } catch(NumberCharacterMissing error){
                    passwordError.setText(error.getMessage());
                } catch(PasswordException error){
                    passwordError.setText(error.getMessage());
                } catch(AtSignMissingException error){
                    emailError.setText(error.getMessage());
                } catch(PeriodMissingException error){
                    emailError.setText(error.getMessage());
                } catch(EmailException error){
                    emailError.setText(error.getMessage());
                }

            }
        });

        return signUpPageWrapper;
    }

    public JPanel createLoggedInScreen() {
        /*Call new method that takes user as input, to handle Hard coded and new users*/
        return createLoggedInScreen(libraryDataBase.getUser(currentUserID));
    }

    public JPanel createLoggedInScreen(backend.User user) {
        // Initialize the panel to display user information
        JPanel loggedInPanel = new JPanel();
        loggedInPanel.setBackground(Color.WHITE);
        loggedInPanel.setLayout(new GridBagLayout());
        loggedInPanel.setPreferredSize(new Dimension(400, 200));
        loggedInPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        // Labels to display user-specific information
        JLabel displayUsername = new JLabel("User: " + user.getUserID());
        displayUsername.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayFirstName = new JLabel("Name: " + user.getName());
        displayFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayJoinDate = new JLabel("Join Date: " + user.getDateJoined());
        displayJoinDate.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayEmail = new JLabel("Email: " + user.getEmail());
        displayEmail.setHorizontalAlignment(SwingConstants.CENTER);

        // Button to proceed to the next screen
        JButton next = new JButton("Next");

        // Setup layout constraints for components
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.ipady = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        loggedInPanel.add(displayUsername, constraints);

        constraints.gridy = 1;
        loggedInPanel.add(displayFirstName, constraints);

        constraints.gridy = 2;
        loggedInPanel.add(displayJoinDate, constraints);

        constraints.gridy = 3;
        loggedInPanel.add(displayEmail, constraints);

        constraints.gridy = 4;
        loggedInPanel.add(next, constraints);

        // Wrapper panel for additional styling and layout
        JPanel loggedInPageWrapper = new JPanel(new GridBagLayout());
        loggedInPageWrapper.setBackground(Color.lightGray);
        Color PURPLE = new Color(102, 0, 153);
        loggedInPageWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));

        // Adding the main panel to the wrapper
        loggedInPageWrapper.add(loggedInPanel);

        // Action listener for the 'Next' button
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userType = user.getClass().getName();

                if(userType.equals("backend.Librarian")) {
                    addBookButton.setVisible(true);
                } else {
                    addBookButton.setVisible(false);
                }

                card.show(cardContainer, "searchScreen");
            }
        });;

        return loggedInPageWrapper;// Return the fully constructed logged-in panel
    }


    public static int createUsername(){ //return random user ID
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        username.append(String.format("%06d", random.nextInt(999999))); //generates 6 digit user ID

        return Integer.parseInt(username.toString());
    }
    public static boolean checkEmailRequirements(String email) throws EmailException{
        // Flags to track presence of '@' and '.' in the email
        boolean containsAtSign = false;
        boolean containsPeriod = false;

        // Loop through each character in the email to check for '@' and '.'
        for(int i=0; i<email.length(); i++) {
            if (email.charAt(i) == '@') {
                containsAtSign = true;
            }
            if (email.charAt(i) == '.') {
                containsPeriod = true;
            }
        }

        // Throw an exception if '@' is missing in the email
        if(!containsAtSign){
            throw new AtSignMissingException("Error - Email Missing '@' Sign");
        }
        // Throw an exception if '.' is missing in the email
        if(!containsPeriod){
            throw new PeriodMissingException("Error - Email Missing Period (.) Sign");
        }
        return true;
    }
    public static boolean checkPasswordRequirements(String password) throws PasswordException{ //Method to check if password requirements are met
        // Initialize flags for each password requirement
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean characterLimit = false;
        boolean specialCharacter = false;
        boolean numberCharacter = false;

        // Check if the password length is at least 8 characters
        if(password.length() >= 8){
            characterLimit = true;
        }

        // Iterate over each character of the password to check for different requirements
        for(int i = 0; i < password.length(); i++){
            char character = password.charAt(i);

            if(Character.isUpperCase(character)){
                upperCase = true;
            }
            if(Character.isLowerCase(character)){
                lowerCase = true;
            }
            if(Character.isDigit(character)){
                numberCharacter = true;
            }
            if(!Character.isLetter(character) && !Character.isDigit(character) && !Character.isWhitespace(character)){
                specialCharacter = true;
            }
        }

        // Throw exceptions if any of the requirements are not met
        if(!upperCase){
            throw new UpperCaseCharacterMissing("Error - Password Missing Uppercase");
        }
        else if(!lowerCase){
            throw new LowerCaseCharacterMissing("Error - Password Missing Lowercase");
        }
        else if(!characterLimit){
            throw new Minimum8CharactersRequired("Error - Minimum of 8 Characters Required");
        }
        else if(!specialCharacter){
            throw new SpecialCharacterMissing("Error - Missing Special Character");
        }
        else if(!numberCharacter){
            throw new NumberCharacterMissing("Error - Missing Number Character");
        }

        return true;
    }


    public JPanel createSearchScreen(){

        // Set up the table model and row sorter for the search table
        tableModel = new DefaultTableModel();
        rowSorter = new TableRowSorter<DefaultTableModel>(tableModel); //Used to create new filters for the table(Search filter)
        table = new JTable(tableModel);
        table.setRowSorter(rowSorter);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add columns to the table model
        tableModel.addColumn("Title");
        tableModel.addColumn("Authors");
        tableModel.addColumn("ISBN");

        // Populate the table with book data from the database
        Map<Integer, backend.Book> myMap = libraryDataBase.getBooks();
        backend.Book book;
        for(Map.Entry<Integer, backend.Book> entry : myMap.entrySet()){ //Populating table with data
            book = entry.getValue();
            tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), Integer.toString(book.getISBN())});
        }

        // Set up a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Create a panel to hold the table and its associated components
        JPanel panel = new JPanel(new GridBagLayout());

        // Add the table (inside its scroll pane) to the panel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(scrollPane, constraints);

        // Create a text field for inputting search filters
        filterText = new JTextField(20);
        filterText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                LibraryUI.this.updateTableFilter();
            }

            public void removeUpdate(DocumentEvent e) {
                LibraryUI.this.updateTableFilter();
            }

            public void changedUpdate(DocumentEvent e) {
                LibraryUI.this.updateTableFilter();
            }
        });

        // Create a button for checking out books
        JButton checkOutButton = new JButton("Check Out");
        checkOutButton.setPreferredSize(new Dimension(100, 22));

        // Setup a panel for search filter and checkout button
        JPanel bufferPanel = new JPanel(new GridBagLayout());
        bufferPanel.add(filterText, constraints);
        constraints.gridx = 2;
        bufferPanel.add(checkOutButton, constraints);

        // Add the buffer panel to the main panel
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(bufferPanel, constraints);

        // Labels for displaying messages related to checked out and unavailable books
        JLabel text = new JLabel(); //Text for checked out books/errors
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(text, constraints);

        JLabel text2 = new JLabel(); //Text for unavailiable books
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(text2, constraints);

        // Event listener for the checkout button
        checkOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the indices of the selected rows in the table
                int[] rows = table.getSelectedRows();

                // StringBuilder objects to accumulate messages for checked out and unavailable books
                StringBuilder checkedOut = new StringBuilder();
                checkedOut.append("Checked out: ");
                StringBuilder unavailiableBook = new StringBuilder();
                unavailiableBook.append("Unavailiable: ");

                // Retrieve the map of books from the database
                Map<Integer, backend.Book> map = libraryDataBase.getBooks();

                if(rows.length <= 5){ //Requirement that only a max of 5 books can be checked out. Exception can be created/handled here
                    for(int i = 0; i < rows.length; i++){
                        // Check if the book is already checked out
                        if(!libraryDataBase.isCheckedOut(Integer.parseInt((String)tableModel.getValueAt(table.convertRowIndexToModel(rows[i]), 2)))){
                            // Append the title of the checked-out book to the StringBuilder
                            checkedOut.append(tableModel.getValueAt(table.convertRowIndexToModel(rows[i]), 0));

                            if((i + 1) != rows.length){
                                checkedOut.append(", ");
                            }

                            // Get the patron object and borrow the book
                            Patron patron = (Patron) libraryDataBase.getUser(currentUserID);
                            patron.borrowBook(map, Integer.parseInt((String)tableModel.getValueAt(table.convertRowIndexToModel(rows[i]), 2)));

                            // Update the book's status in the database as checked out
                            backend.Book book = new backend.Book(Integer.parseInt((String)tableModel.getValueAt(table.convertRowIndexToModel(rows[i]), 2)));
                            libraryDataBase.checkOutBook(book);
                        }
                        else{
                            // Append the title of the unavailable book to the StringBuilder
                            unavailiableBook.append(tableModel.getValueAt(table.convertRowIndexToModel(rows[i]), 0));
                            if((i + 1) != rows.length){
                                unavailiableBook.append(", ");
                            }
                        }

                    }
                }
                else{
                    // Set error message if more than 5 books are selected for checkout
                    checkedOut.append("Error --- Book Limit: 5");
                    unavailiableBook.append("");
                }

                // Update the labels with the messages for checked out and unavailable books
                text.setText(checkedOut.toString());
                text2.setText(unavailiableBook.toString());

            }
        });

        // Create a 'Return' button
        JButton returnButton = new JButton("Return");
        returnButton.setPreferredSize(new Dimension(100, 22));

        // Add the filter text field to the buffer panel
        constraints.gridx = 0; // Adjust grid position as needed
        constraints.gridy = 0; // Adjust grid position as needed
        bufferPanel.add(filterText, constraints);

        // Add the 'Check Out' button to the buffer panel
        constraints.gridx = 1; // Adjust grid position as needed
        bufferPanel.add(checkOutButton, constraints);

        // Add the 'Return' button to the buffer panel
        constraints.gridx = 2; // Adjust grid position as needed
        bufferPanel.add(returnButton, constraints);

        // Add the buffer panel to the main panel
        constraints.gridx = 0; // Reset to align with the main panel
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER; // Span across all columns
        panel.add(bufferPanel, constraints);

        // Action listener for the 'Return' button
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add the return book screen to the card layout
                cardContainer.add(LibraryUI.this.createReturnBookScreen(), "returnScreen" + currentUserID + runner);
                // Show the return book screen
                card.show(cardContainer, "returnScreen" + currentUserID + runner);
                runner++;
            }
        });

        // Set the constraints for the 'Add Book' button
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        addBookButton = new JButton("Add Book");
        panel.add(addBookButton, constraints);  //Add linked to sign in button. If backend.Patron don't show. If librarian show
        addBookButton.setVisible(true);

        // Create a 'Logout' button
        JButton logoutFromTableButton = new JButton("Logout");
        constraints.ipadx = 5;  // Padding in x direction
        constraints.gridx = 3;  // Position on x-axis
        constraints.gridy = 1;  // Position on y-axis
        constraints.anchor = GridBagConstraints.FIRST_LINE_END; // Align to the end of the first line

        // Add the 'Logout' button to the panel
        panel.add(logoutFromTableButton, constraints);

        // Add action listener to the 'Add Book' button
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // On clicking 'Add Book', switch to the 'addBook' card in the CardLayout
                card.show(cardContainer, "addBook");
                // Clear any text and selections from the search screen
                text.setText("");
                text2.setText("");
                table.clearSelection();
            }
        });

        // Add action listener to the 'Logout' button
        logoutFromTableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // On clicking 'Logout', switch back to the sign-in card
                card.show(cardContainer, "signin");
                // Clear any text and selections from the search screen
                text.setText("");
                text2.setText("");
                table.clearSelection();
            }
        });

        return panel;

    }

    public JPanel addBooksPage(){
        // Setting up the main panel for adding books
        JPanel addBookPanel = new JPanel(new GridBagLayout());
        addBookPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));
        addBookPanel.setBackground(Color.WHITE);
        addBookPanel.setPreferredSize(new Dimension(250, 150));

        // Creating labels for book details input fields
        JLabel bookTitle = new JLabel("Book Title: ");
        JLabel bookAuthor = new JLabel("Book Author: ");
        JLabel bookISBN = new JLabel("Book ISBN: ");

        // Text fields for entering book details
        JTextField title = new JTextField(10);
        JTextField author = new JTextField(10);
        JTextField isbn = new JTextField(10);

        // Constraints for layout management
        GridBagConstraints constraints = new GridBagConstraints();

        // Adding title label and text field to the panel
        constraints.ipady = 6;
        constraints.gridx = 0;
        constraints.gridy = 0;
        addBookPanel.add(bookTitle, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(title, constraints);

        // Adding author label and text field to the panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = 6;
        addBookPanel.add(bookAuthor, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(author, constraints);

        // Adding ISBN label and text field to the panel
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.ipady = 6;
        addBookPanel.add(bookISBN, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(isbn, constraints);

        // Button to add the book to the database
        JButton addBookButton = new JButton("Add");
        addBookButton.setPreferredSize(new Dimension(65, 25));

        // Adding the button to the panel
        constraints.gridy = 3;
        addBookPanel.add(addBookButton, constraints);

        // Wrapper panel for aesthetic purposes
        JPanel addBookPanelWrapper = new JPanel(new GridBagLayout());
        addBookPanelWrapper.setBackground(Color.LIGHT_GRAY);
        Color PURPLE = new Color(102, 0, 153);
        addBookPanelWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        addBookPanelWrapper.add(addBookPanel);

        // Action listener for the add book button
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try { // Attempt to parse the ISBN and create a new book object
                    int isbnNumber = Integer.parseInt(isbn.getText()); // Try to parse the ISBN
                    backend.Book book = new backend.Book(backend.Book.BookStatus.AVAILABLE, title.getText(), author.getText(), isbnNumber, false, null);

                    // Add the book to the database and update the table
                    libraryDataBase.dbAddBook(book);
                    tableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), Integer.toString(book.getISBN())}); //Add new data to table

                    // Switch back to the search screen
                    card.show(cardContainer, "searchScreen");

                    // Clear the text fields
                    title.setText("");
                    author.setText("");
                    isbn.setText("");
                } catch (NumberFormatException ex) {
                    // Display error message
                    JOptionPane.showMessageDialog(frame, "Invalid ISBN", "Error", JOptionPane.ERROR_MESSAGE);
                    // Optionally, you could also clear the isbn field or keep the erroneous input for correction
                    isbn.setText("");
                }
            }
        });

        return addBookPanelWrapper; // Return the complete panel for adding books
    }

    public void updateTableFilter(){ // This method updates the table filter based on the entered text in the search bar.
        RowFilter<DefaultTableModel, Object> updatedFilter = null;

        try {
            // Create a new RowFilter based on the text entered in the filterText field.
            // The regexFilter here is case insensitive ('(?i)') and matches the filterText.
            updatedFilter = RowFilter.regexFilter("(?i)" + filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            // If the regex pattern is not valid (e.g., an incomplete expression), do not update the filter.
            return;
        }

        // Apply the newly created filter to the row sorter.
        // This will update the display of the table to only show rows matching the filter.
        rowSorter.setRowFilter(updatedFilter);
    }


    //creates a page where users can return books
    public JPanel createReturnBookScreen(){
        //TODO:change this logic to allow the user to select what books they want to return

        // Create a panel for the returned books section
        JPanel returnedBooksPanel = new JPanel(new GridBagLayout());
        returnedBooksPanel.setBackground(Color.WHITE);
        returnedBooksPanel.setPreferredSize(new Dimension(400, 200));
        returnedBooksPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        // Labels to display information about returned and overdue books
        JLabel bookInformation = new JLabel();
        JLabel overdueBooksInformation = new JLabel();

        // Obtain current Patron information from the database
        Patron patron = (Patron)libraryDataBase.getUser(currentUserID);

        // Retrieve lists of overdue and returned books
        List<String> overdueBooks = patron.getOverdueBooks(libraryDataBase.getBooks());
        List<String> returnedBooks = patron.returnBook(libraryDataBase.getBooks()); //Can create too many overDue books exception here

        // Building strings to display returned books
        StringBuilder returned = new StringBuilder();
        for(int i = 0; i < returnedBooks.size(); i++){
            returned.append(returnedBooks.get(i));

            if((i + 1) < returnedBooks.size()){
                returned.append(", ");
            }
        }

        // Building strings to display overdue books
        StringBuilder overdue = new StringBuilder();
        if(!overdueBooks.isEmpty()){
            for(int i = 0; i < overdueBooks.size(); i++){
                overdue.append(overdueBooks.get(i));

                if((i + 1) < overdueBooks.size()){
                    overdue.append(", ");
                }
            }
        }

        // Setting text for the labels
        bookInformation.setText("Returned Books: " + returned);
        overdueBooksInformation.setText("Overdue Books: " + overdue);

        // Setup layout constraints
        GridBagConstraints constraints = new GridBagConstraints();

        // Add the labels to the panel
        returnedBooksPanel.add(bookInformation, constraints);
        constraints.gridy = 1;
        returnedBooksPanel.add(overdueBooksInformation, constraints);

        // Wrapper panel for styling and layout
        JPanel returnedBooksPanelWrapper = new JPanel(new GridBagLayout());
        returnedBooksPanelWrapper.setBackground(Color.LIGHT_GRAY);
        Color PURPLE = new Color(102, 0, 153);
        returnedBooksPanelWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        returnedBooksPanelWrapper.add(returnedBooksPanel);

        // Button to navigate to the next screen
        JButton next = new JButton("Next");
        constraints.gridy = 2;
        returnedBooksPanel.add(next, constraints);

        // Event listener for the next button
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "searchScreen");
            }
        });

        return returnedBooksPanelWrapper;
    }

    public void createDataBase() { // Creating the database from here
        libraryDataBase = new Tables();
        File file = new File(JSON_FILE_PATH);
        if (!file.exists()) {

            // Adding books
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", 34587, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "The Hunger Games", "Suzanne Collins", 76459, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "1984", "George Orwell", 58320, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "To Kill a Mockingbird", "Harper Lee", 49275, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "The Great Gatsby", "F. Scott Fitzgerald", 86543, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "Pride and Prejudice", "Jane Austen", 23498, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "The Lord of the Rings", "J.R.R. Tolkien", 67932, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "The Catcher in the Rye", "J.D. Salinger", 43876, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "The Da Vinci Code", "Dan Brown", 54782, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "Brave New World", "Aldous Huxley", 65890, false, null));
            libraryDataBase.dbAddBook(new backend.Book(backend.Book.BookStatus.AVAILABLE, "Animal Farm", "George Orwell", 32789, false, null));


            // Hardcoding users
            Date currentDate = new Date();

            // Creating two patrons
            hardcodePatron(1, "Default Patron 1", "patron1@example.com", "Pass", currentDate);
            hardcodePatron(2, "Default Patron 2", "patron2@example.com", "Pass", currentDate);

            // Creating two librarians
            hardcodeLibrarian(3, "Default Librarian 1", "librarian1@example.com", "Pass", currentDate);
            hardcodeLibrarian(4, "Default Librarian 2", "librarian2@example.com", "Pass", currentDate);

            saveDatabase();
        }
    }

    // Helper method to create and add a loggedInPage for a given user
    private void addLoggedInPageForUser(backend.User user) {
        JPanel loggedInPage = createLoggedInScreen(user);
        String pageName = "loggedInPage" + user.getUserID();
        cardContainer.add(loggedInPage, pageName);
    }

    private void hardcodePatron(int id, String name, String email, String password, Date joinDate) {
        backend.User patron = new backend.Patron(id, name, email, password, joinDate);
        libraryDataBase.dbAddUser(patron);
        addLoggedInPageForUser(patron);
    }

    private void hardcodeLibrarian(int id, String name, String email, String password, Date joinDate) {
        backend.User librarian = new backend.Librarian(id, name, email, password, joinDate, joinDate);
        libraryDataBase.dbAddUser(librarian);
        addLoggedInPageForUser(librarian);
    }

    // Load data from JSON file
    private void loadDatabase() {
        File jsonFile = new File(JSON_FILE_PATH);
        if (jsonFile.exists()) {
            libraryDataBase.loadFromJSON(JSON_FILE_PATH);
        } else {
            createDataBase(); // Call only if the JSON file does not exist
        }
    }

    // Save data to JSON file
    private void saveDatabase() {
        libraryDataBase.saveToJSON(JSON_FILE_PATH);
    }

    public static void main(String[] args) {

        new LibraryUI(); //Create application
    }
}
