package pl.pwr.swd.beerapp.android_app.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Adrian
 * Date: 25/05/14
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class NotEnoughElementsCheckedException extends Exception {
    public NotEnoughElementsCheckedException(String detailMessage) {
        super(detailMessage);
    }
}
