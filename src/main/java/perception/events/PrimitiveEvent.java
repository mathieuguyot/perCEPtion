package perception.events;


import graph.CloudResourceType;

public abstract class PrimitiveEvent extends Event {

    final private String cloudResourceName;
    final private CloudResourceType cloudResourceType;

    public PrimitiveEvent(String cloudResourceName, CloudResourceType cloudResourceType)
    {
        super(EventType.PRIMITIVE);
        this.cloudResourceName = cloudResourceName;
        this.cloudResourceType = cloudResourceType;
    }

    public String getCloudResourceName() {
        return cloudResourceName;
    }

    public CloudResourceType getCloudResourceType() {
        return cloudResourceType;
    }

    public abstract void display();

}
