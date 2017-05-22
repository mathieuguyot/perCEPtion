package perception.events.primitive_events;

import graph.CloudResourceType;
import perception.events.PrimitiveEvent;
import utils.Color;
import utils.SysOutLogger;

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
    public void display() {
        SysOutLogger.log("[[");
        SysOutLogger.log("RESPONSE_TIME_PE", Color.CYAN);
        SysOutLogger.log("]{");
        SysOutLogger.log(getCloudResourceType().toString(), Color.CYAN);
        SysOutLogger.log("}{");
        SysOutLogger.log("response_time", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(String.valueOf(responseTime), Color.MAGENTA);
        SysOutLogger.log(", name", Color.BLUE);
        SysOutLogger.log(": ");
        SysOutLogger.log(getCloudResourceName(), Color.MAGENTA);
        SysOutLogger.log("}]\n");
    }

    @Override
    public String toString() {
        return "[[RESPONSE_TIME_PE]{" + getCloudResourceType() + "}{response_time:" + String.valueOf(responseTime) + ", name:" + getCloudResourceName() + "}]";
    }

}
