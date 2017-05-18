package fr.emn.elastuff.perCEPtion;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.jdom.JDOMException;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import fr.emn.elastuff.graph.Appli;
import fr.emn.elastuff.graph.CloudResource;
import fr.emn.elastuff.graph.Co;
import fr.emn.elastuff.graph.PM;
import fr.emn.elastuff.graph.Tier;
import fr.emn.elastuff.graph.VM;

/**
 * This class is to manage all the Esper configuration.
 * 
 * @author Kevin Keovilay
 * @author Benjamin Robert
 * @author Dimitri Saingre
 * 
 */
public class EsperManager {

	private Configuration config;
	private EPServiceProvider epsp;
	private EPRuntime eprt;

	private Collection<Request> requests;

	public EsperManager(Collection<CloudResource> cloudsRessources) {
		config = new Configuration();
		configAliases();
		epsp = EPServiceProviderManager.getProvider("myCEPEngine", config);
		eprt = epsp.getEPRuntime();

		for (CloudResource cR : cloudsRessources) {
			CloudRessourceListener listener = new CloudRessourceListener(eprt);
			cR.addObserver(listener);
		}
	}

	public EPRuntime getRuntime() {
		return eprt;
	}

	public void configAliases() {
		config.addEventType("Appli", Appli.class.getName());
		config.addEventType("Co", Co.class.getName());
		config.addEventType("PM", PM.class.getName());
		config.addEventType("VM", VM.class.getName());
		config.addEventType("Tier", Tier.class.getName());
	}

	public void readXml(File file) throws JDOMException, IOException, ParserXMLException {
		ParserXML parser = new ParserXML();
		requests = parser.read(file);
	}

	public void addStatements() {
		EPAdministrator epAdm = epsp.getEPAdministrator();

		for (Request r : requests) {
			EPStatement epStatement = epAdm.createEPL(r.getCommand());
			System.out.println("STMNT ADDED : " + r.getCommand());
			if (r.getEvent().equals(Event.SYMPTOM)) {
				SymptomRequest sr = (SymptomRequest) r;
				epStatement.addListener(new CEPSymptomListener(sr.getName(), sr.getTTL()));
			} else {
				epStatement.addListener(new CEPEventListener(r.getName()));
			}
		}
	}

	public void removeStatements() {
		epsp.getEPAdministrator().destroyAllStatements();
	}
}
