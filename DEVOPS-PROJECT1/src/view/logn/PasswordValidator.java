package view.logn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static boolean validatePassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
