package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private ArrayList<createdUser> userArray = new ArrayList<createdUser>(); //Storing the individual Users
    private int signedInUserArrayIndex;

    class createdUser{ //class to store user datatypes
        private String generatedUsername;
        private String userFirstName;
        private String userLastName;
        private String userEmail;
        private String userPassword;

        public String getGeneratedUsername() {
            return generatedUsername;
        }

        public void setGeneratedUsername(String generatedUsername) {
            this.generatedUsername = generatedUsername;
        }

        public String getUserFirstName() {
            return userFirstName;
        }

        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        public String getUserLastName() {
            return userLastName;
        }

        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }
    }


    public LibraryUI(){ //create the different panels/screens

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
        //loginPanel.setBounds(100, 100, 100, 100);
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
        JLabel myspaceLogo = new JLabel("Myspace");
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

                for(int i = 0; i < userArray.size(); i++){
                    if(usernameInput.getText().equals(userArray.get(i).getGeneratedUsername()) && passwordInput.getText().equals(userArray.get(i).getUserPassword())){
                        signedInUserArrayIndex = i;
                        String page = "loggedInPage" + signedInUserArrayIndex;
                        card.show(cardContainer, page); //Display the correct corresponding screen for the user
                        usernameInput.setText(""); //clear text
                        passwordInput.setText(""); //clear text

                        text.setText("");
                        loggedIn = true;
                        break;
                    }
                }

                if(loggedIn == false){
                    out = "Incorrect Login...";
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

        constraints.gridy = 4;
        constraints.gridx = 3;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        signUpPage.add(createAccount, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        signUpPage.add(passwordError, constraints);

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
                    signUpHeader.setVisible(false);

                    createdUser newUser = new createdUser();
                    newUser.setGeneratedUsername(LibraryUI.createUsername(newFirstName.getText(), newLastName.getText()));
                    newUser.setUserEmail(newEmail.getText());
                    newUser.setUserFirstName(newFirstName.getText());
                    newUser.setUserLastName(newLastName.getText());
                    newUser.setUserPassword(newPassword.getText());

                    newEmail.setText("");
                    newFirstName.setText("");
                    newLastName.setText("");
                    newPassword.setText("");

                    userArray.add(newUser); //Storing new user information

                    username.setText("Generated Username: " + newUser.getGeneratedUsername());
                    usernameCard.show(usernameScreen, "loginUsername");

                    String pageName = "loggedInPage" + (userArray.size() - 1);
                    signedInUserArrayIndex = (userArray.size() - 1);
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
        displayUsername.setText("User: " + userArray.get(signedInUserArrayIndex).getGeneratedUsername());
        displayUsername.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayFirstName = new JLabel("First Name: " + userArray.get(signedInUserArrayIndex).getUserFirstName());
        displayFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayLastName = new JLabel("Last Name: " + userArray.get(signedInUserArrayIndex).getUserLastName());
        displayLastName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayEmail = new JLabel("Email: " + userArray.get(signedInUserArrayIndex).getUserEmail());
        displayEmail.setHorizontalAlignment(SwingConstants.CENTER);
        JButton logoutButton1 = new JButton("Logout");

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.ipady = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        loggedInPanel.add(displayUsername, constraints);

        constraints.gridy = 1;
        loggedInPanel.add(displayFirstName, constraints);

        constraints.gridy = 2;
        loggedInPanel.add(displayLastName, constraints);

        constraints.gridy = 3;
        loggedInPanel.add(displayEmail, constraints);

        constraints.gridy = 4;
        loggedInPanel.add(logoutButton1, constraints);

        JPanel loggedInPageWrapper = new JPanel(new GridBagLayout()); //Wrapper panel for the loggedInPanel
        loggedInPageWrapper.setBackground(Color.lightGray);
        Color PURPLE = new Color(102, 0, 153);
        loggedInPageWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));

        loggedInPageWrapper.add(loggedInPanel);

        logoutButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "signin");
            }
        });


        return loggedInPageWrapper;
    }

    public static String createUsername(String firstName, String lastName){ //Method to create the user's username
        Random random = new Random();
        StringBuilder username = new StringBuilder();

        username.append(firstName.charAt(0));
        username.append(lastName.charAt(0));
        username.append("-");
        username.append(String.format("%04d", random.nextInt(9999)));

        return username.toString();
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


    public static void main(String[] args) {
        new LibraryUI(); //Create application
    }
}
