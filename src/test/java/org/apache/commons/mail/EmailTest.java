package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EmailTest{
	
	private static final String[] TEST_EMAILS = { "ab@bc.com", "a.b@c.org", 
			"abcdefghijklmnopqrst@abcdefghifklmnopqrst.com.bd" 
			};
			
	private EmailConcrete email;
	private static final String TESTemail = "abc@cb.com";
	
	@Before
	public void setUpEmailTest() throws Exception {
		email = new EmailConcrete();
		
	}
	
	@After
	public void tearDownEmailTest() throws Exception {
		
		
	}

	@Rule
    public ExpectedException thrown = ExpectedException.none();

	
	@Test
	public void testAddBcc() throws Exception{
		
		email.addBcc(TEST_EMAILS);
		
		assertEquals(3, email.getBccAddresses().size());
		
		
	}
	
	@Test
	public void testAddCc() throws Exception{
		
		email.addCc(TESTemail);
		
		assertEquals(1, email.getCcAddresses().size());
		
	}
	
	@Test
	public void testAddHeader() throws Exception{
		final String header = "header";
		
		email.addHeader(header, header);
		assertEquals(1, email.headers.size());
		
	}
	
	@Test
	public void testAddHeader2() throws Exception{
		
		thrown.expectMessage("name can not be null or empty");
		email.addHeader("", "header");
		
	}

	
	@Test
	public void testaddReplyTo() throws Exception{
		
		email.addReplyTo(TESTemail, "Ritoshree");
		
		assertEquals(1, email.getReplyToAddresses().size());
		
	}
	
	@Test
	public void testbuildMimeMessage() throws Exception{
	email.setHostName("localhost");
	email.setSmtpPort(8080);
	email.setFrom("a@b.com");
	email.addTo("c@d.com");
	email.setSubject("test mail");

	final String headerValue = "1234567890 1234567890 123456789 01234567890 123456789 0123456789 01234567890 01234567890";
	email.addHeader("X-LongHeader", headerValue);

	email.buildMimeMessage();

	MimeMessage msg = email.getMimeMessage();
	msg.saveChanges();

	String Subject = msg.getSubject();

	assertEquals(Subject,"test mail");
	
	}
	
	@Test
	public void testgetHostName() throws Exception{
		
		email.setHostName("Local");
		
		assertEquals("Local", email.getHostName());
		
	}
	
	@Test
	public void testgetHostName1() throws Exception{
		
		assertEquals(null, email.getHostName());
		
	}
	
	@Test
	public void testgetMailSession() throws Exception{
		final String username = "username@gmail.com";
		final String password = "password";

		Properties props = new Properties();
		props.put(EmailConstants.MAIL_HOST, "smtp.gmail.com");
		props.put(EmailConstants.MAIL_PORT, "587");
		props.put(EmailConstants.MAIL_SMTP_USER, "username@gmail.com");
		props.put(EmailConstants.MAIL_SMTP_PASSWORD, "password");
		props.put(EmailConstants.MAIL_SMTP_AUTH, "true");
		
		
		Session p = Session.getInstance(props);
		email.setMailSession(p);
		Session p1 = email.getMailSession();
		String prop = p1.getProperty(EmailConstants.MAIL_HOST);
		assertEquals(prop, "smtp.gmail.com");
	}
	
	@Test
	public void testgetMailSession2() throws Exception{
		
	    thrown.expectMessage("Cannot find valid hostname for mail session");
	    email.getMailSession();
	}
	
	
	@Test
	public void testgetSentDate() throws Exception{
		
		
	}

	@Test
	public void testgetSocketConnectionTimeout() throws Exception{		
		
		int SocketConnection = email.getSocketConnectionTimeout();
		
		assertEquals(SocketConnection, email.socketConnectionTimeout);
		
	}


	@Test
	public void testSetFrom() throws Exception{
		
		email.setFrom("ab@cd.com");
		
		assertEquals("ab@cd.com", email.getFromAddress().toString());
		
	}
	
	
	
	
	
	
}
