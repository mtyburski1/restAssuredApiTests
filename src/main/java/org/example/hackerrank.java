package org.example;

public class hackerrank {

    public static int blabla(String code) {
        if (code == null || code.length() > 1000) {
            return 0;
        }

        StringBuilder filtered = new StringBuilder();

        // Step 1: Filter only letters and convert to lowercase
        for (char c : code.toCharArray()) {
            if (Character.isLetter(c)) {
                filtered.append(Character.toLowerCase(c));
            }
        }

        // Step 2: Check if it's a palindrome
        String original = filtered.toString();
        String reversed = filtered.reverse().toString();
        return original.equals(reversed) ? 1 : 0;
    }

    public static void main(String[] args) {
        blabla("abb!@a");
    }

}