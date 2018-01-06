package data.xmldao;

import data.DaoException;
import data.DataItem;
import model.Event;
import model.Item;
import model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gray_Wanderer on 06.01.2018.
 */
public class TestXmlWarehouseDao {

    @Test
    public void testCreateData() {
        XmlWarehouseDaoForTest xmlWarehouseDao = getXmlWarehouseDaoForTest();

        for (DataItem item : createTestData()) {
            Assert.assertEquals(item, xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new));
        }
    }

    @Test
    public void testDeleteData() {
        XmlWarehouseDaoForTest xmlWarehouseDao = getXmlWarehouseDaoForTest();

        for (DataItem item : createTestData()) {
            xmlWarehouseDao.deleteItem(item);
            Assert.assertFalse(xmlWarehouseDao.getItem(item.getId(), item.getClass()).isPresent());
        }
    }

    @Test
    public void testDeleteDataById() {
        XmlWarehouseDaoForTest xmlWarehouseDao = getXmlWarehouseDaoForTest();

        for (DataItem item : createTestData()) {
            xmlWarehouseDao.deleteItem(item.getId(), item.getClass());
            Assert.assertFalse(xmlWarehouseDao.getItem(item.getId(), item.getClass()).isPresent());
        }
    }

    @Test
    public void testIsolationData() {
        XmlWarehouseDaoForTest xmlWarehouseDao = getXmlWarehouseDaoForTest();

        for (DataItem item : createTestData()) {
            DataItem newItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            modifyDate(newItem);

            DataItem oldItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            Assert.assertNotEquals(newItem, oldItem);
        }
    }

    @Test
    public void testUpdateData() {
        XmlWarehouseDaoForTest xmlWarehouseDao = getXmlWarehouseDaoForTest();

        for (DataItem item : createTestData()) {
            DataItem oldItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            DataItem newItem = xmlWarehouseDao.getItem(item.getId(), item.getClass()).orElseThrow(AssertionError::new);
            modifyDate(newItem);
            xmlWarehouseDao.updateItemTest(item.getId(), newItem);
            Assert.assertNotEquals(oldItem, xmlWarehouseDao.getItem(newItem.getId(), newItem.getClass()).orElseThrow(AssertionError::new));
        }
    }

    private void modifyDate(DataItem item) {
        if (item instanceof Event) {
            ((Event) item).setName(((Event) item).getName() + " new");
        } else if (item instanceof Item) {
            ((Item) item).setName(((Item) item).getName() + " new");
        } else if (item instanceof Person) {
            ((Person) item).setName(((Person) item).getName() + " new");
        } else {
            throw new AssertionError(item.getClass().getSimpleName());
        }
    }

    private List<DataItem> createTestData() {
        List<DataItem> testData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setName("Test event " + i);
            testData.add(event);
        }
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setMaker("marker");
            item.setName("Item name " + i);
            item.setPersonId("Test Person " + i);
            testData.add(item);
        }
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setName("Person " + i);
            person.setSurname("Test");
            person.setEventId("Test event " + i);
            testData.add(person);
        }
        return testData;
    }


    private XmlWarehouseDaoForTest getXmlWarehouseDaoForTest() {
        XmlWarehouseDaoForTest xmlWarehouseDao = new XmlWarehouseDaoForTest();
        xmlWarehouseDao.init(null);

        createTestData().forEach(xmlWarehouseDao::addItemTest);

        return xmlWarehouseDao;
    }

    private static class XmlWarehouseDaoForTest extends XmlWarehouseDao {
        public void addItemTest(DataItem dataItem) {
            try {
                super.addItem(dataItem);
            } catch (DaoException e) {
                throw new AssertionError(e);
            }
        }

        public void updateItemTest(Object oldId, DataItem dataItem) {
            try {
                super.updateItem(oldId, dataItem);
            } catch (DaoException e) {
                throw new AssertionError(e);
            }
        }

    }

}
