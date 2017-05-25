package perception.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.simple_events_generator.SimpleEventGenerator;

/**
 * This class is the perception run context.
 * Before each flink env run, we have to construct some flink-link resources
 * like datastreams, PEG, SEG, CEG, ... and many more.
 * This class is moved to each resources that need to be initialized in order to share flink-based
 * resources.
 */
public class PerceptionRunContext {

    private EventGeneratorManager<PrimitiveEventGenerator> primitiveEventGeneratorManager; //Manager who manage our PEG
    private EventGeneratorManager<SimpleEventGenerator> simpleEventGeneratorManager; //Manager who manager our SEG
    private StreamExecutionEnvironment env; //Flink run environment.
    private PrimitiveEventStream primitiveEventStream; //Primitive event stream
    private SACEventStream sacEventStream; //simple and complex event stream

    /**
     * Constructor of the perception run context
     */
    public PerceptionRunContext() {
        this.primitiveEventGeneratorManager = new EventGeneratorManager<>();
        this.simpleEventGeneratorManager = new EventGeneratorManager<>();
        this.primitiveEventStream = new PrimitiveEventStream();
        this.sacEventStream = new SACEventStream();
        this.env = null;
    }

    /**
     * Setter on the stream execution environment
     * @param env The stream execution environment
     */
    public void setEnv(StreamExecutionEnvironment env) {
        this.env = env;
    }

    /**
     * Getter on the stream execution environment
     * @return The stream execution environment
     */
    public StreamExecutionEnvironment getEnv() {
        return env;
    }

    /**
     * Getter on the primitive event stream
     * @return Setter on the primitive event stream
     */
    public PrimitiveEventStream getPrimitiveEventStream() {
        return primitiveEventStream;
    }

    /**
     * Getter on the simple and complex event stream
     * @return The simple and complex event stream
     */
    public SACEventStream getSacEventStream() {
        return sacEventStream;
    }

    /**
     * Getter on the primitive event generator manager
     * @return The primitive event generator manager
     */
    public EventGeneratorManager<PrimitiveEventGenerator>  getPrimitiveEventGeneratorManager() {
        return primitiveEventGeneratorManager;
    }

    /**
     * Getter on the simple event generator manager
     * @return The simple event generator manager
     */
    public EventGeneratorManager<SimpleEventGenerator> getSimpleEventGeneratorManager() {
        return simpleEventGeneratorManager;
    }
}
