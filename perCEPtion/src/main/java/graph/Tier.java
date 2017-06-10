package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe représentant un Tier.
 * Un Tier est une ressource de Cloud, donc il s'agit d'une extension de {@link CloudResource}.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class Tier extends CloudResource {

	private Appli appli;

	/**
	 * Constructeur de la classe {@link Tier}
	 * @param nom - Le nom de la ressource de cloud
	 */
	public Tier(String nom, int score) {
		super(nom, CloudResourceType.TIER, score);
		this.vms.initResourceList();
	}

	/**
	 * Accesseur de l'{@link Appli} contenant le Tier
	 * @return L'application contenant le Tier
	 */
	public  Appli getAppli() {
		return (appli);
	}

	/**
	 * Modificateur de l'{@link Appli} contenant le Tier
	 * @param a - La nouvelle application contenant le Tier
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
	//Une PM contient de multiples Ressources de Cloud VM
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
