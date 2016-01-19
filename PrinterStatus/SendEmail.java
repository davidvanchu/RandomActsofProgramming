import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendEmail {
	Properties props;
	Session session;
	String creds,credsp;
	public SendEmail(String creds, String credsp) {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		this.creds = creds;
		this.credsp = credsp;
		Authenticator auth = new SMTPAuthenticator();
        session = Session.getDefaultInstance(props, auth);
	}
	
	public static void main(String[] args) {
	}
	
	public boolean sendMessage(String subject, String body) throws IOException {
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(creds + "@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(creds + "@gmail.com"));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			System.out.println("Sent email");
		} catch (MessagingException e) {
			System.out.println("Message not sent.");
			JOptionPane.showMessageDialog(null, "Credentials invalid.");
			System.exit(-1);
			return false;
			//throw new RuntimeException(e);
		}
		return true;
		
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = creds;
           String password = credsp;
           return new PasswordAuthentication(username, password);
        }
    }
}