package fr.emn.elastuff.perCEPtion;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * This class is the Esper listener used for Event. It only logs infos about
 * received event.
 * 
 * @author Kevin Keovilay
 * @author Benjamin Robert
 * @author Dimitri Saingre
 * @see UpdateListener
 */
public class CEPEventListener implements UpdateListener {
	private static Logger logger = Logger.getLogger("mainLogger");

	private String name;

	public CEPEventListener(String n) {
		super();
		name = n;
	}

	/**
	 * Implementation notes : this update only logs info about received data.
	 */
	@Override
	public void update(EventBean[] newData, EventBean[] oldData) {
		logger.info("ESPER CEP Event Listener : Event " + name + " received: " + newData[0].getUnderlying());
	}

}
