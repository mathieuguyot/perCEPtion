package perception.core;

import graph.CloudResource;

import java.util.HashMap;

/**
 * Point d'accès statique aux graph de ressources
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class CloudResourcesAccess {

    /**
     * Singleton de l'accès au graph de ressources
     */
    public static CloudResourcesAccess instance = new CloudResourcesAccess();

    /**
     * Constructeur privé de l'accès au graph de ressources
     */
    private CloudResourcesAccess() {

    }

    /**
     * Singleton du point d'accès universel au graph ressources
     * @return Le point d'accès universel au graph de ressources
     */
    public static CloudResourcesAccess getInstance() {
        return CloudResourcesAccess.instance;
    }

    private HashMap<String, CloudResource> resources = new HashMap<>();

    /**
     * Ajoute une ressource de cloud à surveiller
     * @param cr - La ressource à surveiller
     * @return <code>True</code> si la ressource a bien été ajoutée, <code>false</code> sinon (cad. nom déjà utilisé)
     */
    public boolean addMonitoredResource(CloudResource cr) {
        if(resources.containsKey(cr.getName())) {
            return false;
        }
        resources.put(cr.getName(), cr);
        return true;
    }

    /**
     * Accesseur des ressources surveillées
     * @return - Les ressources surveillées
     */
    public HashMap<String, CloudResource> getResources() {
        return resources;
    }

}
