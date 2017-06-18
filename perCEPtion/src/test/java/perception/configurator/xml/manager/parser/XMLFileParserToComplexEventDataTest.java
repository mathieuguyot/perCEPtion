package perception.configurator.xml.manager.parser;

import org.junit.Test;
import perception.configurator.xml.manager.model.ComplexEventData;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class XMLFileParserToComplexEventDataTest {

    @Test
    public void addEventDataTest() {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<String, String>("Long", "12000"));

        ComplexEventData complexEventData = new ComplexEventData("CEG_Dead_Cpu", "MonComplexEvent1", params);

        ResultatParsing resultatParsing = ResultatParsing.FAB();

        XMLFileParserToComplexEventData parser = new XMLFileParserToComplexEventData();
        parser.addEventData("MonComplexEvent1", "CEG_Dead_Cpu", params, resultatParsing);

        assertEquals(complexEventData, resultatParsing.getComplexEventList().get(0));
    }

    @Test
    public void existingEventWithNameInResultatParsingOK() {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<String, String>("Long", "12000"));

        ComplexEventData complexEventData = new ComplexEventData("CEG_Dead_Cpu", "MonComplexEvent1", params);

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        resultatParsing.addComplexEvent(complexEventData);

        XMLFileParserToComplexEventData parser = new XMLFileParserToComplexEventData();
        boolean exists = parser.existingEventWithNameInResultatParsing("MonComplexEvent1", resultatParsing);

        assertTrue(exists);
    }

    @Test
    public void existingEventWithNameInResultatParsingKO() {
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(new Pair<String, String>("Long", "12000"));

        ComplexEventData complexEventData = new ComplexEventData("CEG_Dead_Cpu", "MonComplexEvent1", params);

        ResultatParsing resultatParsing = ResultatParsing.FAB();
        resultatParsing.addComplexEvent(complexEventData);

        XMLFileParserToComplexEventData parser = new XMLFileParserToComplexEventData();
        boolean exists = parser.existingEventWithNameInResultatParsing("MonComplexEvent2", resultatParsing);

        assertFalse(exists);
    }

}
