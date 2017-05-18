package fr.emn.elastuff.perCEPtion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * the class ParserXML is used to parse our xml file with a specific architecture.
 *
 */
public class ParserXML {

	/**
	 * read the xml file and transform it into a Collection of Request
	 * @param file our xml file
	 * @return our requests
	 * @throws JDOMException
	 * @throws IOException
	 * @throws ParserXMLException
	 */
	public Collection<Request> read(File file) throws JDOMException, IOException, ParserXMLException {
		Collection<Request> requests = new ArrayList<Request>();

		SAXBuilder sxb = new SAXBuilder();
		// parsing
		Document document = sxb.build(file);

		Element racine = document.getRootElement();
		List<?> listRequest = racine.getChildren();
		Iterator<?> i = listRequest.iterator();

		// loop on requests
		while (i.hasNext()) {
			Element element_request = (Element) i.next();

			// throw new ParserException if it is uncorrect
			checkRequest(element_request);
			
			if(element_request.getAttribute("event").getValue().equals(Event.SYMPTOM.toString())){
				SymptomRequest srequest ;
				if(element_request.getAttribute("ttl") != null)
					srequest = new SymptomRequest(element_request.getAttributeValue("name"),
										   	  	  element_request.getAttributeValue("command"),
										   	  	  Event.valueOf(element_request.getAttributeValue("event").toUpperCase()),
										   	  	  Integer.parseInt(element_request.getAttributeValue("ttl")));
				else
					srequest = new SymptomRequest(element_request.getAttributeValue("name"),
										   	  	  element_request.getAttributeValue("command"),
										   	  	  Event.valueOf(element_request.getAttributeValue("event").toUpperCase()),
										   	  	  Constant.getInstance().getDefaultTTL());
				requests.add(srequest);
			} else{
				Request request = new Request(element_request.getAttribute("name").getValue(),
										      element_request.getAttribute("command").getValue(),
											  Event.valueOf(element_request.getAttribute("event").getValue().toUpperCase()));	
				requests.add(request);
			}
				
		}

		return requests;
	}

	/**
	 * Check if the request in the xml file is correct
	 * @param request
	 * @throws ParserXMLException
	 */
	private void checkRequest(Element request) throws ParserXMLException {
		// Checks if attribute exists
		if (request.getAttribute("name") == null)
			throw new ParserXMLException("the attribute name in the xml file doesn't exist");

		if (request.getAttribute("command") == null)
			throw new ParserXMLException("the attribute command in the xml file doesn't exist");

		if (request.getAttribute("event") == null)
			throw new ParserXMLException("the attribute event in the xml file doesn't exist");
		
		

		// check if attribute value is correct
		if (request.getAttribute("name").getValue() == null)
			throw new ParserXMLException("the value event in the xml file is uncorrect");

		if (request.getAttribute("command").getValue() == null)
			throw new ParserXMLException("the value event in the xml file is uncorrect");

		String eventvalue = request.getAttribute("event").getValue();
		if (eventvalue == null)
			throw new ParserXMLException("the value event in the xml file is uncorrect");
		
		eventvalue = eventvalue.toUpperCase();
		try {
			Event.valueOf(eventvalue);
		} catch (IllegalArgumentException e) {
			throw new ParserXMLException("the event \"" + eventvalue + "\" doesn't exist");
		}
		
		//can have a ttl attribute
		Event request_event = Event.valueOf(eventvalue);
		if(!Event.SYMPTOM.equals(request_event) && request.getAttribute("ttl") != null)
			throw new ParserXMLException("the ttl attribute in the xml file is reserve for symptoms for the symptom " + request.getAttributeValue("name")) ;
		
		//if it is a symptom
		if (request.getAttribute("ttl") != null){
			String ttl = request.getAttribute("ttl").getValue();
			if (ttl == null)
				throw new ParserXMLException("the value ttl in the xml file is uncorrect");
			
			try{
				Integer.parseInt(ttl);
			}catch(NumberFormatException e){
				throw new ParserXMLException("the value ttl must be an integer in the xml file");
			}
		}
	}
}
