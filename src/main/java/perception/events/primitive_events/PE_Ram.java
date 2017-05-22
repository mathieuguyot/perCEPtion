package perception.events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;
import utils.Color;
import utils.SysOutLogger;

public class PE_Ram extends PrimitiveEvent {

    final private int ramValue;

    public PE_Ram(String cloudResourceName,
                  CloudResourceType cloudResourceType,
                  int ramValue)
    {
        super(cloudResourceName, cloudResourceType);
        this.ramValue = ramValue;
    }

    public int getRamValue() {
        return ramValue;
    }

    @Override
    public void display() {
        SysOutLogger.log("[[");
        SysOutLogger.log("RAM_PE", Color.CYAN);
        SysOutLogger.log("]{");
        SysOutLogger.log(getCloudResourceType().toString(), Color.CYAN);
        SysOutLogger.log("}{");
        SysOutLogger.log("ram", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(String.valueOf(ramValue), Color.MAGENTA);
        SysOutLogger.log(", name", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(getCloudResourceName(), Color.MAGENTA);
        SysOutLogger.log("}]\n");
    }

    @Override
    public String toString() {
        return "[[RAM_PE]{" + getCloudResourceType() + "}{ram:" + String.valueOf(ramValue) + ", name:" + getCloudResourceName() + "}]";
    }

}
