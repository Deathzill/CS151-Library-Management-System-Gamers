package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LibraryUI {
    private JFrame frame;
    private JPanel createdSignInPage;
    private JPanel createdSignUpPage;
    private JPanel createdLoggedInPage;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private CardLayout card;
    private JPanel cardContainer;
    private CardLayout usernameCard;
    private JPanel usernameScreen;
    private String generatedUsername;
    private JTextField newFirstName;
    private JTextField newLastName;
    private JTextField newEmail;
    private JPasswordField newPassword;

    public LibraryUI(){

        frame = new JFrame("Social Network - Sign In Page");
        frame.setLayout(new BorderLayout());
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //array = new String[5];

        card = new CardLayout();
        cardContainer = new JPanel(card);

        createdSignInPage = this.createSignInPage();
        createdSignUpPage = this.createSignUpPage();

        cardContainer.add(createdSignInPage, "signin");
        cardContainer.add(createdSignUpPage, "signUpPage");
        //cardContainer.add(this.createdLoggedInPage, "loggedInPage");

        frame.add(cardContainer);

        frame.setVisible(true);

        //cardContainer.add(this.createLoggedInScreen(), "loggedInPage");

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

        JPanel loginWrapper = new JPanel();
        loginWrapper.setBackground(Color.WHITE);
        loginWrapper.setLayout(new GridBagLayout());
        loginWrapper.setPreferredSize(new Dimension(100, 100));

        c.gridx = 1;
        c.gridy = 1;
        loginWrapper.add(loginPanel, c);

        c.gridy = 0;
        loginWrapper.add(myspaceLogo, c);
        loginWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true), BorderFactory.createLineBorder(Color.BLACK, 2, true)));

        JPanel finalFrontPage = new JPanel();
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

                if(usernameInput.getText().equals(generatedUsername) && passwordInput.getText().equals(newPassword.getText())){
                    card.show(cardContainer, "loggedInPage");
                }
                else{
                    if(usernameInput.getText().equals(generatedUsername)){
                        out = "Incorrect Username...";
                        text.setText(out);
                    }
                    else if(passwordInput.getText().equals(newPassword.getText())){
                        out = "Incorrect Password...";
                        text.setText(out);
                    }
                }

            }
        });

        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                card.show(cardContainer, "signUpPage");
                usernameCard.show(usernameScreen, "signingUp");
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

        newFirstName = new JTextField();
        newFirstName.setPreferredSize(new Dimension(40, 20));

        newLastName = new JTextField();
        newLastName.setPreferredSize(new Dimension(40, 20));

        newEmail = new JTextField();
        newEmail.setPreferredSize(new Dimension(40, 20));

        newPassword = new JPasswordField();
        newPassword.setPreferredSize(new Dimension(40, 20));

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 2;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        signUpPage.add(firstName, c);

        c.gridy = 1;
        signUpPage.add(lastName, c);

        c.gridx = 3;
        c.gridy = 0;
        signUpPage.add(newFirstName, c);

        c.gridy = 1;
        signUpPage.add(newLastName, c);

        c.gridy = 2;
        c.gridx = 0;
        signUpPage.add(email, c);

        c.gridx = 3;
        signUpPage.add(newEmail, c);

        c.gridy = 3;
        signUpPage.add(newPassword, c);

        c.gridx = 0;
        signUpPage.add(createNewPassword, c);

        c.gridy = 4;
        c.gridx = 3;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        signUpPage.add(createAccount, c);

        c.gridy = 5;
        c.gridx = 0;
        signUpPage.add(passwordError, c);

        JLabel signUpHeader = new JLabel("- - - - - - Sign Up - - - - - -");
        signUpHeader.setVisible(true);
        JPanel signUpPageWrapper = new JPanel(new GridBagLayout());
        Color PURPLE = new Color(102, 0, 153);
        signUpPageWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        signUpPageWrapper.setBackground(Color.LIGHT_GRAY);

        usernameCard = new CardLayout();
        usernameScreen = new JPanel(usernameCard);
        usernameScreen.add(signUpPage, "signingUp");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        signUpPageWrapper.add(usernameScreen, constraints);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        signUpPageWrapper.add(signUpHeader);

        JPanel loggedIn = new JPanel();
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
                    generatedUsername = LibraryUI.createUsername(newFirstName.getText(), newLastName.getText());
                    username.setText("Generated Username: " + generatedUsername);
                    usernameCard.show(usernameScreen, "loginUsername");
                    cardContainer.add(LibraryUI.this.createLoggedInScreen(), "loggedInPage");
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

        JLabel displayUsername = new JLabel();
        displayUsername.setText("User: " + generatedUsername);
        displayUsername.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayFirstName = new JLabel("First Name: " + newFirstName.getText());
        displayFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayLastName = new JLabel("Last Name: " + newLastName.getText());
        displayLastName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel displayEmail = new JLabel("Email: " + newEmail.getText());
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

        JPanel loggedInPageWrapper = new JPanel(new GridBagLayout());
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

    public static String createUsername(String firstName, String lastName){
        Random random = new Random();
        StringBuilder username = new StringBuilder();

        username.append(firstName.charAt(0));
        username.append(lastName.charAt(0));
        username.append("-");
        username.append(String.format("%04d", random.nextInt(9999)));

        return username.toString();
    }

    public static boolean checkPasswordRequirements(String password) throws PasswordException {
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
        new LibraryUI();
    }
}
