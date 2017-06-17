package perception.configurator.activator.manager.SEG;

import perception.configurator.activator.enums.constructors.ParamTypes;
import perception.configurator.activator.enums.errors.ActivationErrorType;
import perception.configurator.activator.manager.ActivationResult;
import perception.configurator.xml.manager.model.EventData;
import perception.core.PerceptionCore;
import perception.pluginManager.EGBank;
import perception.pluginManager.PluginManager;
import perception.services.PerceptionLogger;
import perception.services.implementations.SysoutPerceptionLogger;
import perception.simple_events_generator.SimpleEventGenerator;
import utils.Pair;
import org.apache.commons.lang.ClassUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Active les Simple Event Generator déclarés dans le fichier de configuration XML
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class SEGActivator {

    /**
     * Récupère les objets de configuration des {@link SimpleEventGenerator},
     * les instancie avec leur paramètres et les ajoute au {@link PerceptionCore}
     * @param segs - Liste d'objets EventData contenant les informations des SimpleEventGenerator à activer
     * @param core - PerceptionCore auquel on ajoutera les SEG activés
     * @return {@link ActivationResult} contenant les éventuels messages d'erreurs
     */
    public static ActivationResult activate(List<? extends EventData> segs, PerceptionCore core) {
        // Instanciation du logger
        PerceptionLogger logger = new SysoutPerceptionLogger();

        // Instanciation du résultat
        ActivationResult activationResult = ActivationResult.FAB();

        // Récupération de la banque de SEG
        EGBank<SimpleEventGenerator> bank = PluginManager.getPluginManager().getSegBank();

        // Parcours de l'ensemble des Simple Event Generator trouvés par le module de parcours
        for (EventData seg : segs) {
            try {
                // Chargement de la classe correspondant au type (nom de la classe) du SEG
                Class<?> event = bank.getClassForEGName(seg.getEventType());

                // Tableau des types de paramètres
                int size = seg.getParamsList().size();
                Class<?> types[] = new Class<?>[size];
                Object param[] = new Object[size];
                int elem = 0;
                // Récupération du constructeur
                for (Pair<String, String> tuples : seg.getParamsList()) {
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

                // Instanciation du Simple Event Generator
                Object instance = constructor.newInstance(param);

                // Ajout du SEG au core du framework
                core.getSimpleEventGeneratorManager().addEventGenerator((SimpleEventGenerator) instance);
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
