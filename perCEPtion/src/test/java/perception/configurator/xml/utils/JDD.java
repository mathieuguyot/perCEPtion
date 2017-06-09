package perception.configurator.xml.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe utilitaire destinée aux tests permettant de créer certain objets
 * utilisé par le système de simulation.
 * 
 * @author Chloé Guilbaud
 *
 */
public class JDD {

	// Singleton
	public static final Map<String, Integer> primitiveEventsMap = createMockedPrimitiveEventsMap();

	/**
	 * Permet la crétion d'un objet avec des
	 * données prédéfinit.
	 * 
	 * @return un objet
	 */
	private static Map<String, Integer> createMockedPrimitiveEventsMap() {
		Map<String, Integer> primitiveEventMap = new HashMap();
		primitiveEventMap.put("PE_1", 78000);
		primitiveEventMap.put("PE_2", 18000);
		primitiveEventMap.put("PE_3", 78000);
		primitiveEventMap.put("PE_4", 26000);
		return primitiveEventMap;
	}

}
