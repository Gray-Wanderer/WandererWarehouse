package data.xmlstorage;

import model.DataItem;
import model.Event;
import model.Item;
import model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public class TestXmlWarehouseStorage {

    private static List<DataItem> testData = null;

    @Test
    public void testCreateData() {
        XmlWarehouseStorage xmlWarehouseDao = getXmlWarehouseStorage();

        for (DataItem item : createTestData()) {
            Assert.assertEquals(item, xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new));
        }
    }

    @Test
    public void testDeleteData() {
        XmlWarehouseStorage xmlWarehouseDao = getXmlWarehouseStorage();

        for (DataItem item : createTestData()) {
            xmlWarehouseDao.deleteItem(item);
            Assert.assertFalse(xmlWarehouseDao.getItem(item.getId(), item.getClass()).isPresent());
        }
    }

    @Test
    public void testDeleteDataById() {
        XmlWarehouseStorage xmlWarehouseDao = getXmlWarehouseStorage();

        for (DataItem item : createTestData()) {
            xmlWarehouseDao.deleteItemById(item.getId(), item.getClass());
            Assert.assertFalse(xmlWarehouseDao.getItem(item.getId(), item.getClass()).isPresent());
        }
    }

    @Test
    public void testIsolationData() {
        XmlWarehouseStorage xmlWarehouseDao = getXmlWarehouseStorage();

        for (DataItem item : createTestData()) {
            DataItem newItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            modifyDate(newItem);

            DataItem oldItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            Assert.assertNotEquals(newItem, oldItem);
        }
    }

    @Test
    public void testUpdateData() {
        XmlWarehouseStorage xmlWarehouseDao = getXmlWarehouseStorage();

        for (DataItem item : createTestData()) {
            DataItem oldItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            DataItem newItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            modifyDate(newItem);
            xmlWarehouseDao.updateItem(newItem);
            Assert.assertNotEquals(oldItem, xmlWarehouseDao.getItem(newItem.getId(), newItem.getClass()).orElseThrow(AssertionError::new));
        }
    }

    @Test
    public void testStorageData() {
        XmlWarehouseStorage xmlWarehouseDao = null;
        try {
            xmlWarehouseDao = getXmlWarehouseStorage();
            xmlWarehouseDao.end();

            XmlWarehouseStorage newXmlWarehouseDao = new XmlWarehouseStorage();
            newXmlWarehouseDao.init(null);

            for (DataItem item : createTestData()) {
                Assert.assertEquals(item, newXmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new));
            }
        } finally {
            if (xmlWarehouseDao != null)
                xmlWarehouseDao.clearAllData();
        }
    }

    private void modifyDate(DataItem item) {
        if (item instanceof Event) {
            ((Event) item).setName(((Event) item).getName() + " new");
        } else if (item instanceof Item) {
            ((Item) item).setName(((Item) item).getName() + " new");
        } else if (item instanceof Person) {
            ((Person) item).setEventId(UUID.randomUUID());
        } else {
            throw new AssertionError(item.getClass().getSimpleName());
        }
    }

    private List<DataItem> createTestData() {
        if (testData != null)
            return testData;
        else {
            testData = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Event event = new Event();
                event.setName("Test event " + i);
                testData.add(event);
            }
            for (int i = 0; i < 10; i++) {
                Item item = new Item();
                item.setMaker("marker");
                item.setName("Item name " + i);
                item.setPersonId(UUID.randomUUID());
                testData.add(item);
            }
            for (int i = 0; i < 10; i++) {
                Person person = new Person();
                person.setName("Person " + i);
                person.setSurname("Test");
                person.setEventId(UUID.randomUUID());
                testData.add(person);
            }
            return testData;
        }
    }


    private XmlWarehouseStorage getXmlWarehouseStorage() {
        XmlWarehouseStorage xmlWarehouseDao = new XmlWarehouseStorage();
        xmlWarehouseDao.init(null);

        createTestData().forEach(xmlWarehouseDao::addItem);

        return xmlWarehouseDao;
    }

}
