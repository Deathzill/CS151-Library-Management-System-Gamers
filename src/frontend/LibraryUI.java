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
    private DefaultTableModel tablemodel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton addBookButton;
    private backend.Tables libraryDataBase;
    private int currentUserID;
    private int runner = 0;

    public LibraryUI(){ //create the different panels/screens

        this.createDataBase();
        frame = new JFrame("Social Network - Sign In Page");
        frame.setLayout(new BorderLayout());
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        card = new CardLayout(); //creating cardlayout
        cardContainer = new JPanel(card); //creating card container for the card layout

        createdSignInPage = this.createSignInPage(); //creating sign in page
        createdSignUpPage = this.createSignUpPage(); //creating sign up page

        cardContainer.add(createdSignInPage, "signin");
        cardContainer.add(createdSignUpPage, "signUpPage");
        cardContainer.add(this.createSearchScreen(), "searchScreen");
        cardContainer.add(this.addBooksPage(), "addBook");

        frame.add(cardContainer);

        frame.setVisible(true);

    }

    public JPanel createSignInPage(){
        JLabel username = new JLabel();
        username.setText("Username:");
        username.setBounds(50, 50, 20, 40);

        JLabel password = new JLabel();
        password.setText("Password:");
        password.setBounds(200, 105, 20, 40);

        JLabel text = new JLabel();
        text.setBounds(100, 100, 100, 100);

        usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(40, 20));

        passwordInput = new JPasswordField();
        passwordInput.setPreferredSize(new Dimension(40, 20));

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setPreferredSize(new Dimension(250, 150));
        loginPanel.setLayout(new GridBagLayout());

        JButton signin = new JButton("Sign In");
        JButton signup = new JButton("Sign Up");

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

        loginPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

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

        signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String out = null;
                boolean loggedIn = false;
                try {
                    if (libraryDataBase.getUser(Integer.parseInt(usernameInput.getText())) != null) { //Checking if login is correct
                        backend.User user = libraryDataBase.getUser(Integer.parseInt(usernameInput.getText()));

                        if (user.getPassword().equals(passwordInput.getText())) {
                            String page = "loggedInPage" + user.getUserID();
                            card.show(cardContainer, page);
                            currentUserID = Integer.parseInt(usernameInput.getText());

                            usernameInput.setText(""); //clear text
                            passwordInput.setText(""); //clear text
                            text.setText("");
                            loggedIn = true;
                        }
                    }

                    if(loggedIn == false) {
                        out = "Incorrect Login...";
                        text.setText(out);
                    }
                //Fixes if username is empty or non-numerical
                } catch(NumberFormatException ex){
                    out = "Input is empty or non-numerical...";
                    text.setText(out);
                }
            }
        });

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
        JPanel signUpPage = new JPanel(new GridBagLayout());
        signUpPage.setPreferredSize(new Dimension(400, 200));
        signUpPage.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));
        signUpPage.setBackground(Color.WHITE);

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

        createAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                try{
                    LibraryUI.checkPasswordRequirements(newPassword.getText());
                    LibraryUI.checkEmailRequirements(newEmail.getText());
                    signUpHeader.setVisible(false);
                    String pageName;

                    if(librarian.isSelected()){ //If Librarian, create librarian object. Else make Patron object
                        Date date = new Date();
                        backend.Librarian newUser = new backend.Librarian(LibraryUI.createUsername(),
                                newFirstName.getText() + " " + newLastName.getText(), newEmail.getText(), newPassword.getText(), date, date);


                        currentUserID = newUser.getUserID();
                        pageName = "loggedInPage" + newUser.getUserID();
                        username.setText("Generated UserID: " + newUser.getUserID());

                        libraryDataBase.dbAddUser(newUser);
                    }
                    else{
                        Date date = new Date();
                        backend.Patron newUser = new backend.Patron(LibraryUI.createUsername(),
                                newFirstName.getText() + " " + newLastName.getText(), newEmail.getText(), newPassword.getText(), date);

                        currentUserID = newUser.getUserID();
                        pageName = "loggedInPage" + newUser.getUserID();
                        username.setText("Generated UserID: " + newUser.getUserID());

                        libraryDataBase.dbAddUser(newUser);
                    }

                    newEmail.setText("");
                    newFirstName.setText("");
                    newLastName.setText("");
                    newPassword.setText("");
                    librarian.setSelected(false);

                    usernameCard.show(usernameScreen, "loginUsername");

                    JPanel logginPanel = LibraryUI.this.createLoggedInScreen(); //Generating screen with user info

                    cardContainer.add(logginPanel, pageName); //Adding screen with user info

                    passwordError.setText("");

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

    public JPanel createLoggedInScreen(){
        JPanel loggedInPanel = new JPanel();
        loggedInPanel.setBackground(Color.WHITE);
        loggedInPanel.setLayout(new GridBagLayout());
        loggedInPanel.setPreferredSize(new Dimension(400, 200));
        loggedInPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        //Setting user information
        JLabel displayUsername = new JLabel();
        displayUsername.setText("User: " + libraryDataBase.getUser(currentUserID).getUserID());
        displayUsername.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayFirstName = new JLabel("Name: " + libraryDataBase.getUser(currentUserID).getName());
        displayFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayJoinDate = new JLabel("Join Date: " + libraryDataBase.getUser(currentUserID).getDateJoined()); //+ userArray.get(signedInUserArrayIndex).getUserLastName());
        displayJoinDate.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayEmail = new JLabel("Email: " + libraryDataBase.getUser(currentUserID).getEmail());
        displayEmail.setHorizontalAlignment(SwingConstants.CENTER);
        JButton next = new JButton("Next");

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

        JPanel loggedInPageWrapper = new JPanel(new GridBagLayout()); //Wrapper panel for the loggedInPanel
        loggedInPageWrapper.setBackground(Color.lightGray);
        Color PURPLE = new Color(102, 0, 153);
        loggedInPageWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));

        loggedInPageWrapper.add(loggedInPanel);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String userType = libraryDataBase.getUser(currentUserID).getClass().getName();

                if(userType.equals("backend.Librarian")){ //Setting different access levels for librarian/patron
                    addBookButton.setVisible(true);
                    card.show(cardContainer, "searchScreen");
                }
                else{
                    addBookButton.setVisible(false);
                    cardContainer.add(LibraryUI.this.createReturnBookScreen(), "returnScreen" + currentUserID + runner); //Creating new screen with return information
                    card.show(cardContainer, "returnScreen" + currentUserID + runner); //runner used to help with the different screen IDs
                    runner += 1;
                }

            }
        });


        return loggedInPageWrapper;
    }

    public static int createUsername(){
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        username.append(String.format("%06d", random.nextInt(999999))); //generates 6 digit user ID

        return Integer.parseInt(username.toString());
    }
    public static boolean checkEmailRequirements(String email) throws EmailException{
        boolean containsAtSign = false;
        boolean containsPeriod = false;
        for(int i=0; i<email.length(); i++) {
            if (email.charAt(i) == '@') {
                containsAtSign = true;
            }
            if (email.charAt(i) == '.') {
                containsPeriod = true;
            }
        }
        if(!containsAtSign){
            throw new AtSignMissingException("Error - Email Missing '@' Sign");
        }
        if(!containsPeriod){
            throw new PeriodMissingException("Error - Email Missing Period (.) Sign");
        }
        return true;
    }
    public static boolean checkPasswordRequirements(String password) throws PasswordException{ //Method to check if password requirements are met
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean characterLimit = false;
        boolean specialCharacter = false;
        boolean numberCharacter = false;

        if(password.length() >= 8){
            characterLimit = true;
        }

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

        tablemodel = new DefaultTableModel();
        rowSorter = new TableRowSorter<DefaultTableModel>(tablemodel); //Used to create new filters for the table(Search filter)
        table = new JTable(tablemodel);
        table.setRowSorter(rowSorter);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        tablemodel.addColumn("Title");
        tablemodel.addColumn("Authors");
        tablemodel.addColumn("ISBN");

        Map<Integer, backend.Book> myMap = libraryDataBase.getBooks();
        backend.Book book;

        for(Map.Entry<Integer, backend.Book> entry : myMap.entrySet()){ //Populating table with data
            book = entry.getValue();
            tablemodel.addRow(new Object[]{book.getTitle(), book.getAuthor(), Integer.toString(book.getISBN())});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(scrollPane, constraints);

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

        JButton checkOutButton = new JButton("Check Out");
        checkOutButton.setPreferredSize(new Dimension(100, 22));

        JPanel bufferPanel = new JPanel(new GridBagLayout());
        bufferPanel.add(filterText, constraints);
        constraints.gridx = 2;
        bufferPanel.add(checkOutButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(bufferPanel, constraints);

        JLabel text = new JLabel(); //Text for checked out books/errors
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(text, constraints);

        JLabel text2 = new JLabel(); //Text for unavailiable books
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(text2, constraints);


        checkOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] rows = table.getSelectedRows();

                StringBuilder checkedOut = new StringBuilder();
                checkedOut.append("Checked out: ");
                StringBuilder unavailiableBook = new StringBuilder();
                unavailiableBook.append("Unavailiable: ");

                Map<Integer, backend.Book> map = libraryDataBase.getBooks();

                if(rows.length <= 5){ //Requirement that only a max of 5 books can be checked out. Exception can be created/handled here
                    for(int i = 0; i < rows.length; i++){
                        if(!libraryDataBase.isCheckedOut(Integer.parseInt((String)tablemodel.getValueAt(table.convertRowIndexToModel(rows[i]), 2)))){
                            checkedOut.append(tablemodel.getValueAt(table.convertRowIndexToModel(rows[i]), 0));

                            if((i + 1) != rows.length){
                                checkedOut.append(", ");
                            }

                            Patron patron = (Patron) libraryDataBase.getUser(currentUserID);

                            patron.borrowBook(map, Integer.parseInt((String)tablemodel.getValueAt(table.convertRowIndexToModel(rows[i]), 2)));

                            backend.Book book = new backend.Book(Integer.parseInt((String)tablemodel.getValueAt(table.convertRowIndexToModel(rows[i]), 2)));

                            libraryDataBase.checkOutBook(book);
                        }
                        else{
                            unavailiableBook.append(tablemodel.getValueAt(table.convertRowIndexToModel(rows[i]), 0));

                            if((i + 1) != rows.length){
                                unavailiableBook.append(", ");
                            }
                        }

                    }
                }
                else{
                    checkedOut.append("Error --- Book Limit: 5");
                    unavailiableBook.append("");
                }

                text.setText(checkedOut.toString());
                text2.setText(unavailiableBook.toString());

            }
        });

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        addBookButton = new JButton("Add Book");
        panel.add(addBookButton, constraints);  //Add linked to sign in button. If backend.Patron don't show. If librarian show
        addBookButton.setVisible(true);

        JButton logoutFromTableButton = new JButton("Logout");
        constraints.ipadx = 5;
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;

        panel.add(logoutFromTableButton, constraints);

        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "addBook");
                text.setText("");
                text2.setText("");
                table.clearSelection();
            }
        });

        logoutFromTableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "signin");
                text.setText("");
                text2.setText("");
                table.clearSelection();
            }
        });

        return panel;

    }

    public JPanel addBooksPage(){
        JPanel addBookPanel = new JPanel(new GridBagLayout());
        addBookPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));
        addBookPanel.setBackground(Color.WHITE);
        addBookPanel.setPreferredSize(new Dimension(250, 150));

        JLabel bookTitle = new JLabel("Book Title: ");
        JLabel bookAuthor = new JLabel("Book Author: ");
        JLabel bookISBN = new JLabel("Book ISBN: ");

        JTextField title = new JTextField(10);
        JTextField author = new JTextField(10);
        JTextField isbn = new JTextField(10);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.ipady = 6;
        constraints.gridx = 0;
        constraints.gridy = 0;
        addBookPanel.add(bookTitle, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(title, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = 6;
        addBookPanel.add(bookAuthor, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(author, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.ipady = 6;
        addBookPanel.add(bookISBN, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(isbn, constraints);

        JButton addBookButton = new JButton("Add");
        addBookButton.setPreferredSize(new Dimension(65, 25));

        constraints.gridy = 3;
        addBookPanel.add(addBookButton, constraints);

        JPanel addBookPanelWrapper = new JPanel(new GridBagLayout());
        addBookPanelWrapper.setBackground(Color.LIGHT_GRAY);
        Color PURPLE = new Color(102, 0, 153);
        addBookPanelWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        addBookPanelWrapper.add(addBookPanel);


        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backend.Book book = new backend.Book(backend.Book.BookStatus.AVAILABLE, title.getText(), author.getText(), Integer.parseInt(isbn.getText()), false, null);

                libraryDataBase.dbAddBook(book);

                tablemodel.addRow(new Object[]{book.getTitle(), book.getAuthor(), Integer.toString(book.getISBN())}); //Add new data to table

                card.show(cardContainer, "searchScreen");

                title.setText("");
                author.setText("");
                isbn.setText("");
            }
        });


        return addBookPanelWrapper;
    }

    public void updateTableFilter(){ //Updating filter based on entered text in search bar
        RowFilter<DefaultTableModel, Object> updatedFilter = null;

        try {
            updatedFilter = RowFilter.regexFilter("(?i)" + filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }

        rowSorter.setRowFilter(updatedFilter);
    }

    public JPanel createReturnBookScreen(){
        JPanel returnedBooksPanel = new JPanel(new GridBagLayout());
        returnedBooksPanel.setBackground(Color.WHITE);
        returnedBooksPanel.setPreferredSize(new Dimension(400, 200));
        returnedBooksPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        JLabel bookInformation = new JLabel();
        JLabel overdueBooksInformation = new JLabel();

        Patron patron = (Patron)libraryDataBase.getUser(currentUserID);

        List<String> overdueBooks = patron.getOverdueBooks(libraryDataBase.getBooks());
        List<String> returnedBooks = patron.returnBook(libraryDataBase.getBooks()); //Can create too many overDue books exception here

        StringBuilder returned = new StringBuilder();

        for(int i = 0; i < returnedBooks.size(); i++){
            returned.append(returnedBooks.get(i));

            if((i + 1) < returnedBooks.size()){
                returned.append(", ");
            }
        }

        StringBuilder overdue = new StringBuilder();

        if(!overdueBooks.isEmpty()){
            for(int i = 0; i < overdueBooks.size(); i++){
                overdue.append(overdueBooks.get(i));

                if((i + 1) < overdueBooks.size()){
                    overdue.append(", ");
                }
            }
        }

        bookInformation.setText("Returned Books: " + returned);
        overdueBooksInformation.setText("Overdue Books: " + overdue);

        GridBagConstraints constraints = new GridBagConstraints();

        returnedBooksPanel.add(bookInformation, constraints);

        constraints.gridy = 1;
        returnedBooksPanel.add(overdueBooksInformation, constraints);

        JPanel returnedBooksPanelWrapper = new JPanel(new GridBagLayout());
        returnedBooksPanelWrapper.setBackground(Color.LIGHT_GRAY);
        Color PURPLE = new Color(102, 0, 153);
        returnedBooksPanelWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        returnedBooksPanelWrapper.add(returnedBooksPanel);

        JButton next = new JButton("Next");
        constraints.gridy = 2;
        returnedBooksPanel.add(next, constraints);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "searchScreen");
            }
        });

        return returnedBooksPanelWrapper;
    }

    public void createDataBase(){ //Creating the database from here
        libraryDataBase = new Tables();

        backend.Book book = new backend.Book(backend.Book.BookStatus.AVAILABLE, "RedRiding", "Hancock" , 23214, false, null);
        libraryDataBase.dbAddBook(book);

        book = new backend.Book(backend.Book.BookStatus.AVAILABLE, "RedRiding1", "Hancock1" , 232142, false, null);
        libraryDataBase.dbAddBook(book);

        book = new backend.Book(backend.Book.BookStatus.AVAILABLE, "RedRiding2", "Hancock2" , 232141, false, null);
        libraryDataBase.dbAddBook(book);

    }


    public static void main(String[] args) {

        new LibraryUI(); //Create application
    }
}
