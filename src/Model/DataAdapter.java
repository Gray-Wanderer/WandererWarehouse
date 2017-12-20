package Model;

import Control.DataList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.Data;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static Control.DataList.*;

/**
 * Created by Алена on 04.12.2017.
 */
public class DataAdapter {
    public static void codeXML(File file){
        try {
            DataList data = new DataList();
            prepareDataToCode();
            JAXBContext c = JAXBContext.newInstance(Event.class, Item.class, ItemType.class, DataList.class, Person.class);
            Marshaller m = c.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(data, file);
        }catch (JAXBException e){
            e.printStackTrace();
        }
    }

    public static void decodeXML(File file){
        try {
            JAXBContext c = JAXBContext.newInstance(Item.class, ItemType.class, DataList.class, Person.class, Event.class);
            Unmarshaller um = c.createUnmarshaller();
            DataList data = (DataList) um.unmarshal(file);
            setDecodeData();
        }catch (JAXBException e){
            e.printStackTrace();
        }
    }
}
