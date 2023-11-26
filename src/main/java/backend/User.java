    package backend;

    import java.util.Date;
    import org.json.JSONObject;  // Import the JSONObject class

    public class User implements Searchable{
        private int userID;
        private String name;
        private String email;
        private String password;
        private Date dateJoined;
        private boolean logged;
        private Tables data;

        public User(int userID, String name, String email, String password, Date dateJoined) {
            this.userID = userID;
            this.name = name;
            this.email = email;
            this.password = password;
            this.dateJoined = dateJoined;
        }

        // Method to convert User object to a JSON object
        public JSONObject toJSON() {
            JSONObject jsonObject = new JSONObject();
            // Add basic user details to the JSON object
            jsonObject.put("userID", userID);
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("password", password);

            // Convert the dateJoined from Date to long for JSON representation
            jsonObject.put("dateJoined", dateJoined.getTime());

            // Return the constructed JSON object
            return jsonObject;
        }


        // Static method to create a User object from a JSON object
        public static User fromJSON(JSONObject jsonObject) {
            // Extract basic user details from the JSON object
            int userID = jsonObject.getInt("userID");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String password = jsonObject.getString("password");

            // Convert the dateJoined from long format back to Date object
            Date dateJoined = new Date(jsonObject.getLong("dateJoined"));

            // Create and return a new User object using the extracted data
            return new User(userID, name, email, password, dateJoined);
        }


        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Date getDateJoined() {
            return dateJoined;
        }

        public void setDateJoined(Date dateJoined) {
            this.dateJoined = dateJoined;
        }

        public Tables getData(){
            return data;
        }

        public void setData(Tables data){
            this.data = data;
        }

        public boolean searchByTitle(String title) {
            return data.searchBookByTitle(title);
        }

        public boolean searchByAuthor(String author) {
            return data.searchBookByAuthor(author);
        }

        public boolean searchByISBN(int ISBN){
            return data.searchBookByISBN(ISBN);
        }

    }
