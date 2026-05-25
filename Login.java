import java.util.regex.*;

public class Login {

    private String username;
    private String password;
    private String cellPhoneNumber;

    // Constructor
    public Login(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password.length() < 8) return false;
        boolean hasCapital = false, hasNumber = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber.matches("^\\+[0-9]{1,3}[0-9]{6,10}$")
                && (cellPhoneNumber.length() - 1) <= 10;
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) 
            && this.password.equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + enteredUsername + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCellPhoneNumber() { return cellPhoneNumber; }
}