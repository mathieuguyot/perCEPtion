package configurator.xml.enums;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import perception.configurator.xml.enums.general.FileErrorTypeTest;
import perception.configurator.xml.enums.general.XMLFileStructureTest;
import perception.configurator.xml.enums.parser.ParsingErrorTypeTest;
import perception.configurator.xml.enums.validator.ValidatorErrorTypeTest;

import static org.junit.runners.Suite.SuiteClasses;

/**
 * Suite de tests unitaires JUnit 4 pour les énumérations du system de validation et de parse des fichier XML de
 * configuration des modules du system.
 *
 * @version 1.0
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
        // general enums
        FileErrorTypeTest.class,
        XMLFileStructureTest.class,
        // parser enums
        ParsingErrorTypeTest.class,
        // validator enums
        ValidatorErrorTypeTest.class
})

public class EnumsTestSuite {

	/* empty class */

}
