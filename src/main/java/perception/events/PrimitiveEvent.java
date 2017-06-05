package perception.events;

import graph.CloudResourceType;

/**
 * Class that represent a primitive event.
 * This class is an event so it extends from event.
 * A primitive event contain one monitoring information (eg. ram, disk, cpu) of one cloud resource.
 * A primitive event is generated using primitive event generators (PEG)
 */
public abstract class PrimitiveEvent extends Event {

    final private String cloudResourceName;
    final private CloudResourceType cloudResourceType;
    final private int score;

    /**
     * The constructor of the primitive event
     * @param cloudResourceName The cloud resource name
     * @param cloudResourceType The cloud resource type
     */
    public PrimitiveEvent(String cloudResourceName, CloudResourceType cloudResourceType, int score)
    {
        super(EventType.PRIMITIVE);
        this.cloudResourceName = cloudResourceName;
        this.cloudResourceType = cloudResourceType;
        this.score = score;
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

    /**
     * Getter on the score of the primitive event
     * @return The score of the primitive event
     */
    public int getScore() {
        return score;
    }

}
