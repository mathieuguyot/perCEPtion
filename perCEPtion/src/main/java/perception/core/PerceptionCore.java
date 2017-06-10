package perception.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import perception.complex_event_generator.ComplexEventGenerator;
import perception.primitive_events_generator.PrimitiveEventGenerator;
import perception.simple_events_generator.SimpleEventGenerator;

public class PerceptionCore {

    private PerceptionRunContext ctx;
    private FlinkEnvRunner runner;
    public boolean isRunning;

    public PerceptionCore() {
        runner = null;
        isRunning = false;
        ctx = new PerceptionRunContext();
    }

    public void run() {
        if(!beforeRun()) {
            return;
        }
        try {
            runner = new FlinkEnvRunner(ctx.getEnv());
            runner.start();
            isRunning = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean beforeRun() {
        //Get a new env
        ctx.setEnv(StreamExecutionEnvironment.getExecutionEnvironment());
        //Call before run of the others PerceptionRunResource components
        boolean isPrepareOk = true;
        isPrepareOk = this.ctx.getPrimitiveEventStream().beforeRun(this.ctx);
        if(isPrepareOk) {
            isPrepareOk = this.ctx.getSacEventStream().beforeRun(this.ctx);
        }
        if(isPrepareOk) {
            isPrepareOk = this.ctx.getPasacEventStream().beforeRun(this.ctx);
        }
        return isPrepareOk;
    }

    public void endRun() {
        if(isRunning) {
            this.runner.stop();
            this.isRunning = false;
            this.runner = null;
            this.ctx.setEnv(null);
            this.ctx.getPrimitiveEventStream().endRun();
        }
    }

    /**
     * Accesseur du {@link EventGeneratorManager} pour les {@link PrimitiveEventGenerator}
     * @return Le gestionnaire des PEG
     */
    public EventGeneratorManager<PrimitiveEventGenerator>  getPrimitiveEventGeneratorManager() {
        return ctx.getPrimitiveEventGeneratorManager();
    }

    /**
     * Accesseur du {@link EventGeneratorManager} pour les {@link SimpleEventGenerator}
     * @return Le gestionnaire des SEG
     */
    public EventGeneratorManager<SimpleEventGenerator> getSimpleEventGeneratorManager() {
        return ctx.getSimpleEventGeneratorManager();
    }

    /**
     * Accesseur du {@link EventGeneratorManager} pour les {@link ComplexEventGenerator}
     * @return Le gestionnaire des CEG
     */
    public EventGeneratorManager<ComplexEventGenerator> getComplexEventGeneratorManager() {
        return ctx.getComplexEventGeneratorManager();
    }

}
