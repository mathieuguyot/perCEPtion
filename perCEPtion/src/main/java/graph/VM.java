package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that represents a VM.
 * A VM is a cloud resource, so this class extends from CloudResource.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class VM extends CloudResource {

	private PM pm; //physical parent
	private Tier tier; //logical parent
	//VM technicals details
    int cpuConsumption;
    int ramConsumption;
    int diskConsumption;

    /**
     * Constructor of a VM
     * @param name The name of the cloud resource
     */
	public VM(String name, int score) {
		super(name, CloudResourceType.VM, score);
        this.cos.initResourceList();
	}

    /**
     * Getter on the cpu consumption of the PM
     * @return The cpu consumption of the PM
     */
    public int getCpu_consumption() {
        return cpuConsumption;
    }

    /**
     * Setter on the cpu consumption of the PM
     * @param cpuConsumption The new cpu consumption of the PM
     */
    public void setCpuConsumption(int cpuConsumption) {
        this.cpuConsumption = cpuConsumption;
    }

    /**
     * Getter on the ram consumption of the PM
     * @return The ram consumption of the PM
     */
    public int getRamConsumption() {
        return ramConsumption;
    }

    /**
     * Setter on the ram consumption of the PM
     * @param ramConsumption The ram cpu consumption of the PM
     */
    public void setRamConsumption(int ramConsumption) {
        this.ramConsumption = ramConsumption;
    }

    /**
     * Getter on the disk consumption of the PM
     * @return The disk consumption of the PM
     */
    public int getDiskConsumption() {
        return diskConsumption;
    }

    /**
     * Setter on the disk consumption of the PM
     * @param diskConsumption The disk cpu consumption of the PM
     */
    public void setDiskConsumption(int diskConsumption) {
        this.diskConsumption = diskConsumption;
    }

    /**
     * Getter on the PM that contains this VM
     * @return The PM that contains this VM
     */
    public PM getPm() {
		return (pm);
	}

    /**
     * Setter on the PM that contains this VM
     * @param p The PM that contains this VM
     */
	public  void setPm(PM p) {
		this.pm = p;
	}

    /**
     * Getter on the Tier that contains this VM
     * @return The Tier that contains this VM
     */
	public Tier getTier() {
		return (tier);
	}

    /**
     * Setter on the Tier that contains this VM
     * @param t The Tier that contains this VM
     */
	public void setTier(Tier t) {
		this.tier = t;
	}

    @Override
    public int getTotalScore() {
        int retScore = this.getScore();
        for(CloudResource co : this.cos.getResourceList()) {
            retScore += co.getTotalScore();
        }
        return retScore;
    }

    //--CO---------------------------------------------------------------------
    //An VM hold multiple Co Cloud Resources
    private CloudResourceHolder<Co> cos = new CloudResourceHolder<Co>() {

        private List<Co> cos;

        @Override
        public List<Co> getResourceList() {
            return cos;
        }

        @Override
        public void initResourceList() {
            cos = new ArrayList<>();
        }
    };
    public boolean addCo(Co co) { return this.cos.addResource(co); }
    public Optional<Co> getCo(String coName) { return this.cos.getResource(coName); }
    public Optional<Co> getCo(int coId) { return this.cos.getResource(coId); }
    public boolean isCoExists(String coName) { return this.cos.isResourceExists(coName); }
    public int getCoNumber() { return this.cos.getResourceNumber(); }
    //--Co---------------------------------------------------------------------

}
