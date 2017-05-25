package perception.core;

import graph.CloudResource;

import java.util.HashMap;

/**
 * Static access point to the graph resources
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class CloudResourcesAccess {

    /**
     * Single instance of the graph resources access
     */
    public static CloudResourcesAccess instance = new CloudResourcesAccess();

    /**
     * Private constructor of the cloud resources access
     */
    private CloudResourcesAccess() {

    }

    /**
     * Single instance of the universal access to the graph resources access
     * @return The universal access to the graph resources access
     */
    public static CloudResourcesAccess getInstance() {
        return CloudResourcesAccess.instance;
    }

    private HashMap<String, CloudResource> resources = new HashMap<>();

    /**
     * Add a cloud resource to monitor
     * @param cr The cloud resource to monitor
     * @return True if the cloud resources will be monitored, false otherwise (eg. name already used)
     */
    public boolean addMonitoredResource(CloudResource cr) {
        if(resources.containsKey(cr.getName())) {
            return false;
        }
        resources.put(cr.getName(), cr);
        return true;
    }

    /**
     * Getter on the cloud resources monitored
     * @return The cloud resources monitored
     */
    public HashMap<String, CloudResource> getResources() {
        return resources;
    }

}
