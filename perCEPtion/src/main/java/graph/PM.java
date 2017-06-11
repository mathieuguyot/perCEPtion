package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe représentant une PM (Machine Physique).
 * Une PM est une ressource de Cloud, donc il s'agit d'une extension de {@link CloudResource}.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class PM extends CloudResource {

	private Appli appli;
	//Détails techniques de la PM
	private int cpuConsumption;
	private int ramConsumption;
	private int diskConsumption;

	/**
	 * Constructeur de la classe {@link PM}
	 * @param nom - Le nom de la ressource
	 */
	public PM(String nom, int score) {
		super(nom, CloudResourceType.PM, score);
		this.vms.initResourceList();
		cpuConsumption = 0;
		ramConsumption = 0;
		diskConsumption = 0;
	}

	/**
	 * Accesseur de l'application contenant la PM
	 * @return L'application contenant la PM
	 */
    public Appli getAppli() {
        return appli;
    }

	/**
	 * Modificateur de l'application contenant la PM
	 * @param appli - La nouvelle application contenant la PM
	 */
	public void setAppli(Appli appli) {
        this.appli = appli;
    }

	/**
	 * Accesseur de la consommation CPU de la PM
	 * @return La consommation CPU de la PM
	 */
	public int getCpu_consumption() {
		return cpuConsumption;
	}

	/**
	 * Modificateur de la consommaiton CPU de la PM
	 * @param cpuConsumption - La nouvelle consommation CPU de la PM
	 */
	public void setCpuConsumption(int cpuConsumption) {
		this.cpuConsumption = cpuConsumption;
	}

    /**
     * Accesseur de la consommation RAM de la PM
     * @return La consommation RAM de la PM
     */
	public int getRamConsumption() {
		return ramConsumption;
	}

    /**
     * Modificateur de la consommation RAM de la PM
     * @param ramConsumption - La nouvelle consommation RAM de la PM
     */
	public void setRamConsumption(int ramConsumption) {
		this.ramConsumption = ramConsumption;
	}

    /**
     * Accesseur de la consommation Disque de la PM
     * @return La consommation Disque de la PM
     */
	public int getDiskConsumption() {
		return diskConsumption;
	}

    /**
     * Modificateur de la consommation Disque de la PM
     * @param diskConsumption - La consommation Disque de la PM
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
