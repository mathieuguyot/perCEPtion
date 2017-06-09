package graph;

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
