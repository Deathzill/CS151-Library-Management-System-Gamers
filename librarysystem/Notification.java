package librarysystem;
import java.util.Date;

public class Notification {
    private String message;
    private User recipient;
    private Date dateSent;

    public Notification(String message, User recipient, Date dateSent) {
        this.message = message;
        this.recipient = recipient;
        this.dateSent = dateSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public void sendNotification(){
        //This is the same case as the login() and logout() methods in the User class. Refer to them
    }


}
