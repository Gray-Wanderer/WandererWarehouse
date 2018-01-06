package data;

/**
 * @author Gray_Wanderer on 06.01.2018.
 */
public class DaoException extends Exception {


    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
