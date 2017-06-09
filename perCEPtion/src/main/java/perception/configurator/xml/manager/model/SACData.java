package perception.configurator.xml.manager.model;

import utils.Pair;

import java.util.ArrayList;

/**
 * Created by asus on 06/06/2017.
 */
public class SACData {

    private String eventType;
    private String eventName;
    private ArrayList<Pair<String, String>> params;

    public SACData(String eventType, String eventName) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.params = new ArrayList<Pair<String, String>>();
    }

    public void addParams(String name, String value) {
        params.add(new Pair<String, String>(name, value));
    }

    public ArrayList<Pair<String, String>> getParamsList() {
        return params;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
