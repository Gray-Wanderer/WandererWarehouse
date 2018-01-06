package data.xmldao.saverstrategy;

import data.DataItem;
import data.xmldao.XmlWarehouseDaoException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Gray_Wanderer on 06.01.2018.
 */
public abstract class AbstractStorageStrategy implements StorageStrategy {

    protected static final String DATA_DIRECTORY = "data";
    protected static final String TMP_FILE_PREFIX = "_";

    protected void saveDataClass(Wrapper wrapper, String tmpFileName, String fileName, Class... classesForJAXB) {
        createDataDirectoryIfNotExists();

        File tmpFile = new File(tmpFileName);

        if (wrapper.getItems().isEmpty())
            return;

        boolean fileCreated;
        try {
            fileCreated = tmpFile.createNewFile();
        } catch (IOException e) {
            fileCreated = false;
        }

        if (!fileCreated)
            throw new XmlWarehouseDaoException("Can't create file '" + tmpFileName + "'");

        try {
            JAXBContext c = JAXBContext.newInstance(classesForJAXB);
            Marshaller m = c.createMarshaller();

            m.marshal(wrapper, tmpFile);
        } catch (JAXBException e) {
            throw new XmlWarehouseDaoException(tmpFileName + " parsing error", e);
        }

        clearDataFile(fileName);
        renameFile(tmpFileName, fileName);
    }

    protected void clearDataFile(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            throw new XmlWarehouseDaoException("Can't delete file '" + fileName + "'", e);
        }
    }

    private void createDataDirectoryIfNotExists() {
        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            if (!dataDirectory.mkdir())
                throw new XmlWarehouseDaoException("Can't create directory '" + DATA_DIRECTORY + "'");
        }
    }

    private void renameFile(String oldFileName, String newFileName) {
        File newFile = new File(newFileName);

        if (newFile.exists()) {
            throw new XmlWarehouseDaoException("Can't save new database file '" + oldFileName + "'");
        }

        File oldFile = new File(oldFileName);

        boolean renamed = oldFile.renameTo(newFile);

        if (!renamed) {
            throw new XmlWarehouseDaoException("Can't save new database file '" + oldFileName + "'");
        }
    }

    @XmlRootElement(name = "items")
    protected static class Wrapper {

        private Collection<DataItem> items;

        public Wrapper() {
            items = new ArrayList<>();
        }

        @SuppressWarnings("unused")
        public Wrapper(Collection<DataItem> items) {
            this.items = items;
        }

        @XmlElement(name = "item")
        public Collection<DataItem> getItems() {
            return items;
        }
    }

}
