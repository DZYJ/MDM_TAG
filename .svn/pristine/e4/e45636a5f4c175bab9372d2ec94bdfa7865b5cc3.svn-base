package com.multigold.mdm.service.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

@Path("rest/demo")
@Component
public class RestServiceDemo {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/query/hello")
	public Response hello(Map map){
		System.out.println(map.get("lspCode"));
		Hello hello = new Hello();
		return Response.status(Status.OK).entity(hello).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/query/hello1")
	public Response hello(Hello hello){
		System.out.println("111111111");
		ResponseTest resp = new ResponseTest();
		resp.setRequestId("12345");
		resp.setStatus("DL");
		
		return Response.status(Status.OK).entity(resp).build();
	}
}
