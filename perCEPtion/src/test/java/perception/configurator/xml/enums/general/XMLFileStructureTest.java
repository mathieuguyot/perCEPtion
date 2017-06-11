package perception.configurator.xml.enums.general;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XMLFileStructureTest {

    @Test
    public void testGetLabel() {
        assertEquals("getLabel - RACINE_PERCEPTION", "perception", XMLFileStructure.RACINE_PERCEPTION.getLabel());
        assertEquals("getLabel - EVENTS", "events", XMLFileStructure.EVENTS.getLabel());
        assertEquals("getLabel - PRIMITIVES", "primitives", XMLFileStructure.PRIMITIVES.getLabel());
        assertEquals("getLabel - PRIMITIVE", "primitive", XMLFileStructure.PRIMITIVE.getLabel());
        assertEquals("getLabel - PRIMITIVE_ATTR_ENABLED", "enabled", XMLFileStructure.PRIMITIVE_ATTR_ENABLED.getLabel());
        assertEquals("getLabel - PRIMITIVE_NAME", "name", XMLFileStructure.PRIMITIVE_NAME.getLabel());
        assertEquals("getLabel - PRIMITIVE_RUNTIME", "runtime", XMLFileStructure.PRIMITIVE_RUNTIME.getLabel());
        assertEquals("getLabel - NAMESPACE", "http://www.w3.org/2001/XMLSchema-instance", XMLFileStructure.NAMESPACE.getLabel());
    }

    @Test
    public void testFromLabel() {
        assertEquals("fromLabel - RACINE_PERCEPTION", XMLFileStructure.RACINE_PERCEPTION, XMLFileStructure.fromLabel("perception"));
        assertEquals("fromLabel - EVENTS", XMLFileStructure.EVENTS, XMLFileStructure.fromLabel("events"));
        assertEquals("fromLabel - PRIMITIVES", XMLFileStructure.PRIMITIVES, XMLFileStructure.fromLabel("primitives"));
        assertEquals("fromLabel - PRIMITIVE", XMLFileStructure.PRIMITIVE, XMLFileStructure.fromLabel("primitive"));
        assertEquals("fromLabel - PRIMITIVE_ATTR_ENABLED", XMLFileStructure.PRIMITIVE_ATTR_ENABLED, XMLFileStructure.fromLabel("enabled"));
        assertEquals("fromLabel - PRIMITIVE_NAME", XMLFileStructure.PRIMITIVE_NAME, XMLFileStructure.fromLabel("name"));
        assertEquals("fromLabel - PRIMITIVE_RUNTIME", XMLFileStructure.PRIMITIVE_RUNTIME, XMLFileStructure.fromLabel("runtime"));
        assertEquals("fromLabel - NAMESPACE", XMLFileStructure.NAMESPACE, XMLFileStructure.fromLabel("http://www.w3.org/2001/XMLSchema-instance"));
    }

    @Test
    public void testFromLabelIgnoreCase() {
        XMLFileStructure res = XMLFileStructure.fromLabel("hTtp://www.W3.orG/2001/XMlSchema-insTance");
        assertEquals("FromLibelle IgnoreCase NAMESPACE ", XMLFileStructure.NAMESPACE, res);
    }

    @Test
    public void testFromLabelInvalide() {
        XMLFileStructure res = XMLFileStructure.fromLabel("N'ImporTquOi");
        assertEquals("FromLibelle Invalide", null, res);
    }

    @Test
    public void testValuesAsList() {
        List<XMLFileStructure> xMLFileStructureList = Arrays.asList(
                XMLFileStructure.RACINE_PERCEPTION, XMLFileStructure.EVENTS,
                XMLFileStructure.PRIMITIVES, XMLFileStructure.PRIMITIVE,
                XMLFileStructure.PRIMITIVE_ATTR_ENABLED, XMLFileStructure.PRIMITIVE_NAME,
                XMLFileStructure.PRIMITIVE_RUNTIME, XMLFileStructure.NAMESPACE);
        assertEquals("valuesAsList - taille", 9, XMLFileStructure.valuesAsList().size());
        assertTrue("valuesAsList - values", XMLFileStructure.valuesAsList().containsAll(xMLFileStructureList));
    }

}
