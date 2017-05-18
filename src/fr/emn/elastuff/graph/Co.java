package fr.emn.elastuff.graph;

import fr.emn.elastuff.utils.Color;
import fr.emn.elastuff.utils.SysOutLogger;

/**
 * Class that represents a Co.
 * A Co is a cloud resource, so this class extends from CloudResource.
 * @author (review) Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 */
public class Co extends CloudResource {

	private VM vm;
	private String typeCO;
	private int responseTime;

	public Co(String nom, String typeCO, int responseTime) {
		super(nom);
		this.typeCO = typeCO;
		this.responseTime = responseTime;
	}

	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
		this.setChanged();
		this.notifyObservers(this);
	}

	public String getTypeCO() {
		return typeCO;
	}

	public void setTypeCO(String typeCO) {
		this.typeCO = typeCO;
	}

	public VM getVm() {
		return (vm);
	}

	public void setVm(VM v) {
		this.vm = v;
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
