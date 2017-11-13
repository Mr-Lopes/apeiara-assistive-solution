package com.example.ifsp.apeiara.controll;

import android.util.Log;
import android.util.Xml;

import com.example.ifsp.apeiara.model.Request;
import com.example.ifsp.apeiara.model.User;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Henrique on 3/21/2016.
 */
public class DoXML {


    public static String writeRequest(final Request req) {

        XmlSerializer serializer = Xml.newSerializer();
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

            Log.d("DoXML", "XML written.");

            return writer.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeLogin(final User user) {

        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);

            serializer.startTag("", "LOGIN");
            serializer.startTag("", "EMAIL");
            serializer.text(user.getEMAIL());
            serializer.endTag("", "EMAIL");

            serializer.startTag("", "PASSWORD");
            serializer.text(user.getPASS());
            serializer.endTag("", "PASSWORD");

            serializer.endTag("", "LOGIN");
            serializer.endDocument();

            Log.d("DoXML", "XML written.");

            return writer.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeUser(final User user)  {

        XmlSerializer serializer = Xml.newSerializer();

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

    public static Request readRequest(final String xml) {
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
            req.setCUIDANDO(new User(Integer.parseInt(getTagValue(doc,"CUIDANDO")), getTagValue(doc,"CUIDANDO_NAME"), "", "", "", getTagValue(doc,"CUIDANDO_PHONE")));
            req.setCUIDADOR(new User(Integer.parseInt(getTagValue(doc,"CUIDADOR")), "", "", "", "",""));
            // req.setCUIDADORES(getTags(doc, "CUIDADORES","ID"));

        } catch (Exception ioe) {
            System.err.println(ioe.getMessage());
        }

        return req;
    }

    public static User readLogin(final String xml) {
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
            us.setCATEGORY(getTagValue(doc,"CATEGORY"));
            us.setEMAIL(getTagValue(doc,"EMAIL"));
            us.setPHONE(getTagValue(doc, "PHONE"));


        } catch (Exception ioe) {
            System.err.println(ioe.getMessage());
        }

        return us;
    }


    // Auxiliary functions to retrieve tag's value
    private static String getTagValue(final Element doc, final String tag) {
        String value = null;

        NodeList nl = doc.getElementsByTagName(tag);
        if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
            value = nl.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
}
