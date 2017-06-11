package events.simple_events;

import graph.CloudResourceType;
import perception.events.SimpleEvent;

public class SE_Ram_Drop extends SimpleEvent {

    final private int ramValueBeforeDrop;
    final private int ramValueAfterDrop;

    public SE_Ram_Drop(String cloudResourceName,
                       CloudResourceType cloudResourceType,
                       int score,
                       int ramValueBeforeDrop,
                       int ramValueAfterDrop)
    {
        super(cloudResourceName, cloudResourceType, score);
        this.ramValueBeforeDrop = ramValueBeforeDrop;
        this.ramValueAfterDrop = ramValueAfterDrop;
    }

    @Override
    public String toString() {
        return "SE_Ram_Drop{" +
                "resourceName=" + getCloudResourceName() +
                ", resourceType" + getCloudResourceType() +
                ", ramValueBeforeDrop=" + ramValueBeforeDrop +
                ", ramValueAfterDrop=" + ramValueAfterDrop +
                '}';
    }

}
