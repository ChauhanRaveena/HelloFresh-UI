package common.UIAutomationTest;

import java.util.Random;

public class GenerateRandom {

	public String getEmail() {
        String CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            str.append(CHARS.charAt(index));
        }
        String Str = str.toString();
        return Str;

    }
	
	public String getPassword() {
        String CHARS = "abcdefghijklmnopqrstuvwxyz1234567890@£$€";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            str.append(CHARS.charAt(index));
        }
        String Str = str.toString();
        return Str;
}    
   public String getMobile() {
        String CHARS = "1234567890";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            str.append(CHARS.charAt(index));
        }
        String Str = str.toString();
        return Str;

    }
}
