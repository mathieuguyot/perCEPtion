package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe représentant un cloud d'application.
 * Un cloud d'application est une ressource du cloud, donc il s'agit d'une extension de {@link CloudResource}
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class Appli extends CloudResource {

    /**
     * Constructeur d'une ressource du cloud d'applicationConstructor of an appli cloud resource
     * @param name - Le nom de l'application
     */
    public Appli(String name, int score) {
        super(name, CloudResourceType.APPLI, score);
        this.pms.initResourceList();
        this.tiers.initResourceList();
    }

    @Override
    public int getTotalScore() {
        int retScore = this.getScore();
        for(CloudResource co : this.pms.getResourceList()) {
            retScore += co.getTotalScore();
        }
        for(CloudResource co : this.tiers.getResourceList()) {
            retScore += co.getTotalScore();
        }
        return retScore;
    }

    //--PM---------------------------------------------------------------------
    //Une application contient de multiples Ressources de Cloud PM
    private CloudResourceHolder<PM> pms = new CloudResourceHolder<PM>() {

        private List<PM> pms;

        @Override
        public List<PM> getResourceList() {
            return pms;
        }

        @Override
        public void initResourceList() {
            pms = new ArrayList<>();
        }
    };
    public boolean addPM(PM pm) { return this.pms.addResource(pm); }
    public Optional<PM> getPM(String pmName) { return this.pms.getResource(pmName); }
    public Optional<PM> getPM(int pmId) { return this.pms.getResource(pmId); }
    public boolean isPMExists(String pmName) { return this.pms.isResourceExists(pmName); }
    public int getPMNumber() { return this.pms.getResourceNumber(); }
    //--PM---------------------------------------------------------------------

    //--TIER-------------------------------------------------------------------
    //Une application contient de multiples Ressources de Cloud Tier
    private CloudResourceHolder<Tier> tiers = new CloudResourceHolder<Tier>() {

        private List<Tier> tiers;

        @Override
        public List<Tier> getResourceList() {
            return tiers;
        }

        @Override
        public void initResourceList() {
            tiers = new ArrayList<>();
        }
    };
    public boolean addTier(Tier tier) { return this.tiers.addResource(tier); }
    public Optional<Tier> getTier(String tierName) { return this.tiers.getResource(tierName); }
    public Optional<Tier> getTier(int pmId) { return this.tiers.getResource(pmId); }
    public boolean isTierExists(String pmName) { return this.tiers.isResourceExists(pmName); }
    public int getTierNumber() { return this.tiers.getResourceNumber(); }
    //--TIER-------------------------------------------------------------------

}
