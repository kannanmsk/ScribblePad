import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

/**
 *  <code>SendMail</code> - class implementing the Mail send feature 
 *  CIS 551 Modern Programming in Java
 *  Project
 * @author kannankuttalam
 * @author divyaradhakrishnanprabhakaran
 * 
 */

public class SendMail {
	
	private String to,text,subject;
	private Properties p;
 
	// constructors
	/** Constructs the destination email address and mail body 
	 * @param to To email address
	 * @param text email body
	 */
	SendMail(String to,String text)
	{
		this.to = to;
		this.text = text;
		this.subject = "Automated Message from ScibblePad";
		p = new Properties();
	}
	
	/** Email send function
     */
	public void send()
	{
		final String email= "scribblepadproject@gmail.com",pwd= "scribblepad_fall2015"; //email id created for demo purpose
		            
	    
	    p.put("mail.smtp.user", email);
	    p.put("mail.smtp.host", "smtp.gmail.com");
	    p.put("mail.smtp.port", "465");
	    p.put("mail.smtp.starttls.enable","true");
	    p.put("mail.smtp.debug", "true");
	    p.put("mail.smtp.auth", "true");
	    p.put("mail.smtps.auth", "true");
	    p.put("mail.smtp.socketFactory.port", "465");
	    p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    p.put("mail.smtp.socketFactory.fallback", "true");

	    
	    Session session = Session.getInstance(p,  
	    		   new javax.mail.Authenticator() {  
	    		   protected PasswordAuthentication getPasswordAuthentication() {  
	    		   return new PasswordAuthentication(email,pwd);
	    		   }  
	    		  });  

	    MimeMessage msg = new MimeMessage(session);
	    try {
	        msg.setSubject(this.subject);
	        msg.setText(this.text);  
	        msg.setFrom(new InternetAddress(email));
	        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
	        

	  Transport transport = session.getTransport("smtps");
	            transport.connect("smtp.gmail.com", Integer.valueOf("465"), email, pwd);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
	            JOptionPane.showMessageDialog(null, "Mail Sent Successfully :) ", "Mail Sent", 1);
	        } catch (AddressException e) {
	        	JOptionPane.showMessageDialog(null, "Mail Not Sent :( ", "Error", 1);
	            e.printStackTrace();
	    
	        } catch (MessagingException e) {
	        	JOptionPane.showMessageDialog(null, "Mail Not Sent :( ", "Error", 1);
	        	e.printStackTrace();
	       
	        }
	}
}
