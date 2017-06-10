package perception.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Classe permettant d'encapsuler le {@link FlinkEnvRunner} pour dans un thrad
 * pour autoriser l'utilisateur à effectuer des modifications pendant l'exécution du système de perCEPtion.
 * Cette classe est un thread, donc il s'agit d'une extension de la classe {@link Thread}.
 */
public class FlinkEnvRunner extends Thread {

    private StreamExecutionEnvironment env; //L'environnement Apache Flink

    /**
     * Constructeur de la classe {@link FlinkEnvRunner}
     * @param env - L'environnement Apache Flink
     */
    public FlinkEnvRunner(StreamExecutionEnvironment env) {
        this.env = env;
    }

    /**
     * Exécute l'environnement Apache Flink
     */
    public void run() {
        try {
            env.execute("perCEPtion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}