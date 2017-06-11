package perception.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import perception.complex_event_generator.ComplexEventGenerator;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.services.PerceptionLogger;
import perception.services.implementations.SysoutPerceptionLogger;
import perception.simple_events_generator.SimpleEventGenerator;

/**
 * Cette classe correspond au Contexte d'exécution de perCEPtion.
 * Avant chaque exécution de {@link FlinkEnvRunner}, il faut construire quelques ressources liées à Apache Flink
 * (DataStreams, PEG, SEG, CEG, etc.).
 * Cette classe est déplacée vers chaque ressources devant être initialisée, afin de partager les ressources basées sur Flink.
 */
public class PerceptionRunContext {

    //Gestionnaire des PEGs
    private EventGeneratorManager<PrimitiveEventGenerator> primitiveEventGeneratorManager;
    //Gestionnaire des SEGs
    private EventGeneratorManager<SimpleEventGenerator> simpleEventGeneratorManager;
    //Gestionnaire des CEGs
    private EventGeneratorManager<ComplexEventGenerator> complexEventGeneratorManager;

    private StreamExecutionEnvironment env; //Flink run environment.

    private PrimitiveEventStream primitiveEventStream; //Primitive event stream
    private SACEventStream sacEventStream; //simple and complex event stream
    private PASACEventStream pasacEventStream;

    //Symptom queue
    private static SymptomQueue symptomQueue;
    //Logging system
    private static PerceptionLogger perceptionLogger;


    /**
     * Constructeur de la classe {@link PerceptionRunContext}
     */
    public PerceptionRunContext() {
        this.primitiveEventGeneratorManager = new EventGeneratorManager<>();
        this.simpleEventGeneratorManager = new EventGeneratorManager<>();
        this.complexEventGeneratorManager = new EventGeneratorManager<>();

        this.primitiveEventStream = new PrimitiveEventStream();
        this.pasacEventStream = new PASACEventStream();
        this.sacEventStream = new SACEventStream();

        this.perceptionLogger = new SysoutPerceptionLogger();

        this.symptomQueue = new SymptomQueue();

        this.env = null;
    }


    public static SymptomQueue getSymptomQueue() {
        return symptomQueue;
    }

    /**
     * Modificateur du {@link StreamExecutionEnvironment}
     * @param env - Le nouveau StreamExecutionEnvironment
     */
    public void setEnv(StreamExecutionEnvironment env) {
        this.env = env;
    }

    /**
     * Accesseur du {@link StreamExecutionEnvironment}
     * @return Le StreamExecutionEnvironment
     */
    public StreamExecutionEnvironment getEnv() {
        return env;
    }

    /**
     * Accesseur du {@link PrimitiveEventStream}
     * @return Le PrimitiveEventStream
     */
    public PrimitiveEventStream getPrimitiveEventStream() {
        return primitiveEventStream;
    }

    /**
     * Accesseur du {@link SACEventStream}
     * @return Le SACEventStream
     */
    public SACEventStream getSacEventStream() {
        return sacEventStream;
    }

    /**
     * Accesseur du Gestionnaire de PEGs
     * @return Le gestionnaire de PEGs
     */
    public EventGeneratorManager<PrimitiveEventGenerator>  getPrimitiveEventGeneratorManager() {
        return primitiveEventGeneratorManager;
    }

    /**
     * Accesseur du Gestionnaire de SEGs
     * @return Le gestionnaire de SEGs
     */
    public EventGeneratorManager<SimpleEventGenerator> getSimpleEventGeneratorManager() {
        return simpleEventGeneratorManager;
    }

    /**
     * Accesseur du Gestionnaire de CEGs
     * @return Le gestionnaire de CEGs
     */
    public EventGeneratorManager<ComplexEventGenerator> getComplexEventGeneratorManager() {
        return complexEventGeneratorManager;
    }

    /**
     * Accesseur du {@link PerceptionLogger} utilisé pour afficher des informations en phase de test
     * @return Le PerceptionLogger
     */
    public static PerceptionLogger getPerceptionLogger() {
        return PerceptionRunContext.perceptionLogger;
    }

    /**
     * Modificateur du {@link PerceptionLogger} utilisé pour afficher des informations en phase de test
     * Setter on the perceptiuon logger that we use to log infos during the test
     * @param perceptionLogger - Le nouveau PerceptionLogger
     */
    public static void setPerceptionLogger(PerceptionLogger perceptionLogger) {
        PerceptionRunContext.perceptionLogger = perceptionLogger;
    }

    public PASACEventStream getPasacEventStream() {
        return pasacEventStream;
    }
}
