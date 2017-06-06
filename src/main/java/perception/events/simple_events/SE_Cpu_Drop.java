package perception.events.simple_events;

import graph.CloudResourceType;
import perception.events.SimpleEvent;

public class SE_Cpu_Drop extends SimpleEvent {

    final private int cpuValueBeforeDrop;
    final private int cpuValueAfterDrop;

    public SE_Cpu_Drop(String cloudResourceName,
                       CloudResourceType cloudResourceType,
                       int score,
                       int cpuValueBeforeDrop,
                       int cpuValueAfterDrop)
    {
        super(cloudResourceName, cloudResourceType, score);
        this.cpuValueBeforeDrop = cpuValueBeforeDrop;
        this.cpuValueAfterDrop = cpuValueAfterDrop;
    }

    @Override
    public String toString() {
        return "SE_Cpu_Drop{" +
                "resourceName=" + getCloudResourceName() +
                ", resourceType" + getCloudResourceType() +
                ", cpuValueBeforeDrop=" + cpuValueBeforeDrop +
                ", cpuValueAfterDrop=" + cpuValueAfterDrop +
                '}';
    }
}
