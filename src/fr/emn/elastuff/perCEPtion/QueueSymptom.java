package fr.emn.elastuff.perCEPtion;

import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.Logger;

public class QueueSymptom extends PriorityBlockingQueue<Symptom> {

	/**
	 * This class is to store all Symptom objects in a PriorityBlockingQueue.
	 * This class is a singleton
	 * 
	 * @author Kevin Keovilay
	 * @author Benjamin Robert
	 * @author Dimitri Saingre
	 * 
	 * @see PriorityBlockingQueue
	 * @see Symptom
	 */
	private static final long serialVersionUID = -5944950698188907872L;
	private static Logger logger = Logger.getLogger("mainLogger");
	private static QueueSymptom instance = new QueueSymptom();

	private QueueSymptom() {
	}

	/**
	 * Return the instance of QueueSymptom
	 * 
	 * @return the only instance of QueueSymptom
	 */
	public static QueueSymptom getInstance() {
		return instance;
	}

	/**
	 * AddSymptom Purge the List and the added Symptom will be sort.
	 * 
	 * @param s
	 *            The symptom to add
	 */
	public boolean addSymptom(Symptom s) {
		// Remove all the symptom which are expired
		logger.info("Add Symptom : " + s);
		this.purgeQueue();
		return super.add(s);
	}

	/**
	 * Purge the QueueSymptom and poll the first Symptom in the queue
	 * 
	 * @return First Symptom in a sorted queue
	 */
	public Symptom pullSymptom() {
		this.purgeQueue();
		return super.poll();

	}

	/**
	 * Remove all the symptom which are expired.
	 */
	public void purgeQueue() {
		
		for (Symptom s : this) {
			if (s.isExpired()) {
				this.remove(s);
				logger.info("Remove Symptom : " + s);
			}
		}
	}

	/**
	 * Return a String representation of the QueueSymptom
	 * 
	 * @return a string representation of the QueueSymptom
	 */
	public String toString() {
		String res = "QueueSymptom { ";
		for (Symptom s : this) {
			res += s.toString() + " ; ";
		}
		return res += " }";
	}
}
