package perception.events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;

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
    public String toString() {
        return "[[RAM_PE]{" + getCloudResourceType() + "}{ram:" + String.valueOf(ramValue) + ", name:" + getCloudResourceName() + "}]";
    }

}
