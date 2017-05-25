package graph;

import utils.Color;
import utils.SysOutLogger;

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
	public VM(String name) {
		super(name, CloudResourceType.VM);
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

    @Override
    public void display(int indent) {
        String indentL = this.getIndent(indent);
        SysOutLogger.log(indentL + "[[");
        SysOutLogger.log("VM", Color.CYAN);
        SysOutLogger.log("]{");
        SysOutLogger.log("cpu", Color.BLUE);
        SysOutLogger.log(":");
        SysOutLogger.log(String.valueOf(cpuConsumption), Color.MAGENTA);
        SysOutLogger.log(", ");
        SysOutLogger.log("ram", Color.BLUE);
        SysOutLogger.log(":");
        SysOutLogger.log(String.valueOf(ramConsumption), Color.MAGENTA);
        SysOutLogger.log(", ");
        SysOutLogger.log("disk", Color.BLUE);
        SysOutLogger.log(":");
        SysOutLogger.log(String.valueOf(diskConsumption), Color.MAGENTA);
        SysOutLogger.log("}{");
        SysOutLogger.log("name", Color.BLUE);
        SysOutLogger.log(":");
        SysOutLogger.log("\"" + name + "\"", Color.CYAN);
        SysOutLogger.log("}{");
        SysOutLogger.log("parent PM", Color.BLUE);
        SysOutLogger.log(":");
        if(this.getPm() == null) {
            SysOutLogger.log("NO PM", Color.RED);
        } else {
            SysOutLogger.log("\"" + pm.getName() + "\"", Color.CYAN);
        }
        SysOutLogger.log(", ");
        SysOutLogger.log("parent Tier", Color.BLUE);
        SysOutLogger.log(":");
        if(this.getTier() == null) {
            SysOutLogger.log("NO TIER", Color.RED);
        } else {
            SysOutLogger.log("\"" + tier.getName() + "\"", Color.CYAN);
        }
        SysOutLogger.log("}]\n");
        for(int i = 0; i < this.getCoNumber(); i++) {
            this.getCo(i).get().display(indent + 1);
        }
    }

}
