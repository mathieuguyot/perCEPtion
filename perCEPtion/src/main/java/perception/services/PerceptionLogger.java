package perception.services;

import perception.events.ComplexEvent;
import perception.events.PrimitiveEvent;
import perception.events.SimpleEvent;

import java.io.Serializable;

/**
 * Classe abstraite définissant le service de log des évènements, utilisés pour afficher
 * des informations sur les évènements générés.
 * L'utilisateur du framework perCEPtion doit créer sa propre implémentation de ce service afin de pouvoir utiliser
 * le {@link perception.core.PerceptionCore}.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public abstract class PerceptionLogger implements Serializable {

    /**
     * Permet l'affichage des {@link PrimitiveEvent} générés
     * @param primitiveEvent - L'évènement primitif généré
     * @param pegName - Le nom du PEG qui a généré cet évènement
     */
    public abstract void logPrimitiveEvent(PrimitiveEvent primitiveEvent, String pegName);

    /**
     * Permet l'affichage des {@link SimpleEvent} générés
     * @param simpleEvent - L'évènement simple généré
     * @param segName - Le nom du SEG qui a généré cet évènement
     */
    public abstract void logSimpleEvent(SimpleEvent simpleEvent, String segName);

    /**
     * Permet l'affichage des {@link ComplexEvent} générés
     * @param complexEvent - L'évènement complexe généré
     * @param cegName - Le nom du CEG qui a généré cet évènement
     */
    public abstract void logComplexEvent(ComplexEvent complexEvent, String cegName);

    /**
     * Permet l'affichage de messages d'information
     * @param message - Message d'information à délivrer à l'utilisateur
     */
    public abstract void logMessage(String message);

    /**
     * Permet l'affichage de messages d'erreur
     * @param error - Message d'erreur à délivrer à l'utilisateur
     */
    public abstract void logError(String error);


}
