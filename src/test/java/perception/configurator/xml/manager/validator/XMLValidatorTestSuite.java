package perception.configurator.xml.manager.validator;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.SuiteClasses;

/**
 * Suite de tests unitaires JUnit 4 pour les classes utilitaires du projet.
 *
 * @version 1.0
 * @author Chloé Guilbaud
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
        ValidationErrorTest.class,
        ValidationResultTest.class,
        XMLFileValidatorTest.class,
        XMLValidatorErrorHandlerTest.class
})

public class XMLValidatorTestSuite {

	/* empty class */

}
