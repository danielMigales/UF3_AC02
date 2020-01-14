package uf3_ac02;

/**
 * @Daniel Migales
 */
import java.io.IOException;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class CrearPersonas {

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {

        //Creamos la instancia para crear el documento xml
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Creamos un documento vacio con la version del xml
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "Personas", null); //Nombre raiz
        document.setXmlVersion("1.0");

        //Menu principal
        boolean salir = true;
        Scanner tecladoNumero = new Scanner(System.in);
        Scanner tecladoString = new Scanner(System.in);

        do {
            System.out.println("\n*****MENU PRINCIPAL*****\n");
            System.out.println("1.  Crear Persona.");
            System.out.println("2.  Leer Personas.");
            System.out.println("3.  Salir del programa.");

            System.out.println("\n" + "Elija una opcion.");
            int seleccion = tecladoNumero.nextInt();

            switch (seleccion) {

                //Dentro del primer caso se crea el elemento persona. 
                //Se solicita un valor al usuario a traves de teclado y se llama al metodo CrearPersona con ese valor.
                case 1:
                    //Creamos un elemento
                    Element raiz = document.createElement("persona"); //nodo raiz personas
                    document.getDocumentElement().appendChild(raiz);

                    //Añadir un nombre a la persona
                    System.out.println("Inserte el nombre de la persona:");
                    String valor = tecladoString.nextLine();

                    //Llamada al metodo crear persona
                    CrearElemento(valor, raiz, document);
                    break;
                case 2:
                    LeerPersonas.leerXML();
                    break;
                case 3:
                    salir = false;
                    break;
            }
        } while (salir);
    }

    public static void CrearElemento(String valor, Element raiz, Document document) throws TransformerConfigurationException, TransformerException {

        Element elem = document.createElement("Nombre");
        Text text = document.createTextNode(valor);//damos valor
        raiz.appendChild(elem);//pegamos el elemento hijo a la raiz
        elem.appendChild(text); //pegamos el valor

        //Creamos la fuente del documento xml (es para cerrar el documento tras crear un elemento)
        Source source = new DOMSource(document);
        Result result = new StreamResult(new java.io.File("Personas.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
    }

}//fin de la clase

