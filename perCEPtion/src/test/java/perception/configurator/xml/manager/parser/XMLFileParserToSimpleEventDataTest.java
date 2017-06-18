package perception.configurator.xml.manager.parser;

import org.junit.Test;
import perception.configurator.xml.manager.model.SimpleEventData;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLFileParserToSimpleEventDataTest {

    @Test
    public void addEventDataTest() {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<String, String>("Long", "12000"));

        SimpleEventData simpleEventData = new SimpleEventData("SEG_Cpu_Drop", "MonSimpleEvent1", params);

        ResultatParsing resultatParsing = ResultatParsing.FAB();

        XMLFileParserToSimpleEventData parser = new XMLFileParserToSimpleEventData();
        parser.addEventData("MonSimpleEvent1", "SEG_Cpu_Drop", params, resultatParsing);

        assertEquals(simpleEventData, resultatParsing.getSimpleEventList().get(0));
    }

    @Test
    public void existingEventWithNameInResultatParsingOK() {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<String, String>("Long", "12000"));

        SimpleEventData simpleEventData = new SimpleEventData("SEG_Cpu_Drop", "MonSimpleEvent1", params);

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        resultatParsing.addSimpleEvent(simpleEventData);

        XMLFileParserToSimpleEventData parser = new XMLFileParserToSimpleEventData();
        boolean exists = parser.existingEventWithNameInResultatParsing("MonSimpleEvent1", resultatParsing);

        assertTrue(exists);
    }

    @Test
    public void existingEventWithNameInResultatParsingKO() {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<String, String>("Long", "12000"));

        SimpleEventData simpleEventData = new SimpleEventData("SEG_Cpu_Drop", "MonSimpleEvent1", params);

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        resultatParsing.addSimpleEvent(simpleEventData);

        XMLFileParserToSimpleEventData parser = new XMLFileParserToSimpleEventData();
        boolean exists = parser.existingEventWithNameInResultatParsing("MonSimpleEvent2", resultatParsing);

        assertFalse(exists);
    }

}
