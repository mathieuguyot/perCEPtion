package perception.events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;
import utils.Color;
import utils.SysOutLogger;

public class PE_Disk extends PrimitiveEvent {

    final private int diskValue;

    public PE_Disk(String cloudResourceName,
                   CloudResourceType cloudResourceType,
                   int diskValue)
    {
        super(cloudResourceName, cloudResourceType);
        this.diskValue = diskValue;
    }

    public int getDiskValue() {
        return diskValue;
    }

    @Override
    public void display() {
        SysOutLogger.log("[[");
        SysOutLogger.log("DISK_PE", Color.CYAN);
        SysOutLogger.log("]{");
        SysOutLogger.log(getCloudResourceType().toString(), Color.CYAN);
        SysOutLogger.log("}{");
        SysOutLogger.log("disk", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(String.valueOf(diskValue), Color.MAGENTA);
        SysOutLogger.log(", name", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(getCloudResourceName(), Color.MAGENTA);
        SysOutLogger.log("}]\n");
    }

    @Override
    public String toString() {
        return "[[DISK_PE]{" + getCloudResourceType() + "}{disk:" + String.valueOf(diskValue) + ", name:" + getCloudResourceName() + "}]";
    }

}
