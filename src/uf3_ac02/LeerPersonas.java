package uf3_ac02;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeerPersonas {

    public static void leerXML() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Personas.xml"));
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raiz: %s %n", document.getDocumentElement().getNodeName());

            //Crea una lista con todos los nodos personas
            NodeList personas = document.getElementsByTagName("persona");
            System.out.printf("Nodos persona a recorrer: %d %n", personas.getLength());

            //recorrer la lista
            for (int i = 0; i < personas.getLength(); i++) {
                Node persona = personas.item(i);//obtener un nodo empleado
                if (persona.getNodeType() == Node.ELEMENT_NODE) {//tipo de nodo
                    //obtener los elementos del nodo
                    Element elemento = (Element) persona;
                    System.out.printf("Nombre = %s %n", elemento.getElementsByTagName("Nombre").item(0).getTextContent());
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }
}


