package fr.emn.elastuff.perCEPtion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import fr.emn.elastuff.graph.Appli;
import fr.emn.elastuff.graph.CloudResource;
import fr.emn.elastuff.graph.Co;
import fr.emn.elastuff.graph.PM;
import fr.emn.elastuff.graph.Tier;
import fr.emn.elastuff.graph.VM;

/**
 * This class is used to simulate different cloudRessource which are linked between them
 * You can change different cloudResource value with this class 
 *
 */
public class CloudRessourceSimulator {

	private Map<String, CloudResource> cloudsRessources;

	public CloudRessourceSimulator() {
		this.cloudsRessources = new HashMap<>();
	}
	
	/**
	 * create all the components of a cloudRessource, build the graph and set each component in an HashMap
	 * @see <cloudsRessources>
	 */
	
	
	
	 
	public void createCloudResources() {
		
		// resources
		Appli appli = new Appli("app1");

		Tier tier1 = new Tier("T1");
		Tier tier2 = new Tier("T2");

		PM pm1 = new PM("PM1");
		PM pm2 = new PM("PM2");

		VM vm1 = new VM("VM1");
		VM vm2 = new VM("VM2");
		VM vm3 = new VM("VM3");

		Co co1 = new Co("co1", "mySQL", 50);
		Co co2 = new Co("co2", "mySQL", 40);
		Co co3 = new Co("co3", "mySQL", 30);
		Co co4 = new Co("co4", "mySQL", 20);

		// graph
		appli.addPM(pm1);
		appli.addPM(pm2);
		appli.addTier(tier1);
		appli.addTier(tier2);

		tier1.addVM(vm1);
		tier2.addVM(vm2);
		tier2.addVM(vm3);

		try {
			pm1.addVM(vm1);
			pm2.addVM(vm2);
			pm2.addVM(vm3);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		vm1.addCo(co1);
		vm1.addCo(co2);
		vm2.addCo(co3);
		vm3.addCo(co4);

		vm1.setTier(tier1);
		vm2.setTier(tier1);
		vm3.setTier(tier2);

		co1.setVm(vm1);
		co2.setVm(vm1);
		co3.setVm(vm2);
		co4.setVm(vm3);

		// cloudRessources
		cloudsRessources.put(appli.getName(), appli);
		cloudsRessources.put(tier1.getName(), tier1);
		cloudsRessources.put(tier2.getName(), tier2);
		cloudsRessources.put(pm1.getName(), pm1);
		cloudsRessources.put(pm2.getName(), pm2);
		cloudsRessources.put(vm1.getName(), vm1);
		cloudsRessources.put(vm2.getName(), vm2);
		cloudsRessources.put(vm3.getName(), vm3);
		cloudsRessources.put(co1.getName(), co1);
		cloudsRessources.put(co2.getName(), co2);
		cloudsRessources.put(co3.getName(), co3);
		cloudsRessources.put(co4.getName(), co4);

		System.out.println("\n***************** Initial graph structure *******************\n");
		appli.display();

	}

	/**
	 * set the <value> to a component <name> with the method <methodName> 
	 * the value must be an integer and the component must be in the HashMap <cloudsRessources>
	 * 
	 * @param name
	 * @param methodName
	 * @param value
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void setValue(String name, String methodName, Object value) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (cloudsRessources.get(name) == null)
			throw new IllegalArgumentException("the cloudRessource " + name + " doesn't exist");

		Method method = cloudsRessources.get(name).getClass()
				.getMethod(this.parse(cloudsRessources.get(name), methodName), int.class);
		method.invoke(cloudsRessources.get(name), value);
	}

	/**
	 * Change the <command> into a real method for the CloudResource <cr>
	 * exemple : parse("VM","vcpu") -> "setVcpu_consumption"
	 * @param cr the cloudResource
	 * @param command the command
	 * @return the real method
	 */
	public String parse(CloudResource cr, String command) {
		if (cr instanceof VM | cr instanceof PM) {
			if ("vcpu".equals(command))
				return "setVcpuConsumption";
			if ("ram".equals(command))
				return "setRamConsumption";
			if ("disk".equals(command))
				return "setDiskConsumption";
			throw new IllegalArgumentException("the parameter " + command + " doesn't exist for " + cr.getClass());
		}
		if (cr instanceof PM) {
			if ("cpu".equals(command))
				return "setCpuConsumption";
			if ("ram".equals(command))
				return "setRamConsumption";
			if ("disk".equals(command))
				return "setDiskConsumption";
			throw new IllegalArgumentException("the parameter " + command + " doesn't exist for PM");
		}
		if (cr instanceof Co) {
			if ("RT".equals(command))
				return "setResponseTime";
			throw new IllegalArgumentException("the parameter " + command + " doesn't exist for Co");
		}
		throw new IllegalArgumentException("the parameter " + command + " doesn't exist for " + cr.getName());
	}

	/**
	 * return our hashmap with all the cloudResources instantiated
	 * the method createCloudResource must be call before this method for a real return value
	 * @return our cloudRessources
	 */
	public Map<String, CloudResource> getCloudRessources() {
		return this.cloudsRessources;
	}
}
