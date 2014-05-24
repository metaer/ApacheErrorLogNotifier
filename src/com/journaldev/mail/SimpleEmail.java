package com.journaldev.mail;

import java.util.Properties;

import javax.mail.Session;

public class SimpleEmail {

    public static void sendemail(String to, String theme, String message, String from) {

        System.out.println("SimpleEmail Start");

        String smtpHostServer = "127.0.0.1";
        String emailID = to;

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        EmailUtil.sendEmail(session, emailID, theme, message, from);
    }

}