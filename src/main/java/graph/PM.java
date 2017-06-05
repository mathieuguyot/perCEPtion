package graph;

import utils.Color;
import utils.SysOutLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that represents a PM.
 * A PM is a cloud resource, so this class extends from CloudResource.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class PM extends CloudResource {

	private Appli appli;
	//PM technicals details
	private int cpuConsumption;
	private int ramConsumption;
	private int diskConsumption;

	/**
	 * Constructor of a PM
	 * @param nom The name of the resource
	 */
	public PM(String nom, int score) {
		super(nom, CloudResourceType.PM, score);
		this.vms.initResourceList();
		cpuConsumption = 0;
		ramConsumption = 0;
		diskConsumption = 0;
	}

	/**
	 * Getter on the appli that contains this PM
	 * @return The appli that contains this PM
	 */
    public Appli getAppli() {
        return appli;
    }

	/**
	 * Setter on the appli that contains this PM
	 * @param appli The appli that contains this PM
	 */
	public void setAppli(Appli appli) {
        this.appli = appli;
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

	@Override
	public int getTotalScore() {
		int retScore = this.getScore();
		for(CloudResource co : this.vms.getResourceList()) {
			retScore += co.getTotalScore();
		}
		return retScore;
	}

	@Override
	public void display(int indent) {
		String indentL = this.getIndent(indent);
		SysOutLogger.log(indentL + "[[");
		SysOutLogger.log("PM", Color.CYAN);
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
		SysOutLogger.log("parent APPLI", Color.BLUE);
		SysOutLogger.log(":");
		if(this.getAppli() == null) {
			SysOutLogger.log("NO APPLI", Color.RED);
		} else {
			SysOutLogger.log("\"" + appli.getName() + "\"", Color.CYAN);
		}
		SysOutLogger.log("}]\n");
		for(int i = 0; i < this.getVMNumber(); i++) {
			this.getVM(i).get().display(indent + 1);
		}
	}

	//--VM---------------------------------------------------------------------
	//An PM hold multiple VM Cloud Resources
	private CloudResourceHolder<VM> vms = new CloudResourceHolder<VM>() {

		private List<VM> vms;

		@Override
		public List<VM> getResourceList() {
			return vms;
		}

		@Override
		public void initResourceList() {
			vms = new ArrayList<>();
		}
	};
	public boolean addVM(VM vm) { return this.vms.addResource(vm); }
	public Optional<VM> getPM(String vmName) { return this.vms.getResource(vmName); }
	public Optional<VM> getVM(int vmId) { return this.vms.getResource(vmId); }
	public boolean isVMExists(String vmName) { return this.vms.isResourceExists(vmName); }
	public int getVMNumber() { return this.vms.getResourceNumber(); }
	//--VM---------------------------------------------------------------------
}
