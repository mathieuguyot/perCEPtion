package perception.services;

import perception.core.PerceptionRunContext;

/**
 * Interface fournissant un moyen de déclarer une classe en tant que ressource d'exécution de perCEPtion.
 * Grâce aux méthodes fournies par cette inferface, le FlinkMonitor sera recréé avant chaque exécution,
 * puis détruit après chaque exécution.
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 * Attention : Une PerceptionRunResource DOIT ABSOLUMENT être sérialisable afin d'être
 * utilisée par Apache Flink.
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 *
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 * Attention : DURANT L'EXECUTION D'APACHE FLINK, TOUS LES MEMBRES D'UNE PERCEPTION RUN RESOURCE
 * PEUVENT ETRE MODIFIEES, MAIS CELA NE MODIFIERA PAS PROCESSUS DE PERCEPTION. AFIN DE MODIFIER
 * LES MEMBRES D'UNE PERCEPTION RUN RESOURCE, IL FAUT ARRETER LE PROCESSUS D'EXECUTION DE PERCEPTION,
 * APPLIQUER LES CHANGEMENT PUIS RELANCER LE SYSTEME PERCEPTION.
 * (Durant l'exéuction, Apache Flink va prendre le contrôle des PerceptionRunResource que nous aurons
 * instanciés en les sérialisant. Ensuite, Apache Flink utilisera des clones de ces PerceptionRunResource
 * et va "se débarasser" des "réelles" instances de PerceptionRunResource.)
 * /!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\_/!\
 */
public interface PerceptionRunResource {

    /**
     * Méthode appelée avant l'exécution du {@link perception.core.FlinkEnvRunner}
     * @param ctx - Contexte d'exécution de perCEPtion
     * @return <code>true</code> si tout a été correctement préparé. <code>false</code> désactivera le lancement du FlinkEnvRunner
     */
    boolean beforeRun(PerceptionRunContext ctx);

    /**
     * Méthode après chaque exécution du {@link perception.core.FlinkEnvRunner}
     */
    void endRun();

}
