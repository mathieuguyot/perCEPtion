package perception.core;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Class that allow to wrap flink environment run in a thread to allow interactions while
 * perCEPtion is monitoring a system.
 * This class is a thread so it extends of Thread class.
 */
public class FlinkEnvRunner extends Thread {

    private StreamExecutionEnvironment env; //The apache flink environement

    /**
     * Constructor of the Flink env runner
     * @param env The apache flink environement
     */
    public FlinkEnvRunner(StreamExecutionEnvironment env) {
        this.env = env;
    }

    /**
     * Execute the apache flink env
     */
    public void run() {
        try {
            env.execute("perCEPtion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}