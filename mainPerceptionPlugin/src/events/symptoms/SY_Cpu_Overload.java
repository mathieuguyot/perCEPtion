package events.symptoms;

import graph.CloudResourceType;
import perception.events.Symptom;

/**
 * Created by asus on 06/06/2017.
 */
public class SY_Cpu_Overload extends Symptom {

    final int cpuHighValue;

    public SY_Cpu_Overload(String cloudResourceName,
                       CloudResourceType cloudResourceType,
                       int score,
                       int cpuHighValue) {
        super(cloudResourceType, cloudResourceName, score);
        this.cpuHighValue = cpuHighValue;
    }

    public int getCpuHighValue() {
        return cpuHighValue;
    }

    @Override
    public String toString() {
        return "SY_Cpu_Overload{" +
                ", cpuHighValue=" + cpuHighValue +
                '}';
    }

}
