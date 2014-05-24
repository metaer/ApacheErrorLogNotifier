package ru.pavelpopovjava;

import com.journaldev.mail.SimpleEmail;

public class Mailer{

    public static final boolean emulateMail = true;

    public static void sendemail(String to, String Theme, String message, String from){
        if (Mailer.emulateMail){
            message = message.replace("<br>","\n");
            System.out.print(message);
        }
        else{
            SimpleEmail.sendemail();
        }
    }
}