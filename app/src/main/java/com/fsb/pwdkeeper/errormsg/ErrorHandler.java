package com.fsb.pwdkeeper.errormsg;

import android.widget.TextView;

public class ErrorHandler {
    public static String WRONGPASSWORD = "Wrong Password";
    public static String AUTHENTICATIONFAILED = "Authentication Failed";
    public static String AUTHENTICATIONERROR = "Authentication Error";


    public static void displayOnTextView(TextView view,String err){
        view.setText(err);
    }

}
