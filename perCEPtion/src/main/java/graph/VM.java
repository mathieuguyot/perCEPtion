package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe représentant une VM.
 * Une VM est une ressource de cloud, donc il s'agit d'une extension de {@link CloudResource}.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class VM extends CloudResource {

	private PM pm; //Parent physique
	private Tier tier; //Parent logique
	//Détails techniques de la VM
    int cpuConsumption;
    int ramConsumption;
    int diskConsumption;

    /**
     * Constructeur de la classe {@link VM}
     * @param name - Nom de la ressource de cloud
     */
	public VM(String name, int score) {
		super(name, CloudResourceType.VM, score);
        this.cos.initResourceList();
	}

    /**
     * Accesseur de la consommation CPU de la PM
     * @return La consommation CPU de la PM
     */
    public int getCpu_consumption() {
        return cpuConsumption;
    }

    /**
     * Modificateur de la consommation CPU de la PM
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
     * @param diskConsumption - La nouvelle consommation Disque de la PM
     */
    public void setDiskConsumption(int diskConsumption) {
        this.diskConsumption = diskConsumption;
    }

    /**
     * Accesseur de la {@link PM} contenant la VM
     * @return La PM contenant la VM
     */
    public PM getPm() {
		return (pm);
	}

    /**
     * Modificateur de la {@link PM} contenant la VM
     * @param p - La PM contenant la VM
     */
	public  void setPm(PM p) {
		this.pm = p;
	}

    /**
     * Accesseur du {@link Tier} contenant la VMGetter on the Tier that contains this VM
     * @return The Tier that contains this VM
     */
	public Tier getTier() {
		return (tier);
	}

    /**
     * Modificateur du {@link Tier} contenant la VM
     * @param t - Le nouveau Tier contenant la VM
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
    //Une VM contient de multiples Ressources de Cloud Co
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
