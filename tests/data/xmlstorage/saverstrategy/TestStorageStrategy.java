package data.xmlstorage.saverstrategy;

import model.DataItem;
import model.Event;
import model.Item;
import model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Gray-Wanderer on 06.01.2018.
 */
public class TestStorageStrategy {

    @Test
    public void testFileByClassStorageStrategy() {
        StorageStrategy storageStrategy = new FileByClassStorageStrategy();
        testStorageStrategy(storageStrategy);
    }

    @Test
    public void testSingleFileStorageStrategy() {
        StorageStrategy storageStrategy = new SingleFileStorageStrategy();
        testStorageStrategy(storageStrategy);
    }


    private void testStorageStrategy(StorageStrategy storageStrategy) {
        Map<Class<? extends DataItem>, Map<Object, DataItem>> testData = getTestData();

        try {
            storageStrategy.save(testData);
            Map<Class<? extends DataItem>, Map<Object, DataItem>> loadedData = storageStrategy.load();

            checkEqualsData(testData, loadedData);
        } finally {
            storageStrategy.clearAllData();
        }
    }

    private Map<Class<? extends DataItem>, Map<Object, DataItem>> getTestData() {
        Map<Class<? extends DataItem>, Map<Object, DataItem>> testData = new HashMap<>();
        testData.put(Item.class, new HashMap<>());
        testData.put(Person.class, new HashMap<>());

        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setName("Test event " + i);
            putDataItemToMap(testData, event);
        }

        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setMaker("marker");
            item.setName("Item name " + i);
            item.setPersonId(UUID.randomUUID());
            putDataItemToMap(testData, item);
        }

        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setName("Person " + i);
            person.setSurname("Test");
            person.setEventId(UUID.randomUUID());
            putDataItemToMap(testData, person);
        }

        return testData;
    }

    private void putDataItemToMap(Map<Class<? extends DataItem>, Map<Object, DataItem>> testData, DataItem dataItem) {
        testData.computeIfAbsent(dataItem.getClass(), k -> new HashMap<>())
                .put(dataItem.getId(), dataItem);
    }

    private void checkEqualsData(Map<Class<? extends DataItem>, Map<Object, DataItem>> data1,
                                 Map<Class<? extends DataItem>, Map<Object, DataItem>> data2) {
        if (data1.size() != data2.size())
            throw new AssertionError("Size of data is not equals");

        for (Class<? extends DataItem> clazz : data2.keySet()) {
            Map<Object, DataItem> dataForClass1 = data1.get(clazz);
            Map<Object, DataItem> dataForClass2 = data2.get(clazz);
            checkEqualsMaps(dataForClass1, dataForClass2);
        }
    }

    private void checkEqualsMaps(Map data1, Map data2) {
        if (data1.size() != data2.size())
            throw new AssertionError("Size of data is not equals");

        for (Object key : data1.keySet()) {
            Assert.assertEquals(data1.get(key), data2.get(key));
        }
    }

}
