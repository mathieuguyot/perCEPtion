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
        assertEquals("getLabel - EVENT_PRIMITIVES", "primitives", XMLFileStructure.EVENT_PRIMITIVES.getLabel());
        assertEquals("getLabel - EVENT_PRIMITIVE", "primitive", XMLFileStructure.EVENT_PRIMITIVE.getLabel());
        assertEquals("getLabel - EVENT_ATTR_ENABLED", "enabled", XMLFileStructure.EVENT_ATTR_ENABLED.getLabel());
        assertEquals("getLabel - EVENT_NAME", "name", XMLFileStructure.EVENT_NAME.getLabel());
        assertEquals("getLabel - EVENT_PRIMITIVE_RUNTIME", "runtime", XMLFileStructure.EVENT_PRIMITIVE_RUNTIME.getLabel());
        assertEquals("getLabel - NAMESPACE", "http://www.w3.org/2001/XMLSchema-instance", XMLFileStructure.NAMESPACE.getLabel());
    }

    @Test
    public void testFromLabel() {
        assertEquals("fromLabel - RACINE_PERCEPTION", XMLFileStructure.RACINE_PERCEPTION, XMLFileStructure.fromLabel("perception"));
        assertEquals("fromLabel - EVENTS", XMLFileStructure.EVENTS, XMLFileStructure.fromLabel("events"));
        assertEquals("fromLabel - EVENT_PRIMITIVES", XMLFileStructure.EVENT_PRIMITIVES, XMLFileStructure.fromLabel("primitives"));
        assertEquals("fromLabel - EVENT_PRIMITIVE", XMLFileStructure.EVENT_PRIMITIVE, XMLFileStructure.fromLabel("primitive"));
        assertEquals("fromLabel - EVENT_ATTR_ENABLED", XMLFileStructure.EVENT_ATTR_ENABLED, XMLFileStructure.fromLabel("enabled"));
        assertEquals("fromLabel - EVENT_NAME", XMLFileStructure.EVENT_NAME, XMLFileStructure.fromLabel("name"));
        assertEquals("fromLabel - EVENT_PRIMITIVE_RUNTIME", XMLFileStructure.EVENT_PRIMITIVE_RUNTIME, XMLFileStructure.fromLabel("runtime"));
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
                XMLFileStructure.EVENT_PRIMITIVES, XMLFileStructure.EVENT_PRIMITIVE,
                XMLFileStructure.EVENT_ATTR_ENABLED, XMLFileStructure.EVENT_NAME,
                XMLFileStructure.EVENT_PRIMITIVE_RUNTIME, XMLFileStructure.NAMESPACE);
        assertEquals("valuesAsList - taille", 17, XMLFileStructure.valuesAsList().size());
        assertTrue("valuesAsList - values", XMLFileStructure.valuesAsList().containsAll(xMLFileStructureList));
    }

}
