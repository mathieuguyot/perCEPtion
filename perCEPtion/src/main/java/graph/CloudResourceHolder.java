package graph;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Interface représentant une liste de {@link CloudResource}
 * (du même type) contenues par la classe qui l'implémente.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public interface CloudResourceHolder<CRImpl extends CloudResource> {

    //Liste de Ressources
    List<CRImpl> getResourceList();
    void initResourceList();

    //Méthode par défaut
    default boolean addResource(CRImpl res) {
        return !this.isResourceExists(res.getName()) && this.getResourceList().add(res);
    }

    /**
     * Récupère le nombre de Ressources contenues dans le {@link CloudResourceHolder}
     * @return Le nombre de ressources contenues dans le holder
     */
    default int getResourceNumber() {
        return this.getResourceList().size();
    }

    /**
     * Vérifie si la ressource est présente
     * @param resName - Nom de la ressource
     * @return <code>true</code> si la ressource est présente, <code>false</code> sinon
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
     * Accesseur de la ressource contenue
     * @param resName - Nom de la ressource
     * @return La ressource (encapsulée dans un {@link Optional}) ou dans un <code>empty</code> si la ressource n'existe pas
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
     * Accesseur d'une sous-ressource
     * @param resId - Identifiant de la sous-ressource
     * @return La ressource (encapsulée dans un {@link Optional}) ou dans un <code>empty</code> si la ressource n'existe pas
     */
    default Optional<CRImpl> getResource(int resId) {
        if(resId >= this.getResourceNumber()) {
            return Optional.empty();
        }
        return Optional.of(this.getResourceList().get(resId));
    }

}
