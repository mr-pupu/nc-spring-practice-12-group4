package utils;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

public class MailSender {

    static final Properties serverConfig = new Properties();
        
    static {
        try {
            serverConfig.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
        }
    }
    
    final static String localhostName = serverConfig.getProperty("localhostName");
    final static String mailhost = serverConfig.getProperty("mailhost");
    final static String sender = serverConfig.getProperty("noreply");
    final static String SMTP_OK_CODE = serverConfig.getProperty("SMTPOkCode");
    final static int SMTP_PORT = Integer.parseInt(serverConfig.getProperty("port"));
    
    static final String mail_footer = "If you think you have "
            + "received this email by accident, please"
            + " contact the aministrator (admin@hardosoft.com)";

    static public boolean send(List<String> recipients, String subject, String data) {


        try {
            Socket smtpPipe = new Socket(mailhost, SMTP_PORT);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(smtpPipe.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(smtpPipe.getOutputStream()), true);
            for (String recipient : recipients) {
                in.readLine();
                out.println("HELO " + localhostName);
                if (!okReturned(in.readLine())) {
                    return false;
                }
                out.println("MAIL FROM:<" + sender + ">");
                if (!okReturned(in.readLine())) {
                    return false;
                }
                out.println("RCPT TO:<" + recipient + ">");
                if (!okReturned(in.readLine())) {
                    return false;
                }
                out.println("DATA");
                out.println("Subject:" + subject);
                out.println(data);
                out.println(".");
                in.readLine();
                if (!okReturned(in.readLine())) {
                    return false;
                }
            }
            out.println("QUIT");

        } catch (IOException ex) {
            System.err.println("Unable to connect to the SMTP server. "
                    + "Check mail server configuration");
            return false;
        }
        return true;
    }

    private static boolean okReturned(String answer) {
        return answer.contains(SMTP_OK_CODE);
    }
}
