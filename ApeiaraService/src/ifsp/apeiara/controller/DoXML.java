package ifsp.apeiara.controller;

import ifsp.apeiara.model.Request;
import ifsp.apeiara.model.User;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

final class DoXML {
// Request
	protected Request readRequest(final String xml) {
		// Reads a request XML
		Request req = new Request();

		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(new InputSource(new StringReader(xml)));

			Element doc = dom.getDocumentElement();

			req.setID(Integer.parseInt(getTagValue(doc, "ID")));
			req.setDATE(getTagValue(doc, "DATE"));
			req.setTYPE(getTagValue(doc, "TYPE"));
			req.setSTATUS(getTagValue(doc, "STATUS"));
			req.setLATITUDE(getTagValue(doc, "LATITUDE"));
			req.setLONGITUDE(getTagValue(doc, "LONGITUDE"));
			req.setCUIDANDO(new User(Integer.parseInt(getTagValue(doc,
					"CUIDANDO")), "", "", "", "", "", null));
			req.setCUIDADOR(new User(Integer.parseInt(getTagValue(doc,
					"CUIDADOR")), "", "", "", "", "", null));
			// req.setCUIDADORES(getTags(doc, "CUIDADORES","ID"));

		} catch (Exception ioe) {
			System.err.println(ioe.getMessage());
		}

		return req;
	}

	protected String writeRequest(final Request req) throws XmlPullParserException {

	        XmlSerializer serializer = XmlPullParserFactory.newInstance().newSerializer();
	        StringWriter writer = new StringWriter();
	        try {
	            serializer.setOutput(writer);
	            serializer.startDocument("UTF-8", true);

	            serializer.startTag("", "REQUEST");
	                 serializer.startTag("", "ID");
	                     serializer.text(String.valueOf(req.getID()));
	                 serializer.endTag("", "ID");

	                serializer.startTag("", "DATE");
	                    serializer.text(req.getDATE());
	                serializer.endTag("", "DATE");

	                serializer.startTag("", "TYPE");
	                     serializer.text(req.getTYPE());
	                serializer.endTag("", "TYPE");

	                serializer.startTag("", "STATUS");
	                      serializer.text(req.getSTATUS());
	                serializer.endTag("", "STATUS");

	                serializer.startTag("", "LATITUDE");
	                     serializer.text(req.getLATITUDE());
	                serializer.endTag("", "LATITUDE");

	                serializer.startTag("", "LONGITUDE");
	                    serializer.text(req.getLONGITUDE());
	                serializer.endTag("", "LONGITUDE");

	                serializer.startTag("", "CUIDANDO");
	                     serializer.text(String.valueOf(req.getCUIDANDO().getID()));
	                serializer.endTag("", "CUIDANDO");
	                
	                serializer.startTag("", "CUIDANDO_NAME");
                    serializer.text(req.getCUIDANDO().getNAME());
                    serializer.endTag("", "CUIDANDO_NAME");
                    
                    
	                serializer.startTag("", "CUIDANDO_PHONE");
                    serializer.text(req.getCUIDANDO().getPHONE());
                    serializer.endTag("", "CUIDANDO_PHONE");

	                 serializer.startTag("", "CUIDADOR");
	                      serializer.text(String.valueOf(req.getCUIDADOR().getID()));
	                serializer.endTag("", "CUIDADOR");

	              /*  serializer.startTag("", "CUIDADORES");
	                    for (User cuidador : req.getCUIDADORES()) {
	                        serializer.startTag("", "ID");
	                        serializer.text(String.valueOf(cuidador.getID()));
	                        serializer.endTag("", "ID");
	                    }
	                serializer.endTag("", "CUIDADORES");
	              */

	            serializer.endTag("", "REQUEST");
	            serializer.endDocument();


	            return writer.toString();

	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	
//User	
	protected User readLogin(final String xml) {
		User us = new User();
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(new InputSource(new StringReader(xml)));

			Element doc = dom.getDocumentElement();

			us.setEMAIL(getTagValue(doc, "EMAIL"));
			us.setPASS(getTagValue(doc, "PASSWORD"));

		} catch (Exception ioe) {
			System.err.println(ioe.getMessage());
		}

		return us;
	}
	
    protected User readUser(final String xml){
		
		User us = new User();
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the
			// XML file
			dom = db.parse(new InputSource(new StringReader(xml)));

			Element doc = dom.getDocumentElement();

			us.setID(Integer.parseInt(getTagValue(doc, "ID")));
			us.setNAME(getTagValue(doc, "NAME"));
			us.setCATEGORY(getTagValue(doc, "CATEGORY"));
			us.setEMAIL(getTagValue(doc, "EMAIL"));
			us.setPHONE(getTagValue(doc, "PHONE"));

		} catch (Exception ioe) {
			System.err.println(ioe.getMessage());
		}

		return us;
	}
	
    protected String writeUser(final User user) throws XmlPullParserException {
		
		  XmlSerializer serializer = XmlPullParserFactory.newInstance().newSerializer();

		StringWriter writer = new StringWriter();
		try {

			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);

			serializer.startTag("", "USER");
			
			serializer.startTag("", "ID");
			serializer.text(String.valueOf(user.getID()));
			serializer.endTag("", "ID");

			serializer.startTag("", "NAME");
			serializer.text(user.getNAME());
			serializer.endTag("", "NAME");
			
			serializer.startTag("", "CATEGORY");
			serializer.text(user.getCATEGORY());
			serializer.endTag("", "CATEGORY");
			
			serializer.startTag("", "EMAIL");
			serializer.text(user.getEMAIL());
			serializer.endTag("", "EMAIL");
			
			serializer.startTag("", "PHONE");
			serializer.text(user.getPHONE());
			serializer.endTag("", "PHONE");

			serializer.endTag("", "USER");
			serializer.endDocument();

			return writer.toString();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

// Auxiliary functions to retrieve tag's value
	private  String getTagValue(final Element doc, final String tag) {
		String value = null;

		NodeList nl = doc.getElementsByTagName(tag);
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			value = nl.item(0).getFirstChild().getNodeValue();
		}
		return value;
	}
	@SuppressWarnings("unused")
	private  List<User> getTags(final Element doc, final String tag,
			final String subTag) {
		List<User> users = new ArrayList<User>();
		User user = new User();

		// Gets Cuidadores TAG
		NodeList nl = doc.getElementsByTagName(tag);

		// Gets Cuidadores ID
		NodeList subNL = ((Element) nl.item(0)).getElementsByTagName(subTag);

		for (int i = 0; i < subNL.getLength(); i++) {
			user.setID(Integer.parseInt(subNL.item(i).getFirstChild()
					.getNodeValue()));
			users.add(user);
		}

		return users;
	}
}
