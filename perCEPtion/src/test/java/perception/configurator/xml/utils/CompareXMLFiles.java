package perception.configurator.xml.utils;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Classe utilitaire destiné au test unitaire permettant la comparaison du
 * contenu de fichiers XML.
 *
 * @author Chloé GUILBAUD, Léo PARIS, Kendall FOREST, Mathieu GUYOT
 *
 */
public class CompareXMLFiles extends XMLTestCase {

	/**
	 * Permet de comparer deux fichiers XML à partir des chemins vers les deux
	 * fichiers à partir de leur chemin. Note : la similitude des fichiers est
	 * valid"e directement dans la méthode avec des assert.
	 * 
	 * @param filePath1
	 *            - chemin vers le premier fichier
	 * @param filePath2
	 *            - chemin vers le deuxième fichier
	 * @throws ParserConfigurationException
	 *             - erreur lié au parsing du fichier
	 * @throws SAXException
	 * @throws IOException
	 *             - erreur lié au traitement du fichier
	 * @throws TransformerException
	 *             - erreur lié à la transformation du document XML en fichier
	 *             XML
	 */
	public void compare(String filePath1, String filePath2)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {

		// Création doc xml à partir du chemin vers le fichier
		DocumentBuilderFactory factoryDoc = DocumentBuilderFactory.newInstance();
		factoryDoc.setNamespaceAware(true);
		DocumentBuilder builder = factoryDoc.newDocumentBuilder();
		Document doc1 = builder.parse(filePath1);
		Document doc2 = builder.parse(filePath2);

		// Transformation du fichier en chaîne de caractère
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		StringWriter writer1 = new StringWriter();
		transformer.transform(new DOMSource(doc1), new StreamResult(writer1));

		StringWriter writer2 = new StringWriter();
		transformer.transform(new DOMSource(doc2), new StreamResult(writer2));

		String output1 = writer1.getBuffer().toString().replaceAll("\n|\r", "");
		String output2 = writer2.getBuffer().toString().replaceAll("\n|\r", "");

		// System.out.println(output1);
		// System.out.println(output2);

		// Comparaison en ignorant les espaces blancs
		XMLUnit.setIgnoreWhitespace(true);
		assertXMLEqual(output1, output2);
		Diff diff = new Diff(output1, output2);
		assertTrue(diff.similar());

	}

}
