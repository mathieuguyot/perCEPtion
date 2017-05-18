package fr.emn.elastuff.perCEPtion;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom.JDOMException;


public class Perception {

	private static Logger logger = Logger.getLogger("mainLogger");
	private static final String needHelp = "type /help to see each command";
	private static final String printHelp = "/run : launch the supervision\n/stop : stop the supervision\n/xml <filepath> : set xml config in perCEPtion\n/exit : stop the application\n/simulate <cloudRessource> <component> <value> : set a value in a cloudRessource\n	ex: /simulate VM1 Vcpu 50";
	private static final String commandHelp = "/help";
	private static final String commandRun = "/run";
	private static final String commandXML = "/xml";
	private static final String commandStop = "/stop";
	private static final String commandExit = "/exit";
	private static final String commandSimulate = "/simulate";

	private Scanner sc;
	private EsperManager em;
	private CloudRessourceSimulator sim;
	private boolean loopLaunching;
	private boolean xmlSetted;
	private boolean isRunning;

	public Perception() {
		sim = new CloudRessourceSimulator();
		this.sc = new Scanner(System.in);
		this.loopLaunching = true;
		this.xmlSetted = false;
		this.isRunning = false;

	}

	
	
	public void initialize() {
		sim.createCloudResources();
		this.em = new EsperManager(sim.getCloudRessources().values());
		
		
	}

	public void launch() {
		while (loopLaunching) {
			this.parse(this.waitCommand());
		}
	}

	private void readXML(File file) throws JDOMException, IOException, ParserXMLException {
		em.readXml(file);
	}

	private void run() {
		em.addStatements();
		this.isRunning = true;
	}

	private void stop() {
		em.removeStatements();
		this.isRunning = false;
	}

	private void exit() {
		this.stop();
		this.loopLaunching = false;
	}

	private String waitCommand() {
		return sc.nextLine();
	}

	// parsing each command in the console
	public void parse(String line) {
		if (line == null) {
			System.out.println(Perception.needHelp);
			return;
		}
		StringTokenizer commandTokenizer = new StringTokenizer(line, " ");
		if (commandTokenizer.hasMoreTokens()) {
			String command = commandTokenizer.nextToken();
			if (command == null) {
				System.out.println(Perception.needHelp);
				return;
			}

			if (command.equals(Perception.commandHelp)) {
				System.out.println(Perception.printHelp);
				return;
			}

			if (command.equals(Perception.commandRun)) {
				if (!this.xmlSetted) {
					System.out.println("PERCEPTION : set the xml file before running the supervision");
					return;
				}
				System.out.println("PERCEPTION : Launching");
				this.run();
				this.isRunning = true;
				return;
			}

			if (command.equals(Perception.commandStop)) {
				System.out.println("PERCEPTION : Stopping");
				this.isRunning = false;
				this.stop();
				return;
			}

			if (command.equals(Perception.commandXML)) {
				if (!commandTokenizer.hasMoreTokens()) {
					System.out.println("ERROR : the command " + Perception.commandXML + " need an argument <filepath>");
					System.out.println(Perception.needHelp);
					return;
				}

				String filepath = commandTokenizer.nextToken();
				File file = new File(filepath);

				if (!file.exists()) {
					System.out.println("ERROR : the file " + filepath + " doesn't exist");
					return;
				}

				try {
					this.readXML(file);
					this.xmlSetted = true;
					System.out.println("PERCEPTION : Xml setted");
					return;
				} catch (JDOMException e) {
					System.out.println("ERROR : " + e.getMessage());
				} catch (IOException e) {
					System.out.println("ERROR : " + e.getMessage());
				} catch (ParserXMLException e) {
					System.out.println("ERROR : the xml file does't match");
					System.out.println("ERROR : " + e.getLocalizedMessage());
				}
			}

			if (command.equals(Perception.commandExit)) {
				System.out.println("PERCEPTION : Exit");
				this.exit();
				return;
			}

			// /simulate VM1 cpu 15
			if (command.equals(Perception.commandSimulate)) {
				if (!this.isRunning) {
					System.out.println("PERCEPTION : run the application before simulating");
					return;
				}

				if (!commandTokenizer.hasMoreTokens()) {
					System.out.println(
							"ERROR : the command " + Perception.commandSimulate + " need an argument <cloudRessource>");
					System.out.println(Perception.needHelp);
					return;
				}

				String cr = commandTokenizer.nextToken();

				if (!commandTokenizer.hasMoreTokens()) {
					System.out.println(
							"ERROR : the command " + Perception.commandSimulate + " need an argument <component>");
					System.out.println(Perception.needHelp);
					return;
				}

				String compo = commandTokenizer.nextToken();

				if (!commandTokenizer.hasMoreTokens()) {
					System.out
							.println("ERROR : the command " + Perception.commandSimulate + " need an argument <value>");
					System.out.println(Perception.needHelp);
					return;
				}

				String v = commandTokenizer.nextToken();
				try {
					Integer.valueOf(v);
				} catch (NumberFormatException e) {
					System.out.println(
							"ERROR : the <value> of the command " + Perception.commandSimulate + " must be an integer");
					return;
				}

				int value = Integer.valueOf(v);
				try {
					sim.setValue(cr, compo, value);
					
					
					logger.info("PERCEPTION : value " + value + " setted in " + compo + " on " + cr);
					
					
				} catch (NoSuchMethodException e) {
					System.out.println("ERROR : " + e.getLocalizedMessage());
				} catch (SecurityException e) {
					System.out.println("ERROR : " + e.getLocalizedMessage());
				} catch (IllegalAccessException e) {
					System.out.println("ERROR : " + e.getLocalizedMessage());
				} catch (IllegalArgumentException e) {
					System.out.println("ERROR : " + e.getLocalizedMessage());
				} catch (InvocationTargetException e) {
					System.out.println("ERROR : " + e.getLocalizedMessage());
				}
				return;
			}

			logger.error("The command " + command + " doesn't exist");
		}
	}

	public static void main(String args[]) {
		//PropertyConfigurator.configure("logs/log4j/log4j.properties");

		Perception perCEPtion = new Perception();
		perCEPtion.initialize();
		perCEPtion.launch();
	}

}
