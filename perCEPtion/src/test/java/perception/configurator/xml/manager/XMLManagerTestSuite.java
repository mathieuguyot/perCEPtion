package perception.configurator.xml.manager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import perception.configurator.xml.manager.parser.XMLParserTestSuite;
import perception.configurator.xml.manager.validator.XMLValidatorTestSuite;

/**
 * Suite de tests unitaires JUnit 4 pour les classes utilitaires du projet.
 *
 * @version 1.0
 * @author Chloé Guilbaud
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        XMLParserTestSuite.class,
        XMLValidatorTestSuite.class
})

public class XMLManagerTestSuite {

	/* empty class */

}
