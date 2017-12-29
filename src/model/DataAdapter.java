package model;

import control.DataList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static control.DataList.prepareDataToCode;
import static control.DataList.setDecodeData;

/**
 * Created by Алена on 04.12.2017.
 */
public class DataAdapter {
    public static void codeXML(File file) {
        try {
            DataList data = new DataList();
            prepareDataToCode();
            JAXBContext c = JAXBContext.newInstance(Event.class, Item.class, ItemType.class, DataList.class, Person.class);
            Marshaller m = c.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(data, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void decodeXML(File file) {
        try {
            JAXBContext c = JAXBContext.newInstance(Item.class, ItemType.class, DataList.class, Person.class, Event.class);
            Unmarshaller um = c.createUnmarshaller();
            DataList data = (DataList) um.unmarshal(file);
            setDecodeData();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
