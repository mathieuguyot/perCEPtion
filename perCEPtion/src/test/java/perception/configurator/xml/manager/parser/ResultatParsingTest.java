package perception.configurator.xml.manager.parser;

import org.junit.Test;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.ComplexEventData;
import perception.configurator.xml.manager.model.PrimitiveEventData;
import perception.configurator.xml.manager.model.SimpleEventData;
import perception.configurator.xml.manager.validator.ValidationError;
import perception.configurator.xml.manager.validator.ValidationResult;
import utils.Pair;

import java.util.*;

import static org.junit.Assert.*;

public class ResultatParsingTest {

	@Test
	public void testResultatParsing_WithParams() {

		List<FileErrorType> fileErrorTypes = Arrays.asList(FileErrorType.FILE_NOT_FOUND, FileErrorType.FILE_NOT_FOUND);
		List<ParsingErrorType> parsingErrorTypes = Arrays.asList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_NAME,
				ParsingErrorType.EVENT_PRIMITIVES_INVALID_NODE, ParsingErrorType.EVENT_PRIMITIVES_INVALID_RUNTIME);

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PrimitiveEventData("MonPmCpu", "PM_CPU", 4500L));
		primitiveEventList.add(new PrimitiveEventData("MonPmRam", "PM_RAM", 4500L));

		List<SimpleEventData> simpleEventDataList = new ArrayList<>();
		simpleEventDataList.add(new SimpleEventData("SimpleEventName1", "SimpleEventType1", Arrays.asList(new Pair<>("SE_typeParam1", "SE_valueParam1"), new Pair<>("SE_typeParam2", "SE_valueParam2"))));
		simpleEventDataList.add(new SimpleEventData("SimpleEventName1", "SimpleEventType1", Arrays.asList(new Pair<>("SE_typeParam1", "SE_valueParam1"), new Pair<>("SE_typeParam2", "SE_valueParam2"), new Pair<>("SE_typeParam3", "SE_valueParam3"))));
		simpleEventDataList.add(new SimpleEventData("SimpleEventName1", "SimpleEventType1", Arrays.asList()));

		List<ComplexEventData> complexEventDataList = new ArrayList<>();
		complexEventDataList.add(new ComplexEventData("ComplexEventName1", "ComplexEventType1", Arrays.asList(new Pair<>("CE_typeParam1", "CE_valueParam1"), new Pair<>("CE_typeParam2", "CE_valueParam2"))));
		complexEventDataList.add(new ComplexEventData("ComplexEventName1", "ComplexEventType1", Arrays.asList(new Pair<>("CE_typeParam1", "CE_valueParam1"), new Pair<>("CE_typeParam2", "CE_valueParam2"), new Pair<>("CE_typeParam3", "CE_valueParam3"))));
		complexEventDataList.add(new ComplexEventData("ComplexEventName1", "ComplexEventType1", Arrays.asList()));

		ResultatParsing resultatParsing = ResultatParsing.FAB(fileErrorTypes, parsingErrorTypes, primitiveEventList, simpleEventDataList, complexEventDataList);
		assertEquals("Constructeur - fileErrorTypes", fileErrorTypes, resultatParsing.getFileErrorTypes());
		assertEquals("Constructeur - parsingErrorTypes", parsingErrorTypes, resultatParsing.getParsingErrorTypes());
		assertEquals("Constructeur - primitiveEventList", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Constructeur - simpleEventDataList", simpleEventDataList, resultatParsing.getSimpleEventList());
		assertEquals("Constructeur - complexEventDataList", complexEventDataList, resultatParsing.getComplexEventList());
		assertEquals("Constructeur - validationResult", null, resultatParsing.getValidationResult());

	}

	@Test
	public void testResultatParsing_WithoutParams() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		assertTrue("Constructeur - fileErrorTypes", resultatParsing.getFileErrorTypes().isEmpty());
		assertTrue("Constructeur - parsingErrorTypes", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Constructeur - primitiveEventMap", resultatParsing.getPrimitiveEventList().isEmpty());
		assertTrue("Constructeur - simpleEventDataList", resultatParsing.getSimpleEventList().isEmpty());
		assertTrue("Constructeur - complexEventDataList", resultatParsing.getComplexEventList().isEmpty());
		assertEquals("Constructeur - validationResult", null, resultatParsing.getValidationResult());
		
	}

	@Test
	public void testSets() {
		
		List<FileErrorType> fileErrorTypes = Arrays.asList(FileErrorType.FILE_NOT_FOUND, FileErrorType.FILE_NOT_FOUND);
		List<ParsingErrorType> parsingErrorTypes = Arrays.asList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_RUNTIME,
				ParsingErrorType.EVENT_PRIMITIVES_INVALID_NAME, ParsingErrorType.EVENT_PRIMITIVES_INVALID_NODE);

        List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PrimitiveEventData("MonPmCpu", "PM_CPU", 4500L));
		primitiveEventList.add(new PrimitiveEventData("MonPmRam", "PM_RAM", 4000L));

		List<SimpleEventData> simpleEventDataList = new ArrayList<>();
		simpleEventDataList.add(new SimpleEventData("SimpleEventName1", "SimpleEventType1", Arrays.asList(new Pair<>("SE_typeParam1", "SE_valueParam1"), new Pair<>("SE_typeParam2", "SE_valueParam2"))));
		simpleEventDataList.add(new SimpleEventData("SimpleEventName1", "SimpleEventType1", Arrays.asList(new Pair<>("SE_typeParam1", "SE_valueParam1"), new Pair<>("SE_typeParam2", "SE_valueParam2"), new Pair<>("SE_typeParam3", "SE_valueParam3"))));
		simpleEventDataList.add(new SimpleEventData("SimpleEventName1", "SimpleEventType1", Arrays.asList()));

		List<ComplexEventData> complexEventDataList = new ArrayList<>();
		complexEventDataList.add(new ComplexEventData("ComplexEventName1", "ComplexEventType1", Arrays.asList(new Pair<>("CE_typeParam1", "CE_valueParam1"), new Pair<>("CE_typeParam2", "CE_valueParam2"))));
		complexEventDataList.add(new ComplexEventData("ComplexEventName1", "ComplexEventType1", Arrays.asList(new Pair<>("CE_typeParam1", "CE_valueParam1"), new Pair<>("CE_typeParam2", "CE_valueParam2"), new Pair<>("CE_typeParam3", "CE_valueParam3"))));
		complexEventDataList.add(new ComplexEventData("ComplexEventName1", "ComplexEventType1", Arrays.asList()));

		ValidationResult validationResult = ValidationResult.FAB();
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		resultatParsing.setFileErrorTypes(fileErrorTypes);
		resultatParsing.setPrimitiveEventList(primitiveEventList);
		resultatParsing.setParsingErrorTypes(parsingErrorTypes);
		resultatParsing.setValidationResult(validationResult);
		resultatParsing.setSimpleEventList(simpleEventDataList);
		resultatParsing.setComplexEventList(complexEventDataList);

		assertEquals("Constructeur - fileErrorTypes", fileErrorTypes, resultatParsing.getFileErrorTypes());
		assertEquals("Constructeur - parsingErrorTypes", parsingErrorTypes, resultatParsing.getParsingErrorTypes());
		assertEquals("Constructeur - primitiveEventList", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Constructeur - simpleEventDataList", simpleEventDataList, resultatParsing.getSimpleEventList());
		assertEquals("Constructeur - complexEventDataList", complexEventDataList, resultatParsing.getComplexEventList());
		assertEquals("Constructeur - validationResult", ValidationResult.FAB(null,null), resultatParsing.getValidationResult());


	}
	
	@Test
	public void testAddFileErrorType() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		
		List<FileErrorType> expectedListFileErrorType = Arrays.asList(FileErrorType.FILE_NOT_FOUND, FileErrorType.FILE_NOT_FOUND);
		resultatParsing.addFileErrorType(FileErrorType.FILE_NOT_FOUND);
		resultatParsing.addFileErrorType(FileErrorType.FILE_NOT_FOUND);

		assertEquals("Ajout - fileErrorTypes", expectedListFileErrorType, resultatParsing.getFileErrorTypes());
		
	}

	@Test
	public void testAddParsingErrorType() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		
		List<ParsingErrorType> expectedListParsingErrorType = Arrays.asList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_NAME,
				ParsingErrorType.EVENT_PRIMITIVES_INVALID_NODE, ParsingErrorType.EVENT_PRIMITIVES_INVALID_RUNTIME);

		resultatParsing.addParsingErrorType(ParsingErrorType.EVENT_PRIMITIVES_INVALID_NAME);
		resultatParsing.addParsingErrorType(ParsingErrorType.EVENT_PRIMITIVES_INVALID_NODE);
		resultatParsing.addParsingErrorType(ParsingErrorType.EVENT_PRIMITIVES_INVALID_RUNTIME);
		
		assertEquals("Ajout - parsingErrorTypes", expectedListParsingErrorType, resultatParsing.getParsingErrorTypes());
		
	}
	
	@Test
	public void testAddPrimitiveEvent() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();

		List<PrimitiveEventData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PrimitiveEventData("MonEvent1", "Event1", 45000L));
		primitiveEventList.add(new PrimitiveEventData("MonEvent2", "Event2", 56000L));
		primitiveEventList.add(new PrimitiveEventData("MonEvent3", "Event3", 56000L));

		resultatParsing.addPrimitiveEvent(new PrimitiveEventData("MonEvent1", "Event1", 45000L));
		resultatParsing.addPrimitiveEvent(new PrimitiveEventData("MonEvent2", "Event2", 56000L));
		resultatParsing.addPrimitiveEvent(new PrimitiveEventData("MonEvent3", "Event3", 56000L));
		
		assertEquals("Ajout - listJeuDeDonnees", primitiveEventList, resultatParsing.getPrimitiveEventList());
		
	}
	
	@Test
	public void testHasErrors_NoErrors1() {
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		assertFalse(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_NoErrors2() {
		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		assertFalse(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_NoErrors3() {
		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		resultatParsing.setValidationResult(ValidationResult.FAB());
		assertFalse(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_OnlyParsingErrors() {
		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = Collections.singletonList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_NODE);
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		assertTrue(resultatParsing.hasErrors());
	}
	
	@Test
	public void testHasErrors_OnlyFileErrors() {
		List<FileErrorType> listeFileErrorTypes = Collections.singletonList(FileErrorType.EMPTY_FILE);
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		assertTrue(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_OnlyValidationResult() {

		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();

		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		resultatParsing.setValidationResult(ValidationResult.FAB(ValidationError.FAB(), FileErrorType.EMPTY_FILE));

		assertTrue(resultatParsing.hasErrors());

	}
	
	@Test
	public void testHasErrors_FileErrorsAndParsingErrors() {
		List<FileErrorType> listeFileErrorTypes = Arrays.asList(FileErrorType.EMPTY_FILE, FileErrorType.INVALID_FILE_FORMAT);
		List<ParsingErrorType> listeParsingErrorTypes = Arrays.asList(ParsingErrorType.EVENT_PRIMITIVES_INVALID_NODE, ParsingErrorType.EVENT_PRIMITIVES_INVALID_RUNTIME);
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		assertTrue(resultatParsing.hasErrors());
	}

	@Test
	public void testAddParsingErrorTypeWithComplementMessage() {

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		resultatParsing.addParsingErrorTypeWithComplementMessage(ParsingErrorType.EVENT_PRIMITIVES_DUPLICATED_NAME, "MonEvent1");
		resultatParsing.addParsingErrorTypeWithComplementMessage(ParsingErrorType.EVENT_PRIMITIVES_DUPLICATED_NAME, "MonEvent2");
		resultatParsing.addParsingErrorTypeWithComplementMessage(ParsingErrorType.EVENT_PRIMITIVES_DUPLICATED_NAME, "MonEvent3");

		assertEquals("ParsingErrorType complement content", ParsingErrorType.EVENT_PRIMITIVES_DUPLICATED_NAME.getComplements(), Arrays.asList("MonEvent1", "MonEvent2", "MonEvent3"));

        ParsingErrorType.EVENT_PRIMITIVES_DUPLICATED_NAME.resetComplements();

	}

	@Test
	public void testExistingPrimitiveEventListWithName() {

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		resultatParsing.addPrimitiveEvent(new PrimitiveEventData("UnPE1", "UnType", 45L));
        resultatParsing.addPrimitiveEvent(new PrimitiveEventData("UnPE2", "UnType", 45L));

        assertTrue(resultatParsing.existingPrimitiveEventListWithName("UnPE1"));
        assertFalse(resultatParsing.existingPrimitiveEventListWithName("UnPE3"));

	}

}
