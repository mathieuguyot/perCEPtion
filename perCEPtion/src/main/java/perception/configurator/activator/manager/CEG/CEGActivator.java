package perception.configurator.activator.manager.CEG;

import org.apache.commons.lang.ClassUtils;
import perception.complex_event_generator.ComplexEventGenerator;
import perception.configurator.activator.enums.constructors.ParamTypes;
import perception.configurator.activator.enums.errors.ActivationErrorType;
import perception.configurator.activator.manager.ActivationResult;
import perception.configurator.xml.manager.model.SACData;
import perception.core.PerceptionCore;
import perception.pluginManager.EGBank;
import perception.pluginManager.PerceptionPlugin;
import perception.pluginManager.PluginManager;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.services.PerceptionLogger;
import perception.services.implementations.SysoutPerceptionLogger;
import perception.simple_events_generator.SimpleEventGenerator;
import utils.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 10/06/2017.
 */
public class CEGActivator {

    /**
     * Activates event generators listed in the xml configuration file.
     * @param cegs - List of SACData objects containing informations about SEGs to activate
     * @param core - PerceptionCore which will get the new PEG
     * @return <code>true</code> if all generators could be added, else <code>false</code>
     */
    public static ActivationResult activate(List<SACData> cegs, PerceptionCore core) {
        // Instanciation du logger
        PerceptionLogger logger = new SysoutPerceptionLogger();

        // Instanciation du résultat
        ActivationResult activationResult = ActivationResult.FAB();

        // Récupération de la banque de SEG
        EGBank<ComplexEventGenerator> bank = PluginManager.getPluginManager().getCegBank();

        // Parcours de l'ensemble des Primitive Event Generator trouvé par le module de parcours
        for (SACData ceg : cegs) {
            try {
                // Chargement de la classe correspondant au type (nom de la classe) du SEG
                Class<?> event = bank.getClassForEGName(ceg.getEventType());

                List<Object> liste = new ArrayList<Object>();

                // Tableau des types de paramètres
                int size = ceg.getParamsList().size();
                Class<?> types[] = new Class<?>[size];
                Object param[] = new Object[size];
                int elem = 0;
                // Récupération du constructeur
                for (Pair<String, String> tuples : ceg.getParamsList()) {
                    Class<?> classe = ParamTypes.getClassForParamName(tuples.getFirst());
                    if (classe.isPrimitive()) {
                        classe = ClassUtils.primitiveToWrapper(classe);
                        Constructor<?> constructor = classe.getConstructor(String.class);
                        param[elem] = constructor.newInstance(tuples.getSecond());
                    } else {
                        Constructor<?> constructor = classe.getConstructor(String.class);
                        param[elem] = constructor.newInstance(tuples.getSecond());
                    }
                    types[elem] = classe;
                    elem++;
                }
                Constructor<?> constructor = event.getConstructor(types);

                // Instanciation du Primitive Event Generator
                Object instance = constructor.newInstance(param);

                // Ajout du PEG au core du framework
                core.getComplexEventGeneratorManager().addEventGenerator((ComplexEventGenerator) instance);
                logger.logMessage("CEG " + ceg.getEventName() + " activé");
            } catch (ClassNotFoundException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.CLASS_NOT_FOUND);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Classe " + ceg.getEventName() + " inexistante. Vérifiez que cet évènement est bien implémentée dans le système.");
            } catch (NoSuchMethodException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Constructeur inexistant pour la classe " + ceg.getEventName() + " et les paramètres " + ceg.getParamsList().toString());
            } catch (IllegalAccessException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.ACCESS_NOT_GRANTED);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'accéder à la méthode de classe " + ceg.getEventName() + ".");
            } catch (InvocationTargetException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage());
            } catch (InstantiationException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'instancier la classe " + ceg.getEventName() + ".");
            }
        }
        return activationResult;
    }

}
