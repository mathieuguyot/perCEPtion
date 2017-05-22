package perception;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkEnvRunner extends Thread {

    private StreamExecutionEnvironment env;

    public FlinkEnvRunner(StreamExecutionEnvironment env) {
        this.env = env;
    }

    public void run() {
        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}