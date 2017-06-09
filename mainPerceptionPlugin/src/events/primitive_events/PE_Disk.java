package events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;

public class PE_Disk extends PrimitiveEvent {

    final private int diskValue;

    public PE_Disk(String cloudResourceName,
                   CloudResourceType cloudResourceType,
                   int score,
                   int diskValue)
    {
        super(cloudResourceName, cloudResourceType, score);
        this.diskValue = diskValue;
    }

    public int getDiskValue() {
        return diskValue;
    }

    @Override
    public String toString() {
        return "[[DISK_PE]{" + getCloudResourceType() + "}{disk:" + String.valueOf(diskValue) + ", name:" + getCloudResourceName() + "}]";
    }

}
