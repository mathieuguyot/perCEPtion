package graph;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Interface that represents a list of could resource (with the same type) contained by the class
 * that implements it.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public interface CloudResourceHolder<CRImpl extends CloudResource> {

    //Resource list
    List<CRImpl> getResourceList();
    void initResourceList();

    //Method defined by default
    default boolean addResource(CRImpl res) {
        return !this.isResourceExists(res.getName()) && this.getResourceList().add(res);
    }

    /**
     * Get the number of resource contained in the holder
     * @return The number of resource contained in the holder
     */
    default int getResourceNumber() {
        return this.getResourceList().size();
    }

    /**
     * Check if a resource is contained
     * @param resName The name of the resource
     * @return true if the resource is contained, false otherwise
     */
    default boolean isResourceExists(String resName) {
        for(int i = 0; i < this.getResourceNumber(); i++) {
            String curResName = this.getResourceList().get(i).getName();
            if (Objects.equals(curResName, resName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter on a contained resource
     * @param resName The name of the resource
     * @return The resource (wrapped in a optional) or Optional.empty if the resource does not exists
     */
    default Optional<CRImpl> getResource(String resName) {
        for(int i = 0; i < this.getResourceNumber(); i++) {
            String curResName = this.getResourceList().get(i).getName();
            if(Objects.equals(curResName, resName)) {
                return Optional.of(this.getResourceList().get(i));
            }
        }
        return Optional.empty();
    }

    /**
     * Getter on a contained resource
     * @param resId The id of the resource
     * @return The resource (wrapped in a optional) or Optional.empty if the resource does not exists
     */
    default Optional<CRImpl> getResource(int resId) {
        if(resId >= this.getResourceNumber()) {
            return Optional.empty();
        }
        return Optional.of(this.getResourceList().get(resId));
    }

}
