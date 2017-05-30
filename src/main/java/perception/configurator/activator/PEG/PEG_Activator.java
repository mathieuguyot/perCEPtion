package perception.configurator.activator.PEG;

import perception.configurator.activator.enums.PEGTypes;
import perception.core.PerceptionCore;
import perception.core.PrimitiveEventStream;
import perception.primitive_events_generator.PrimitiveEventGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activates PEG from XML configuration file
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class PEG_Activator {

    /**
     * Activates event generators listed in the xml configuration file.
     * @param map - Map based on xml parsing
     * @param core - PerceptionCore which will get the new PEG
     * @return <code>true</code> if all generators could be added, else <code>false</code>
     */
    public static boolean activate(Map<String, Long> map, PerceptionCore core) {
        boolean success = true;

        for (String peg : map.keySet()) {
            try {
                Class<?> event = PEGTypes.getClassForPEGName(peg);
                Constructor<?> constructor = event.getConstructor(long.class);
                Object instance = constructor.newInstance(map.get(peg).longValue());
                core.getPrimitiveEventGeneratorManager().addEventGenerator((PrimitiveEventGenerator) instance);
                System.out.println("PEG " + peg + " activé");
            } catch (ClassNotFoundException ex) {
                System.err.println("Classe " + peg + " inexistante. Vérifiez que cet évènement est bien implémentée dans le système.");
                ex.printStackTrace();
                success = false;
            } catch (NoSuchMethodException ex) {
                System.err.println("Constructeur inexistant pour la classe " + peg + " et les paramètres " + map.get(peg));
                ex.printStackTrace();
                success = false;
            } catch (IllegalAccessException ex) {
                System.err.println("Impossible d'accéder à la méthode de classe " + peg + ".");
                ex.printStackTrace();
                success = false;
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
                success = false;
            } catch (InstantiationException ex) {
                System.err.println("Impossible d'instancier la classe " + peg + ".");
                ex.printStackTrace();
                success = false;
            }
        }
        return success;
    }
}
