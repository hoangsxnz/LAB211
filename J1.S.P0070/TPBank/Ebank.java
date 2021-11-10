/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPBank;

import Utility.GetValidInput;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author hoangson
 */
public class Ebank {

    public HashMap<String, String> ACCOUNT_INFO;

    public Ebank() {
        ACCOUNT_INFO = new HashMap<>();
    }

    public void mockAccount() {
        ACCOUNT_INFO.put("0123456789", "abcd1234");
        ACCOUNT_INFO.put("9876543210", "1234abcd");
        ACCOUNT_INFO.put("6666688888", "abcd6868");
    }

    public String generateCaptcha() {
        Random rand = new Random();
        String captcha = "";
        String characters = "0123456789";
        char c = 'A';
        // loop to add all uppercase and lowercase characters to the String "0123456789"
        for (int i = 0; i < 26; ++i) {
            characters += Character.toString(c).toLowerCase() + c;
            c += 1;
        }
        // loop until captcha format is correct (length is 5 and alphanumeric)
        while (true) {
            // loop from the first to the last character
            for (int i = 0; i < 5; ++i) {
                // random a number in range (0,length-1) and add the number-th character to captcha
                int index = rand.nextInt(characters.length());
                captcha += characters.charAt(index) + "";
            }
            /*
            \w matches any word character
            {5} matches 5 times of \w token
             */
            if (captcha.matches("\\w{5}")) {
                break;
            }
        }
        return captcha;
    }

    public void login(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("TPBank.Language", locale);
        /*
        ^ matches the beginning of string
        \d matches any digit character (0-9)
        {10} maches 10 times of \d token
        $ matches the end of string
         */
        String accountNumber = GetValidInput.getStringByRegex(bundle.getString("account"), bundle.getString("account.error"), "^\\d{10}$");
        String password = GetValidInput.getPassword(bundle.getString("password"), bundle.getString("password.error"));
        // loop until user enter correct captcha
        while (true) {
            String captcha = generateCaptcha();
            System.out.println("Captcha: " + captcha);
            String captchaInput = GetValidInput.getStringByRegex(bundle.getString("captcha"), bundle.getString("captcha.error"), "\\w{5}");
            // check if user enter correct captcha
            if (captchaInput.equals(captcha)) {
                break;
            } else {
                System.err.println(bundle.getString("captcha.incorrect"));
            }
        }
        // check if input account number exists or not
        if (!ACCOUNT_INFO.containsKey(accountNumber)) {
            System.err.println(bundle.getString("accountNotExist"));
        } // check if password is correct
        else if (!password.equals(ACCOUNT_INFO.get(accountNumber))) {
            System.err.println(bundle.getString("password.incorrect"));
        } else {
            System.out.println(bundle.getString("login.success"));
        }
    }
}
