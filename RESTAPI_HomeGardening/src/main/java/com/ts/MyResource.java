package com.ts;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dao.GardenerDAO;
import com.dao.ItemDAO;
import com.dao.UserDAO;
import com.dto.GardenerDetails;
import com.dto.ItemDetails;
import com.dto.UserDetails;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	
	@Path("Hi")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hi() {
		return "Hi Service!";
	}

	
	@Path("registerUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerUser(UserDetails user) {
		System.out.println("Data Recieved in User Register : " + user);
		UserDAO userDao = new UserDAO();
		userDao.register(user);
	}

	
	@Path("registerGardener")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerGardener(GardenerDetails gardener) {
		System.out.println("Data Recieved in Gardener Register : " + gardener);
		GardenerDAO gardenerDao = new GardenerDAO();
		gardenerDao.register(gardener);
	}
	
	@Path("getUserByPass/{userEmail}/{userPassword}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUserByPass(@PathParam("userEmail") String userEmail,@PathParam("userPassword") String userPassword) {
		System.out.println("Recieved path params: "+userEmail+userPassword); 
		UserDAO userDAO = new UserDAO();
		return userDAO.getUserByPass(userEmail, userPassword);
	} 
	
	@Path("getGardenerByUserPass/{gardenerEmail}/{gardenerPassword}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getGardenerByUserPass(@PathParam("gardenerEmail") String gardenerEmail,@PathParam("gardenerPassword") String gardenerPassword) {
		System.out.println("Recieved path params: "+gardenerEmail+gardenerPassword); 
		GardenerDAO gardenerDAO = new GardenerDAO();
		return gardenerDAO.getGardenerByUserPass(gardenerEmail, gardenerPassword);
	} 
	
	@Path("uploadImage")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadImage(@FormDataParam("itemImage") InputStream fileInputStream,@FormDataParam("itemImage") FormDataContentDisposition
	formDataContentDisposition, @FormDataParam("itemName") String itemName, @FormDataParam("itemDescription") String itemDescription) throws IOException {; 
		int read = 0;
		byte[] bytes = new byte[1024];
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		
		String pathArr[] = path.split("/WEB-INF/classes/");
		
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));
				
				
		while((read = fileInputStream.read(bytes)) != -1){	
			
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
		
		ItemDetails item = new ItemDetails();
		item.setItemName(itemName);
		item.setItemDescription(itemDescription);
		item.setItemImage(formDataContentDisposition.getFileName());
		ItemDAO itemDao = new ItemDAO();
		itemDao.additem(item);
	}
	@Path("uploadVideo")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadVideo(@FormDataParam("itemVideo") InputStream fileInputStream,@FormDataParam("itemVideo") FormDataContentDisposition
	formDataContentDisposition, @FormDataParam("itemName") String itemName, @FormDataParam("itemDescription") String itemDescription) throws IOException {; 
		int read = 0;
		byte[] bytes = new byte[1024];
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		
		String pathArr[] = path.split("/WEB-INF/classes/");
		
		FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/image/", formDataContentDisposition.getFileName()));
				
				
		while((read = fileInputStream.read(bytes)) != -1){	
			
			out.write(bytes,0,read);
		}
		out.flush();
		out.close();
		
		ItemDetails item = new ItemDetails();
		item.setItemName(itemName);
		item.setItemDescription(itemDescription);
		item.setItemVideo(formDataContentDisposition.getFileName());
		ItemDAO itemDao = new ItemDAO();
		itemDao.additem(item);
	}
	@Path("getItems")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemDetails> getItems() {

		ItemDAO itemDAO = new ItemDAO();
		
		List <ItemDetails> itemList = itemDAO.getAllItemDetailsByImage();
		return itemList;
	}
	
	@Path("getItemVideos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemDetails> getItemVideos() {

		ItemDAO itemDAO = new ItemDAO();
		List <ItemDetails> itemList = itemDAO.getAllItemDetailsByVideo();

		return itemList;
	}
	
	@Path("getAllGardeners")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<GardenerDetails> getAllGardeners() {

		GardenerDAO GardenerDAO = new GardenerDAO();
		
		List <GardenerDetails> gardenerList = GardenerDAO.getAllGardeners();
		return gardenerList;
	}
	@Path("mail")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws MessagingException {
		String subject="Test Mail";
		String body="Java mail test...";
		String email="likithamanne99@gmail.com";
		String host = "smtp.gmail.com";
		String from = "bhavya.aparna1@gmail.com";
		String pass = "nagendraV";

		Properties props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = {email}; // added this line

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress = new InternetAddress[to.length];

		// To get the array of addresses

		for( int i=0; i < to.length; i++ )
		{
			// changed from a while loop
			toAddress[i] = new InternetAddress(to[i]);
		}

		for( int i=0; i < toAddress.length; i++)
		{
			// changed from a while loop
			message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		}

		message.setSubject(subject);
		message.setText(body);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());

		transport.close();

        return "Successful";
    	}
	
	}
