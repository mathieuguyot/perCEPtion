package perception.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
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
     * Getter on the primitive event generator manager
     * @return The primitive event generator manager
     */
    public EventGeneratorManager<PrimitiveEventGenerator>  getPrimitiveEventGeneratorManager() {
        return ctx.getPrimitiveEventGeneratorManager();
    }

    /**
     * Getter on the simple event generator manager
     * @return The simple event generator manager
     */
    public EventGeneratorManager<SimpleEventGenerator> getSimpleEventGeneratorManager() {
        return ctx.getSimpleEventGeneratorManager();
    }

}
