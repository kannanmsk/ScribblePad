 
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *  <code>SendSMS</code> - class implementing the SMS Send feature
 *  CIS 551 Modern Programming in Java
 *  Project
 * @author kannankuttalam
 * @author divyaradhakrishnanprabhakaran
 */
//reference: https://www.twilio.com/docs/java/install
public class SendSMS {
 
	private String to,msg;
	//credentials hard-coded for testing purposes
	public static final String ACCOUNT_SID = "ACd8d7021d32f3590461c766e518236024";  
    public static final String AUTH_TOKEN = "17ef6631ced1d5ec649215f0ced3c4c4";     
    
 // constructors
 	/** Constructs the destination mobile number and text body 
 	 * @param to To mobile number
 	 * @param msg text body
 	 */
	SendSMS(String to,String msg)
	{
		this.to = to;
		this.msg = msg;
	}
	
	/** SMS send function
     */
	public void sendsms() throws TwilioRestException{
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
        Account account = client.getAccount();
 
        MessageFactory messageFactory = account.getMessageFactory();
        List<NameValuePair> messageParameters = new ArrayList<NameValuePair>();
        messageParameters.add(new BasicNameValuePair("To", this.to)); 
        messageParameters.add(new BasicNameValuePair("From", "+13158025258")); 
        messageParameters.add(new BasicNameValuePair("Body", this.msg + "\n\nDO NOT REPLY. Automated message from ScribblePad"));
        @SuppressWarnings("unused")
		Message sms = messageFactory.create(messageParameters);
        JOptionPane.showMessageDialog(null, "Text Message Sent Successfully :) ", "SMS Sent", 1);
	}
   
    
}