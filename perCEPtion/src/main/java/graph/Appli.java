package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that represents a cloud application.
 * A cloud application is a cloud resource, so this class extends from CloudResource.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class Appli extends CloudResource {

    /**
     * Constructor of an appli cloud resource
     * @param name The name of the appli
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
    //An appli hold multiple PM Cloud Resources
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
    //An appli hold multiple Tier Cloud Resources
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
