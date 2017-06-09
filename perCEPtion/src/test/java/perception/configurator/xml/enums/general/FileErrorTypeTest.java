package perception.configurator.xml.enums.general;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileErrorTypeTest {

	@Test
	public void testGetLabel() {
		assertEquals("getLabel - FILE_NOT_FOUND", 
				"Fichier introuvable.", 
				FileErrorType.FILE_NOT_FOUND.getLabel());
	}
	
	@Test
	public void testFromLabel() {
		assertEquals("fromLabel - FILE_NOT_FOUND",
				FileErrorType.FILE_NOT_FOUND,
				FileErrorType.fromLabel("Fichier introuvable."));
	}
	
	@Test
	public void testFromLabelIgnoreCase() {
		FileErrorType res = FileErrorType.fromLabel("Fichier IntroUvable.");
		assertEquals("FromLibelle IgnoreCase NAMESPACE ", FileErrorType.FILE_NOT_FOUND, res);
	}
	
	@Test
	public void testFromLabelInvalide() {
		FileErrorType res = FileErrorType.fromLabel("N'ImporTquOi");
		assertEquals("FromLibelle Invalide", null, res);
	}

	@Test
	public void testValuesAsList() {
		List<FileErrorType> fileErrorTypeList = Arrays.asList(
				FileErrorType.FILE_NOT_FOUND, FileErrorType.EMPTY_FILE, FileErrorType.FILE_READING,
				FileErrorType.INVALID_FILE_FORMAT, FileErrorType.SCHEMA_ERROR);
		assertEquals("valuesAsList - taille", 5, FileErrorType.valuesAsList().size());
		assertTrue("valuesAsList - values", FileErrorType.valuesAsList().containsAll(fileErrorTypeList));
	}

}
