package perception.events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;

public class PE_ResponseTime extends PrimitiveEvent {

    final private int responseTime;

    public PE_ResponseTime(String cloudResourceName, CloudResourceType cloudResourceType, int responseTime) {
        super(cloudResourceName, cloudResourceType);
        this.responseTime = responseTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    @Override
    public String toString() {
        return "[[RESPONSE_TIME_PE]{" + getCloudResourceType() + "}{response_time:" + String.valueOf(responseTime) + ", name:" + getCloudResourceName() + "}]";
    }

}
