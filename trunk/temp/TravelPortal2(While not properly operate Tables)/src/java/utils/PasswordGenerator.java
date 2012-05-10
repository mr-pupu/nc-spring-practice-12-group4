/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author allan
 */
public class PasswordGenerator {
    private static final Random rnd = new Random();
    private static final char[] alphabet = {'0','1','2','3','4','5','6','7','8',
        '9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q', 
    'R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j',
    'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                
    public static String createPassword(int length){
        if(length>5){
            char[] pass = new char[length];
            for(int i=0; i<length; i++){
                pass[i] = alphabet[rnd.nextInt(alphabet.length)];
            }
            return String.valueOf(pass);
        }
        else{
            throw new RuntimeException("Invalid password length");
        }
    }
}
