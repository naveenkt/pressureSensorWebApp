package org.siemens.pressureSensorMonitoringCloud;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




import javax.ws.rs.core.Response;

import com.microsoft.windowsazure.Configuration;
//Include the following imports to use Service Bus APIs
import com.microsoft.windowsazure.services.servicebus.*;
import com.microsoft.windowsazure.services.servicebus.models.*;
import com.microsoft.windowsazure.core.*;
import com.microsoft.windowsazure.exception.ServiceException;


import javax.xml.datatype.*;

@Path("/hello")
public class pressureSensorRestApi {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		
		
		Configuration config =  ServiceBusConfiguration.configureWithSASAuthentication(
			            "siemensbusns",
			            "RootManageSharedAccessKey",
			            "YMv+pxgk8yqKsZY2KrnelyyyDLAg7FQaRERmUTavRLY=",
			            ".servicebus.windows.net"
			            );
		ServiceBusContract service = ServiceBusService.create(config);
		try
		{
			QueueInfo queueInfo = new QueueInfo("TestQueue");
			try
			{
			    BrokeredMessage message = new BrokeredMessage("sn1");
			    service.sendQueueMessage("TestQueue", message);
			}
			catch (ServiceException e)
			{
			}
		}
		catch(Exception ex)
		{
			//System.out.println("Error "+ ex);
		}
		return "Hello from sensor api cloud New Naveen";
	}
	
	
	@POST
	@Path("/postsensordata")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response pushdata(String mesg) {

		String result = "Time series data saved : " + mesg;
		Configuration config =  ServiceBusConfiguration.configureWithSASAuthentication(
	            "siemensbusns",
	            "RootManageSharedAccessKey",
	            "YMv+pxgk8yqKsZY2KrnelyyyDLAg7FQaRERmUTavRLY=",
	            ".servicebus.windows.net"
	            );
		ServiceBusContract service = ServiceBusService.create(config);
		try
		{
			QueueInfo queueInfo = new QueueInfo("TestQueue");
			try
			{
				BrokeredMessage message = new BrokeredMessage("sn1");
				service.sendQueueMessage("TestQueue", message);
			}
			catch (ServiceException e)
			{
			}
		}
		catch(Exception ex)
		{
			//System.out.println("Error "+ ex);
		}
		return Response.status(200).entity(result).build();

	}
	
	/*
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProductInJSON(product p1) {

		String result = "Product created : " + p1.getName();
		return Response.status(201).entity(result).build();

	}
	*/
	

}
