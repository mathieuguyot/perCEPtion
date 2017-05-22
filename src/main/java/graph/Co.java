package graph;

import utils.Color;
import utils.SysOutLogger;

/**
 * Class that represents a Co.
 * A Co is a cloud resource, so this class extends from CloudResource.
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class Co extends CloudResource {

	private VM vm;
	private String typeCO;
	private int responseTime;

	/**
	 * Constructor of a co resource
	 * @param nom The name of the resource
	 * @param typeCO The type of CO
	 * @param responseTime The response time of the CO
	 */
	public Co(String nom, String typeCO, int responseTime) {
		super(nom, CloudResourceType.CO);
		this.typeCO = typeCO;
		this.responseTime = responseTime;
	}

    /**
     * Getter on the response time of the CO
     * @return The response time of the CO
     */
	public int getResponseTime() {
		return responseTime;
	}

    /**
     * Setter on the response time of the CO
     * @param responseTime The new response time
     */
	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

    /**
     * Getter on the type of the CO
     * @return The type of the CO
     */
	public String getTypeCO() {
		return typeCO;
	}

    /**
     * Setter on the type of the CO
     * @param typeCO The new type of the CO
     */
	public void setTypeCO(String typeCO) {
		this.typeCO = typeCO;
	}

    /**
     * Getter on the VM that contains this CO
     * @return The VM that contains this CO
     */
	public VM getVm() {
		return (vm);
	}

    /**
     * Setter on the VM that contains this CO
     * @param vm The new VM that contains this CO
     */
	public void setVm(VM vm) {
		this.vm = vm;
	}

	@Override
	public void display(int indent) {
	    String indentL = this.getIndent(indent);
        SysOutLogger.log(indentL + "[[");
        SysOutLogger.log("CO", Color.CYAN);
        SysOutLogger.log("]{");
        SysOutLogger.log("type", Color.BLUE);
        SysOutLogger.log(":");
        SysOutLogger.log("\"" + typeCO + "\"", Color.MAGENTA);
        SysOutLogger.log(", ");
        SysOutLogger.log("responseTime", Color.BLUE);
        SysOutLogger.log(":");
        SysOutLogger.log(String.valueOf(responseTime), Color.MAGENTA);
        SysOutLogger.log("}{");
        SysOutLogger.log("name", Color.BLUE);
        SysOutLogger.log(":");
        SysOutLogger.log("\"" + name + "\"", Color.CYAN);
        SysOutLogger.log("}{");
        SysOutLogger.log("parent VM", Color.BLUE);
        SysOutLogger.log(":");
        if(this.getVm() == null) {
            SysOutLogger.log("NO VM", Color.RED);
        } else {
            SysOutLogger.log("\"" + vm.getName() + "\"", Color.CYAN);
        }
        SysOutLogger.log("}]\n");
	}

}
