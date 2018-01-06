package data;

/**
 * @author Gray_Wanderer on 05.01.2018.
 */
public abstract class DataItem<T> implements Cloneable {

    public abstract T getId();

    @Override
    @SuppressWarnings("unchecked")
    public DataItem<T> clone() throws CloneNotSupportedException {
        return (DataItem<T>) super.clone();
    }
}
