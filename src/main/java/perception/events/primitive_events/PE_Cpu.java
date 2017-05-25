package perception.events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;

public class PE_Cpu extends PrimitiveEvent {

    final private int cpuValue;

    public PE_Cpu(String cloudResourceName,
                  CloudResourceType cloudResourceType,
                  int cpuValue)
    {
        super(cloudResourceName, cloudResourceType);
        this.cpuValue = cpuValue;
    }

    public int getCpuValue() {
        return cpuValue;
    }

    @Override
    public String toString() {
        return "[[CPU_PE]{" + getCloudResourceType() + "}{cpu:" + String.valueOf(cpuValue) + ", name:" + getCloudResourceName() + "}]";
    }

}
