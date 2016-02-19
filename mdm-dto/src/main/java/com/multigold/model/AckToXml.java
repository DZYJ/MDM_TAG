package com.multigold.model;

import java.beans.XMLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.multigold.model.Ack;
import com.multigold.model.ExceptionDetail;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class AckToXml {
	
	public static String ackToXml(Ack ack){
		
		
	
		XStream xStream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
		xStream.alias("ack", Ack.class);
		xStream.alias("exception_detail", ExceptionDetail.class);
	
		xStream.setMode(XStream.NO_REFERENCES);
		
		String xml = xStream.toXML(ack);
		
//		System.out.println(xml);
		return xml;
	}

	public static void main(String[] args) {
		Ack a = new Ack();
		
		a.setTransaction_id("1000442431");
		//System.out.println(new Date().toLocaleString());
		a.setTransaction_date(new Date().toLocaleString());
		a.setBuid("8270");
		a.setPartner_code("PORTAL");
		a.setReceiver_id("0001");
		a.setReceiver_name("AckTest");
		a.setMessage_type("1");
		a.setSuccess_flag("OK");
		ExceptionDetail ed = new ExceptionDetail();
		ed.setSeq("12345");
		ed.setDetail_line("abcdefg");
		List<ExceptionDetail> edlist = new ArrayList<ExceptionDetail>();
		edlist.add(ed);
		a.setException_details(edlist);
		
		System.out.println(ackToXml(a));
	}

}
