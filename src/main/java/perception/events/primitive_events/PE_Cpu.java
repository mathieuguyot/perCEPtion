package perception.events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;
import utils.Color;
import utils.SysOutLogger;

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
    public void display() {
        SysOutLogger.log("[[");
        SysOutLogger.log("CPU_PE", Color.CYAN);
        SysOutLogger.log("]{");
        SysOutLogger.log(getCloudResourceType().toString(), Color.CYAN);
        SysOutLogger.log("}{");
        SysOutLogger.log("cpu", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(String.valueOf(cpuValue), Color.MAGENTA);
        SysOutLogger.log(", name", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(getCloudResourceName(), Color.MAGENTA);
        SysOutLogger.log("}]\n");
    }

    @Override
    public String toString() {
        return "[[CPU_PE]{" + getCloudResourceType() + "}{cpu:" + String.valueOf(cpuValue) + ", name:" + getCloudResourceName() + "}]";
    }

}
