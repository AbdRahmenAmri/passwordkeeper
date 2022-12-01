package com.fsb.pwdkeeper.errorhandler;

import android.widget.TextView;

public class ErrorHandler {
    public static String WRONGPASSWORD = "Wrong Password";
    public static String AUTHENTICATIONFAILED = "Authentication Failed";
    public static String AUTHENTICATIONERROR = "Authentication Error";
    public static String WEAKPASSWORD = "Weak Password";

    /**
    Use this method to display error message on specified text view
    @param view TextView where you need to display error
    @param err String form ErrorHandler represent error type
     */
    public static void displayOnTextView(TextView view,String err){
        view.setText(err);
    }

}
