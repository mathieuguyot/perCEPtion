package graph;

/**
 * Classe représentant un Co.
 * Un Co est une ressource de cloud, donc il s'agit d'une extension de {@link CloudResource}.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class Co extends CloudResource {

	private VM vm;
	private String typeCO;
	private int responseTime;

	/**
	 * Constructeur de la classe {@link Co}
	 * @param nom - Le nom de la ressource
	 * @param typeCO - Le type du Co
	 * @param responseTime - Le temps de réponse du Co
	 */
	public Co(String nom, String typeCO, int score, int responseTime) {
		super(nom, CloudResourceType.CO, score);
		this.typeCO = typeCO;
		this.responseTime = responseTime;
	}

    /**
     * Accesseur du temps de réponse du Co
     * @return Le temps de réponse du Co
     */
	public int getResponseTime() {
		return responseTime;
	}

    /**
     * Modificateur du temps de réponse du Co
     * @param responseTime - Le nouveau temps de réponse
     */
	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

    /**
     * Récupère le type du Co
     * @return Le type du CO
     */
	public String getTypeCO() {
		return typeCO;
	}

    /**
     * Modificateur du type du Co
     * @param typeCO - Le nouveau type du Co
     */
	public void setTypeCO(String typeCO) {
		this.typeCO = typeCO;
	}

    /**
     * Accesseur de la VM contenant le Co
     * @return La VM contenant le Co
     */
	public VM getVm() {
		return (vm);
	}

    /**
     * Modificateur de la VM contenant le Co
     * @param vm La nouvelle VM contenant le Co
     */
	public void setVm(VM vm) {
		this.vm = vm;
	}

	@Override
	public int getTotalScore() {
		return this.getScore();
	}

}
