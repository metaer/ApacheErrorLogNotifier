package com.journaldev.mail;

import java.util.Properties;

import javax.mail.Session;

public class SimpleEmail {

    public static void sendemail() {

        System.out.println("SimpleEmail Start");

        String smtpHostServer = "127.0.0.1";
        String emailID = "pankaj@journaldev.com";

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        EmailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
    }

}