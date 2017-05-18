package fr.emn.elastuff.graph;

import fr.emn.elastuff.utils.Color;
import fr.emn.elastuff.utils.SysOutLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that represents a PM.
 * A PM is a cloud resource, so this class extends from CloudResource.
 * @author (review) Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class PM extends CloudResource {

	private Appli appli;
	//PM technicals details
	private int cpuConsumption;
	private int ramConsumption;
	private int diskConsumption;

	public PM(String nom) {
		super(nom);
		this.vms.initResourceList();
	}

    public Appli getAppli() {
        return appli;
    }

    public void setAppli(Appli appli) {
        this.appli = appli;
    }

    public int getCpu_consumption() {
		return cpuConsumption;
	}

	public void setCpu_consumption(int cpuConsumption) {
		this.cpuConsumption = cpuConsumption;
		this.setChanged();
		this.notifyObservers(this);
	}

	public int getRamConsumption() {
		return ramConsumption;
	}

	public void setRamConsumption(int ramConsumption) {
		this.ramConsumption = ramConsumption;
		this.setChanged();
		this.notifyObservers(this);
	}

	public int getDisk_consumption() {
		return diskConsumption;
	}

	public void setDisk_consumption(int diskConsumption) {
		this.diskConsumption = diskConsumption;
		this.setChanged();
		this.notifyObservers(this);
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
