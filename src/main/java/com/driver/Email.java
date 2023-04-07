package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword.equals(password)){
            if(isValidPassword(newPassword)){
                this.password=newPassword;
                System.out.println("Password changed successfully!");
            }
            else{
                System.out.println("Given password is not valid!");
            }
        }
        else{
            System.out.println("Current password doesn't matches with the old password!");
        }
    }

    public boolean isValidPassword(String password){
        boolean upperCase=false;
        boolean lowerCase=false;
        boolean digit=false;
        boolean special=false;
        int n=password.length();
        if(n<8){
            return false;
        }
        for(int i=0;i<n;i++){
            if(password.charAt(i)>='A' && password.charAt(i)<='Z'){
                upperCase=true;
            }
            else if(password.charAt(i)>='a' && password.charAt(i)<='z'){
                lowerCase=true;
            }
            else if(password.charAt(i)>='0' && password.charAt(i)<='9'){
                digit=true;
            }
            else{
                special=true;
            }
        }

        if(lowerCase && upperCase && digit && special){
            return true;
        }
        return false;
    }
}