package utils;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class MailSender {

    static final Properties conf = new Properties();

    static {
        try {
            conf.load(new FileInputStream("config.properties"));
        } catch (IOException ex) {
            System.err.println("Check configuration file");
        }
    }
    static final String SMTP_OK_CODE = conf.getProperty("SMTPOkCode");

    static public boolean send(String from, String to, String subject, String data) {
        
        final int SMTP_PORT = Integer.parseInt(conf.getProperty("port"));
        final String localhostName = conf.getProperty("localhostName");
        final String mailhost = conf.getProperty("mailhost");

        
        try
        {
            Socket smtpPipe = new Socket(mailhost, SMTP_PORT);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(smtpPipe.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(smtpPipe.getOutputStream()), true);
            in.readLine();
            out.println("HELO " + localhostName);
            if (!okReturned(in.readLine())) {
                return false;
            }
            out.println("MAIL FROM:<" + from + ">");
            if (!okReturned(in.readLine())) {
                return false;
            }
            out.println("RCPT TO:<" + to + ">");
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
            out.println("QUIT");
            
        } catch (IOException ex) {
            System.err.println("Unable to connect to the SMTP server");
            return false;
        }
        return true;
    }

    private static boolean okReturned(String answer) {
        return answer.contains(SMTP_OK_CODE);
    }
}
