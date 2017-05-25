package perception.events;

import graph.CloudResourceType;

/**
 * Class that represent a simple event.
 * This class is an event so it extends from event.
 * A simple event contain one or multiple monitoring information (eg. ram, disk, cpu)
 * of one cloud resource.
 * A simple event is generated using simple event generators (SEG)
 */
public abstract class SimpleEvent extends Event {

    final private String cloudResourceName;
    final private CloudResourceType cloudResourceType;

    /**
     * The constructor of the simple event
     * @param cloudResourceName The cloud resource name
     * @param cloudResourceType The cloud resource type
     */
    public SimpleEvent(String cloudResourceName, CloudResourceType cloudResourceType) {
        super(EventType.SIMPLE);
        this.cloudResourceName = cloudResourceName;
        this.cloudResourceType = cloudResourceType;
    }

    /**
     * Getter on the name of the cloud resource name
     * @return The cloud resource name
     */
    public String getCloudResourceName() {
        return cloudResourceName;
    }

    /**
     * Getter on the name of the cloud resource type
     * @return The cloud resource type
     */
    public CloudResourceType getCloudResourceType() {
        return cloudResourceType;
    }

}
