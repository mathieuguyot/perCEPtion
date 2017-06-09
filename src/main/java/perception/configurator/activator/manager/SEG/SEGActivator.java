package perception.configurator.activator.manager.SEG;

import perception.configurator.activator.enums.constructors.ParamTypes;
import perception.configurator.activator.enums.errors.ActivationErrorType;
import perception.configurator.activator.enums.events.SEGTypes;
import perception.configurator.activator.manager.ActivationResult;
import perception.configurator.xml.manager.model.SACData;
import perception.core.PerceptionCore;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.services.PerceptionLogger;
import perception.services.implementations.SysoutPerceptionLogger;
import utils.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Activates SEGs from XML configuration file
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class SEGActivator {

    /**
     * Activates event generators listed in the xml configuration file.
     * @param segs - List of SACData objects containing informations about SEGs to activate
     * @param core - PerceptionCore which will get the new PEG
     * @return <code>true</code> if all generators could be added, else <code>false</code>
     */
    public static ActivationResult activate(List<SACData> segs, PerceptionCore core) {
        // Instanciation du logger
        PerceptionLogger logger = new SysoutPerceptionLogger();

        // Instanciation du résultat
        ActivationResult activationResult = ActivationResult.FAB();

        // Parcours de l'ensemble des Primitive Event Generator trouvé par le module de parcours
        for (SACData seg : segs) {
            try {
                // Chargement de la classe correspondant au type (nom de la classe) du SEG
                Class<?> event = SEGTypes.getClassForSEGName(seg.getEventType());

                List<Object> liste = new ArrayList<Object>();
                // Récupération du constructeur
                for (Pair<String, String> tuples : seg.getParamsList()) {
                    liste.add(ParamTypes.getClassForParamName(tuples.getFirst()).cast(tuples.getSecond()));
                }
                Constructor<?> constructor = event.getConstructor();

                // Instanciation du Primitive Event Generator
                Object instance = constructor.newInstance(liste);

                // Ajout du PEG au core du framework
                core.getPrimitiveEventGeneratorManager().addEventGenerator((PrimitiveEventGenerator) instance);
                logger.logMessage("SEG " + seg.getEventName() + " activé");
            } catch (ClassNotFoundException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.CLASS_NOT_FOUND);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Classe " + seg.getEventName() + " inexistante. Vérifiez que cet évènement est bien implémentée dans le système.");
            } catch (NoSuchMethodException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Constructeur inexistant pour la classe " + seg.getEventName() + " et les paramètres " + seg.getParamsList().toString());
            } catch (IllegalAccessException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.ACCESS_NOT_GRANTED);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'accéder à la méthode de classe " + seg.getEventName() + ".");
            } catch (InvocationTargetException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage());
            } catch (InstantiationException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'instancier la classe " + seg.getEventName() + ".");
            }
        }
        return activationResult;
    }
}
