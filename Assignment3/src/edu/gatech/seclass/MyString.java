package edu.gatech.seclass;

/**
 * Created by amilkov3 on 5/24/15.
 */
public class MyString implements MyStringInterface {

    private String string;

    public void setString(String string){
        this.string = string;
    }

    public String getString(){
        return string;
    }

    public String getVowels(){
        String vowelString = "";
        for(int i = 0; i < string.length(); i++){
            char ch = string.charAt(i);
            if ("aeiouAEIOU".indexOf(ch) != -1){
                vowelString+=ch;
            }
        }
        return vowelString;
    }

    public int numberOfVowels(){
        int vowelCount = 0;
        for (int i = 0; i < string.length(); i++){
            if ("aeiouAEIOU".indexOf(string.charAt(i)) != -1){
                vowelCount+=1;
            }
        }
        return vowelCount;
    }

    public char getCharacter(int position) throws IllegalArgumentException, IllegalIndexException {
        if (position <= 0){
            throw new IllegalArgumentException();
        }
        if (string.length() < position){
            throw new IllegalIndexException();
        }
        return string.charAt(position-1);
    }

    public void flipCaseInSubstring(int startPosition, int endPosition) throws IllegalArgumentException, IllegalIndexException{
        if (string.length() < endPosition){
            throw new IllegalIndexException();
        }
        if (startPosition<= 0 || endPosition <= 0 || startPosition > endPosition){
            throw new IllegalArgumentException();
        }
        char[] chars = string.toCharArray();
        for (int i = startPosition - 1; i < endPosition; i++){
            char ch = chars[i];
            if (Character.isUpperCase(ch)){
                chars[i] = Character.toLowerCase(ch);
            }
            else if (Character.isLowerCase(ch)){
                chars[i] = Character.toUpperCase(ch);
            }
        }
        string = new String(chars);
    }
}
