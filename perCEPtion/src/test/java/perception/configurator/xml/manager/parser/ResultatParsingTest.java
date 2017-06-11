package perception.configurator.xml.manager.parser;

import org.junit.Test;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.model.PEData;
import perception.configurator.xml.manager.validator.ValidationError;
import perception.configurator.xml.manager.validator.ValidationResult;

import java.util.*;

import static org.junit.Assert.*;

public class ResultatParsingTest {

	@Test
	public void testResultatParsing_WithParams() {
		
		List<FileErrorType> fileErrorTypes = Arrays.asList(FileErrorType.FILE_NOT_FOUND, FileErrorType.FILE_NOT_FOUND);
		List<ParsingErrorType> parsingErrorTypes = Arrays.asList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME,
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE, ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME);

        List<PEData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PEData("MonPmCpu", "PM_CPU", 4500L));
		primitiveEventList.add(new PEData("MonPmRam", "PM_RAM", 4500L));

		ResultatParsing resultatParsing = ResultatParsing.FAB(fileErrorTypes, parsingErrorTypes, primitiveEventList);
		assertEquals("Constructeur - fileErrorTypes", fileErrorTypes, resultatParsing.getFileErrorTypes());
		assertEquals("Constructeur - primitiveEventMap", parsingErrorTypes, resultatParsing.getParsingErrorTypes());
		assertEquals("Constructeur - listJeuDeDonnees", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Constructeur - validationResult", null, resultatParsing.getValidationResult());
		
	}

	@Test
	public void testResultatParsing_WithoutParams() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		assertTrue("Constructeur - fileErrorTypes", resultatParsing.getFileErrorTypes().isEmpty());
		assertTrue("Constructeur - parsingErrorTypes", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Constructeur - primitiveEventMap", resultatParsing.getPrimitiveEventList().isEmpty());
		assertEquals("Constructeur - validationResult", null, resultatParsing.getValidationResult());
		
	}

	@Test
	public void testSets() {
		
		List<FileErrorType> fileErrorTypes = Arrays.asList(FileErrorType.FILE_NOT_FOUND, FileErrorType.FILE_NOT_FOUND);
		List<ParsingErrorType> parsingErrorTypes = Arrays.asList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME,
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME, ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE);

        List<PEData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PEData("MonPmCpu", "PM_CPU", 4500L));
		primitiveEventList.add(new PEData("MonPmRam", "PM_RAM", 4000L));

		ValidationResult validationResult = ValidationResult.FAB();
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		resultatParsing.setFileErrorTypes(fileErrorTypes);
		resultatParsing.setPrimitiveEventList(primitiveEventList);
		resultatParsing.setParsingErrorTypes(parsingErrorTypes);
		resultatParsing.setValidationResult(validationResult);
		
		assertEquals("Constructeur - fileErrorTypes", fileErrorTypes, resultatParsing.getFileErrorTypes());
		assertEquals("Constructeur - parsingErrorTypes", parsingErrorTypes, resultatParsing.getParsingErrorTypes());
		assertEquals("Constructeur - primitiveEventMap", primitiveEventList, resultatParsing.getPrimitiveEventList());
		assertEquals("Constructeur - validationResult", validationResult, resultatParsing.getValidationResult());
		
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
		
		List<ParsingErrorType> expectedListParsingErrorType = Arrays.asList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME,
				ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE, ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME);

		resultatParsing.addParsingErrorType(ParsingErrorType.PRIMITIVES_EVENT_INVALID_NAME);
		resultatParsing.addParsingErrorType(ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE);
		resultatParsing.addParsingErrorType(ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME);
		
		assertEquals("Ajout - parsingErrorTypes", expectedListParsingErrorType, resultatParsing.getParsingErrorTypes());
		
	}
	
	@Test
	public void testAddPrimitiveEvent() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();

		List<PEData> primitiveEventList = new ArrayList<>();
		primitiveEventList.add(new PEData("MonEvent1", "Event1", 45000L));
		primitiveEventList.add(new PEData("MonEvent2", "Event2", 56000L));
		primitiveEventList.add(new PEData("MonEvent3", "Event3", 56000L));

		resultatParsing.addPrimitiveEvent(new PEData("MonEvent1", "Event1", 45000L));
		resultatParsing.addPrimitiveEvent(new PEData("MonEvent2", "Event2", 56000L));
		resultatParsing.addPrimitiveEvent(new PEData("MonEvent3", "Event3", 56000L));
		
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
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>());
		assertFalse(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_NoErrors3() {
		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>());
		resultatParsing.setValidationResult(ValidationResult.FAB());
		assertFalse(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_OnlyParsingErrors() {
		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = Collections.singletonList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE);
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>());
		assertTrue(resultatParsing.hasErrors());
	}
	
	@Test
	public void testHasErrors_OnlyFileErrors() {
		List<FileErrorType> listeFileErrorTypes = Collections.singletonList(FileErrorType.EMPTY_FILE);
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>());
		assertTrue(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_OnlyValidationResult() {

		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();

		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>());
		resultatParsing.setValidationResult(ValidationResult.FAB(ValidationError.FAB(), FileErrorType.EMPTY_FILE));

		assertTrue(resultatParsing.hasErrors());

	}
	
	@Test
	public void testHasErrors_FileErrorsAndParsingErrors() {
		List<FileErrorType> listeFileErrorTypes = Arrays.asList(FileErrorType.EMPTY_FILE, FileErrorType.INVALID_FILE_FORMAT);
		List<ParsingErrorType> listeParsingErrorTypes = Arrays.asList(ParsingErrorType.PRIMITIVES_EVENT_INVALID_NODE, ParsingErrorType.PRIMITIVES_EVENT_INVALID_RUNTIME);
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new ArrayList<>());
		assertTrue(resultatParsing.hasErrors());
	}

	@Test
	public void testAddParsingErrorTypeWithComplementMessage() {

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		resultatParsing.addParsingErrorTypeWithComplementMessage(ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME, "MonEvent1");
		resultatParsing.addParsingErrorTypeWithComplementMessage(ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME, "MonEvent2");
		resultatParsing.addParsingErrorTypeWithComplementMessage(ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME, "MonEvent3");

		assertEquals("ParsingErrorType complement content", ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME.getComplements(), Arrays.asList("MonEvent1", "MonEvent2", "MonEvent3"));

        ParsingErrorType.PRIMITIVES_EVENT_DUPLICATED_NAME.resetComplements();

	}

	@Test
	public void testExistingPrimitiveEventListWithName() {

		ResultatParsing resultatParsing = ResultatParsing.FAB();
		resultatParsing.addPrimitiveEvent(new PEData("UnPE1", "UnType", 45L));
        resultatParsing.addPrimitiveEvent(new PEData("UnPE2", "UnType", 45L));

        assertTrue(resultatParsing.existingPrimitiveEventListWithName("UnPE1"));
        assertFalse(resultatParsing.existingPrimitiveEventListWithName("UnPE3"));

	}

}
