package perception.configurator.activator.manager.PEG;

import perception.configurator.activator.enums.errors.ActivationErrorType;
import perception.configurator.activator.manager.ActivationResult;
import perception.configurator.xml.manager.model.PEData;
import perception.core.PerceptionCore;
import perception.pluginManager.EGBank;
import perception.pluginManager.PluginManager;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.services.PerceptionLogger;
import perception.services.implementations.SysoutPerceptionLogger;
import perception.simple_events_generator.SimpleEventGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Active les Primitive Event Generator déclarés dans le fichier de configuration XML
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class PEGActivator {

    /**
     * Récupère les objets de configuration des {@link PrimitiveEventGenerator},
     * les instancie avec leur paramètres et les ajoute au {@link PerceptionCore}
     * @param primitiveEventList - Liste d'objets {@link PEData} obtenus lors du parsing
     * @param core - PerceptionCore auquel on ajoutera les PEG activés
     * @return {@link ActivationResult} contenant les éventuels messages d'erreurs
     */
    public static ActivationResult activate(List<PEData> primitiveEventList, PerceptionCore core) {
        // Instanciation du logger
        PerceptionLogger logger = new SysoutPerceptionLogger();

        // Instanciation du résultat
        ActivationResult activationResult = ActivationResult.FAB();

        // Parcours de l'ensemble des Primitive Event Generator trouvés par le module de parcours
        for (PEData peg : primitiveEventList) {
            try {
                // Chargement de la classe correspondant au nom du PEG
                Class<? extends PrimitiveEventGenerator> event = PluginManager.getPluginManager().getPegBank().getClassForEGName(peg.getType());

                // Récupération du constructeur
                Constructor<?> constructor = event.getConstructor(String.class, long.class);

                // Instanciation du Primitive Event Generator
                Object instance = constructor.newInstance(peg.getName(), peg.getRunTime());

                // Ajout du PEG au core du framework
                core.getPrimitiveEventGeneratorManager().addEventGenerator((PrimitiveEventGenerator) instance);
                System.out.println("PEG " + peg + " activé");
            } catch (NoSuchMethodException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Constructeur inexistant pour la classe " + peg.getType() + " et les paramètres " + peg.getRunTime());
            } catch (IllegalAccessException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.ACCESS_NOT_GRANTED);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'accéder à la méthode de classe " + peg.getType() + ".");
            } catch (InvocationTargetException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage());
            } catch (InstantiationException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.WRONG_PARAMETERS);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Impossible d'instancier la classe " + peg.getType() + ".");
            } catch (ClassNotFoundException ex) {
                activationResult.setActivationErrorType(ActivationErrorType.CLASS_NOT_FOUND);
                activationResult.setErrorMsg(ex.getMessage() + "\n" + "Classe " + peg.getType() + " inexistante. Vérifiez que cet évènement est bien implémentée dans le système.");
                return activationResult;
            }
        }
        return activationResult;
    }
}
