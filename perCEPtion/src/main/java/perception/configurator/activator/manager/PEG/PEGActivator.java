package perception.configurator.activator.manager.PEG;

import perception.configurator.activator.enums.errors.ActivationErrorType;
import perception.configurator.activator.manager.ActivationResult;
import perception.core.PerceptionCore;
import perception.pluginManager.EGBank;
import perception.pluginManager.PluginManager;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.services.PerceptionLogger;
import perception.services.implementations.SysoutPerceptionLogger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;

/**
 * Activates PEGs from XML configuration file
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class PEGActivator {

    /**
     * Activates event generators listed in the xml configuration file.
     * @param map - Map based on xml parsing
     * @param core - PerceptionCore which will get the new PEG
     * @return <code>true</code> if all generators could be added, else <code>false</code>
     */
    public static ActivationResult activate(Map<String, Long> map, PerceptionCore core) {
        // Instanciation du logger
        PerceptionLogger logger = new SysoutPerceptionLogger();

        // Instanciation du résultat
        ActivationResult activationResult = ActivationResult.FAB();

        // Parcours de l'ensemble des Primitive Event Generator trouvé par le module de parcours
        for (String peg : map.keySet()) {
            try {
                // Chargement de la classe correspondant au nom du PEG
                Class<? extends PrimitiveEventGenerator> event = PluginManager.getPluginManager().getPegBank().getClassForEGName(peg);

                // Récupération du constructeur
                Constructor<?> constructor = event.getConstructor(long.class);

                // Instanciation du Primitive Event Generator
                Object instance = constructor.newInstance(map.get(peg).longValue());

                // Ajout du PEG au core du framework
                core.getPrimitiveEventGeneratorManager().addEventGenerator((PrimitiveEventGenerator) instance);
                System.out.println("PEG " + peg + " activé");
            } catch (NoSuchMethodException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Constructeur inexistant pour la classe " + peg + " et les paramètres " + map.get(peg));
            } catch (IllegalAccessException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.ACCESS_NOT_GRANTED);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'accéder à la méthode de classe " + peg + ".");
            } catch (InvocationTargetException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage());
            } catch (InstantiationException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'instancier la classe " + peg + ".");
            } catch (ClassNotFoundException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.CLASS_NOT_FOUND);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Classe " + peg + " inexistante. Vérifiez que cet évènement est bien implémentée dans le système.");
                return activationResult;
            }
        }
        return activationResult;
    }
}
