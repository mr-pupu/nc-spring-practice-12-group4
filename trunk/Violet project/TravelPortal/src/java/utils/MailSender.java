package utils;

import database.utilities.MailLists;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MailSender {

    static final Properties serverConfig = new Properties();

    static {
        try {
            serverConfig.load(new FileInputStream("src/utils/config.properties"));
        } catch (IOException ex) {
        }
    }
    final static String localhostName = serverConfig.getProperty("localhostName");
    final static String mailhost = serverConfig.getProperty("mailhost");
    final static String sender = serverConfig.getProperty("noreplyAddr");
    final static String SMTP_OK_CODE = serverConfig.getProperty("SMTPOkCode");
    
    final static int SMTP_PORT = 25;//Integer.parseInt(serverConfig.getProperty("port"));
    
    static final String mail_footer = "\n\nIf you think you have "
            + "received this email by accident, please"
            + " contact the aministrator (admin@hardosoft.com)";
    public static final String rejected_subject = "Your Travel Request Form has been rejected";
    public static final String rejected_body = "Your Travel Request Form status has been changed"
            + "to rejected. Please, visit your employee desktop at Hardosoft Travel Portal to get more details.";
    public static final String approved_subject = "New travel submitted";
    public static final String approved_body = "New travel to your country submitted. "
            + "Please, check updates on your desktop at Hardosoft Travel Portal.";

    static public void notifyByMail(int status, Long trfId)
    {
        System.out.println("Status"+status+", trf id:"+ trfId);
            final int ready = 4;
            final int commit = 3;
            final int rejected = 1;
            if (status == rejected) {
                List<String> owner = MailLists.TRFOwnerMail(trfId);
                if (owner != null) {
                    System.out.println(owner.toString());
                   // MailSender.sendTo(owner, MailSender.rejected_subject, MailSender.rejected_body);
                }
            } else if (status == ready || status == commit) {
                List<String> supGroup = MailLists.notifyingGroupOnApprove(trfId);
                if (supGroup != null) {
                    System.out.println(supGroup.toString());
                   // MailSender.sendTo(supGroup, MailSender.approved_subject, MailSender.approved_body);
                }
            }
    }
    
    static public void sendTo(List<String> recipients, String subject, String body) {
        try {
            Socket smtpPipe = new Socket(mailhost, SMTP_PORT);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(smtpPipe.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(smtpPipe.getOutputStream()), true);
            in.readLine();
            out.println("HELO " + localhostName);
            if (!okReturned(in.readLine())) {
                return;
            }
            for (String recipient : recipients) {

                System.out.println(recipient);
                out.println("MAIL FROM:<" + sender + ">");
                if (!okReturned(in.readLine())) {
                    continue;
                }
                out.println("RCPT TO:<" + recipient + ">");
                if (!okReturned(in.readLine())) {
                    continue;
                }
                out.println("DATA");
                out.println("Subject:" + subject);
                out.println(body + mail_footer);
                out.println(".");
                in.readLine();
                if (!okReturned(in.readLine())) {
                    continue;
                }
            }
            out.println("QUIT");
            smtpPipe.close();

        } catch (IOException ex) {
            System.err.println("Unable to connect to the SMTP server. "
                    + "Check mail server configuration");
        }
    }

    private static boolean okReturned(String answer) {
        return answer.contains(SMTP_OK_CODE);
    }
}