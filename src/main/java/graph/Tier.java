package graph;

import utils.Color;
import utils.SysOutLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that represents a Tier.
 * A Tier is a cloud resource, so this class extends from CloudResource.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class Tier extends CloudResource {

	private Appli appli;

	/**
	 * Constructor of a tier
	 * @param nom The name of the cloud resource
	 */
	public Tier(String nom, int score) {
		super(nom, CloudResourceType.TIER, score);
		this.vms.initResourceList();
	}

	/**
	 * Getter on the appli that contains this Tier
	 * @return The appli that contains this Tier
	 */
	public  Appli getAppli() {
		return (appli);
	}

	/**
	 * Setter on the appli that contains this Tier
	 * @param a The new appli that contains this Tier
	 */
	public void setAppli(Appli a) {
		this.appli = a;
		
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
		SysOutLogger.log("TIER", Color.CYAN);
		SysOutLogger.log("]{");
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
