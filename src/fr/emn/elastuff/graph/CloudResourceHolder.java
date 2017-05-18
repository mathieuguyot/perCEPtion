package fr.emn.elastuff.graph;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Interface that represents a list of could resource (with the same type) contained by the class
 * that implements it.
 * @author (review) Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public interface CloudResourceHolder<CRImpl extends CloudResource> {

    //Resource list
    List<CRImpl> getResourceList();
    void initResourceList();

    //Method defined by default
    default boolean addResource(CRImpl res) {
        return !this.isResourceExists(res.getName()) && this.getResourceList().add(res);
    }

    default int getResourceNumber() {
        return this.getResourceList().size();
    }

    default boolean isResourceExists(String resName) {
        for(int i = 0; i < this.getResourceNumber(); i++) {
            String curResName = this.getResourceList().get(i).getName();
            if (Objects.equals(curResName, resName)) {
                return true;
            }
        }
        return false;
    }

    default Optional<CRImpl> getResource(String resName) {
        for(int i = 0; i < this.getResourceNumber(); i++) {
            String curResName = this.getResourceList().get(i).getName();
            if(Objects.equals(curResName, resName)) {
                return Optional.of(this.getResourceList().get(i));
            }
        }
        return Optional.empty();
    }

    default Optional<CRImpl> getResource(int resId) {
        if(resId >= this.getResourceNumber()) {
            return Optional.empty();
        }
        return Optional.of(this.getResourceList().get(resId));
    }

}
