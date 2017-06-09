package perception.configurator.xml;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import perception.configurator.xml.enums.EnumsTestSuite;
import perception.configurator.xml.manager.XMLManagerTestSuite;

/**
 * Suite de tests unitaires JUnit 4 pour les classes utilitaires du projet.
 *
 * @version 1.0
 * @author Chloé Guilbaud
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EnumsTestSuite.class,
        XMLManagerTestSuite.class
})
public class ConfiguratorTestAllSuite {

	/* empty class */

}
