package fr.emn.elastuff.perCEPtion;

import java.util.Observable;
import java.util.Observer;

import com.espertech.esper.client.EPRuntime;

/**
 * This class is used to call the EPRuntime.sendEvent method at every
 * CloudResource modifications. We didn't want to give access to the EPRuntime
 * object to every CloudResource object
 * 
 * @author Kevin Keovilay
 * @author Benjamin Robert
 * @author Dimitri Saingre
 * 
 * @see Observer
 * @see EPRuntime
 */
public class CloudRessourceListener implements Observer {

	private EPRuntime eprt;

	public CloudRessourceListener(EPRuntime eprt) {
		this.eprt = eprt;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.eprt.sendEvent(o);
	}

}
