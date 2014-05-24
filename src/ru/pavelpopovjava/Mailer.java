package ru.pavelpopovjava;

import com.journaldev.mail.SimpleEmail;

public class Mailer{

    public static final boolean emulateMail = false;

    public static void sendemail(String to, String theme, String message, String from){
        if (Mailer.emulateMail){
            System.out.print(message);
        }
        else{
            SimpleEmail.sendemail(to, theme, message, from);
        }
    }
}