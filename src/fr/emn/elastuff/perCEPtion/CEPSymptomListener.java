package fr.emn.elastuff.perCEPtion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.event.bean.BeanEventBean;


import fr.emn.elastuff.graph.CloudResource;

/**
 * This class is the Esper listener used for Symptoms. It creates Symptom and
 * add them to the QueueSymptom
 * 
 * @author Kevin Keovilay
 * @author Benjamin Robert
 * @author Dimitri Saingre
 * 
 * @see UpdateListener
 * @see QueueSymptom
 * @See Symptom
 */
public class CEPSymptomListener implements UpdateListener {
	private static Logger logger = Logger.getLogger("mainLogger");
	private String name;
	private int symptom_ttl;

	public CEPSymptomListener(String n, int symptom_ttl) {
		super();
		name = n;
		this.symptom_ttl = symptom_ttl;
	}

	public void update(EventBean[] newData, EventBean[] oldData) {
		logger.info("Found new Symptom!");
		logger.info("ESPER CEPSymptomListener : Event " + name + " received: " + newData[0].getUnderlying());

		List<CloudResource> ressources = new ArrayList<CloudResource>();

		// The EventBean array can contain HashMap or POJO (CF Esper doc)
		if (newData[0].getUnderlying() instanceof Map) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) newData[0].getUnderlying();

			for (String key : map.keySet()) {
				if (map.get(key) instanceof BeanEventBean)
					ressources.add((CloudResource) ((BeanEventBean) map.get(key)).getUnderlying());
				if (map.get(key) instanceof CloudResource)
					ressources.add((CloudResource) map.get(key));
			}
		} else if (newData[0].getUnderlying() instanceof CloudResource) {
			ressources.add((CloudResource) newData[0].getUnderlying());
		} else {
			logger.error("CEPSymptomListener " + newData[0].getUnderlying().getClass() + " unsupported");
		}

		Symptom s = new Symptom(this.name, ressources, this.symptom_ttl);
		
		QueueSymptom queueSymptom = QueueSymptom.getInstance();
		//adding symptom and notify elastuff
        synchronized (queueSymptom) {
        	queueSymptom.addSymptom(s);
        	queueSymptom.notifyAll();
        }


		
		//queueSymptom.addSymptom(s);
		System.out.println(queueSymptom.toString());
	}

}
