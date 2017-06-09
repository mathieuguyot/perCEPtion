package perception.configurator.xml.manager.parser;

import org.testng.annotations.Test;
import perception.configurator.xml.enums.general.FileErrorType;
import perception.configurator.xml.enums.parser.ParsingErrorType;
import perception.configurator.xml.manager.validator.ValidationError;
import perception.configurator.xml.manager.validator.ValidationResult;
import scopt.Validation;

import java.util.*;

import static org.junit.Assert.*;

public class ResultatParsingTest {

	@Test
	public void testResultatParsing_WithParams() {
		
		List<FileErrorType> fileErrorTypes = Arrays.asList(FileErrorType.FILE_NOT_FOUND, FileErrorType.FILE_NOT_FOUND);
		List<ParsingErrorType> parsingErrorTypes = Arrays.asList(ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR,
				ParsingErrorType.INVALID_PRIMITIVES_NODE, ParsingErrorType.INVALID_PRIMITIVE_RUNTIME);

		HashMap<String, Long> primitiveEventMap = new HashMap<>();
		primitiveEventMap.put("PM_CPU", 4500L);
        primitiveEventMap.put("PM_RAM", 4000L);
		
		ResultatParsing resultatParsing = ResultatParsing.FAB(fileErrorTypes, parsingErrorTypes, primitiveEventMap);
		assertEquals("Constructeur - fileErrorTypes", fileErrorTypes, resultatParsing.getFileErrorTypes());
		assertEquals("Constructeur - primitiveEventMap", parsingErrorTypes, resultatParsing.getParsingErrorTypes());
		assertEquals("Constructeur - listJeuDeDonnees", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
		assertEquals("Constructeur - validationResult", null, resultatParsing.getValidationResult());
		
	}

	@Test
	public void testResultatParsing_WithoutParams() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		assertTrue("Constructeur - fileErrorTypes", resultatParsing.getFileErrorTypes().isEmpty());
		assertTrue("Constructeur - parsingErrorTypes", resultatParsing.getParsingErrorTypes().isEmpty());
		assertTrue("Constructeur - primitiveEventMap", resultatParsing.getPrimitiveEventMap().isEmpty());
		assertEquals("Constructeur - validationResult", null, resultatParsing.getValidationResult());
		
	}

	@Test
	public void testSets() {
		
		List<FileErrorType> fileErrorTypes = Arrays.asList(FileErrorType.FILE_NOT_FOUND, FileErrorType.FILE_NOT_FOUND);
		List<ParsingErrorType> parsingErrorTypes = Arrays.asList(ParsingErrorType.INVALID_PRIMITIVE_RUNTIME,
				ParsingErrorType.INVALID_PRIMITIVE_NAME, ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR);

        Map<String, Long> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("PM_CPU", 4500L);
        primitiveEventMap.put("PM_RAM", 4000L);

		ValidationResult validationResult = ValidationResult.FAB();
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();
		resultatParsing.setFileErrorTypes(fileErrorTypes);
		resultatParsing.setPrimitiveEventMap(primitiveEventMap);
		resultatParsing.setParsingErrorTypes(parsingErrorTypes);
		resultatParsing.setValidationResult(validationResult);
		
		assertEquals("Constructeur - fileErrorTypes", fileErrorTypes, resultatParsing.getFileErrorTypes());
		assertEquals("Constructeur - parsingErrorTypes", parsingErrorTypes, resultatParsing.getParsingErrorTypes());
		assertEquals("Constructeur - primitiveEventMap", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
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
		
		List<ParsingErrorType> expectedListParsingErrorType = Arrays.asList(ParsingErrorType.INVALID_PRIMITIVE_NAME,
				ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR, ParsingErrorType.INVALID_PRIMITIVE_RUNTIME);

		resultatParsing.addParsingErrorType(ParsingErrorType.INVALID_PRIMITIVE_NAME);
		resultatParsing.addParsingErrorType(ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR);
		resultatParsing.addParsingErrorType(ParsingErrorType.INVALID_PRIMITIVE_RUNTIME);
		
		assertEquals("Ajout - parsingErrorTypes", expectedListParsingErrorType, resultatParsing.getParsingErrorTypes());
		
	}
	
	@Test
	public void testAddPrimitiveEvent() {
		
		ResultatParsing resultatParsing = ResultatParsing.FAB();

        Map<String, Long> primitiveEventMap = new HashMap<>();
        primitiveEventMap.put("Event1", 45000L);
        primitiveEventMap.put("Event2", 56000L);
        primitiveEventMap.put("Event3", 56000L);

		resultatParsing.addPrimitiveEvent("Event1", 45000L);
		resultatParsing.addPrimitiveEvent("Event2", 56000L);
		resultatParsing.addPrimitiveEvent("Event3", 56000L);
		
		assertEquals("Ajout - listJeuDeDonnees", primitiveEventMap, resultatParsing.getPrimitiveEventMap());
		
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
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new HashMap<>());
		assertFalse(resultatParsing.hasErrors());
	}

	@Test
	public void testHasErrors_NoErrors3() {
		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new HashMap<>());
		resultatParsing.setValidationResult(ValidationResult.FAB());
		assertFalse(resultatParsing.hasErrors());
	}

	@Test
	public void testhasErrors_OnlyParsingErrors() {
		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = Collections.singletonList(ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR);
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new HashMap<>());
		assertTrue(resultatParsing.hasErrors());
	}
	
	@Test
	public void testhasErrors_OnlyFileErrors() {
		List<FileErrorType> listeFileErrorTypes = Collections.singletonList(FileErrorType.EMPTY_FILE);
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new HashMap<>());
		assertTrue(resultatParsing.hasErrors());
	}

	@Test
	public void testhasErrors_OnlyValidationResult() {

		List<FileErrorType> listeFileErrorTypes = new ArrayList<>();
		List<ParsingErrorType> listeParsingErrorTypes = new ArrayList<>();

		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new HashMap<>());
		resultatParsing.setValidationResult(ValidationResult.FAB(ValidationError.FAB(), FileErrorType.EMPTY_FILE));

		assertTrue(resultatParsing.hasErrors());

	}
	
	@Test
	public void testhasErrors_FileErrorsAndParsingErrors() {
		List<FileErrorType> listeFileErrorTypes = Arrays.asList(FileErrorType.EMPTY_FILE, FileErrorType.INVALID_FILE_FORMAT);
		List<ParsingErrorType> listeParsingErrorTypes = Arrays.asList(ParsingErrorType.INVALID_PRIMITIVE_ENABLED_ATTR, ParsingErrorType.INVALID_PRIMITIVE_RUNTIME);
		ResultatParsing resultatParsing = ResultatParsing.FAB(listeFileErrorTypes, listeParsingErrorTypes, new HashMap<>());
		assertTrue(resultatParsing.hasErrors());
	}


}
