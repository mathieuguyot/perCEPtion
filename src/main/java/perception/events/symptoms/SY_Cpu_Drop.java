package perception.events.symptoms;

import graph.CloudResource;
import graph.CloudResourceType;
import perception.events.Symptom;

public class SY_Cpu_Drop extends Symptom {

    final int cpuInitValue;
    final int cpuDropValue;

    public SY_Cpu_Drop(String cloudResourceName,
                       CloudResourceType cloudResourceType,
                       int score,
                       int cpuInitValue,
                       int cpuDropValue) {
        super(cloudResourceType, cloudResourceName, score);
        this.cpuInitValue = cpuInitValue;
        this.cpuDropValue = cpuDropValue;
    }

    public int getCpuInitValue() {
        return cpuInitValue;
    }

    public int getCpuDropValue() {
        return cpuDropValue;
    }

    @Override
    public String toString() {
        return "SY_Cpu_Drop{" +
                "cpuInitValue=" + cpuInitValue +
                ", cpuDropValue=" + cpuDropValue +
                '}';
    }
}
