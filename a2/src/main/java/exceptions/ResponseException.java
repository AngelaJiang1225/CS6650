package exceptions;
import javax.servlet.http.HttpServletResponse;

public class ResponseException extends Exception  {
    /**
     * constructor passed
     * @param message denotes that the exception is thrown.
     */
    public ResponseException(String message) {
        super(message);
    }

    public int getResponseCode() {
        return HttpServletResponse.SC_BAD_GATEWAY;
    }
}
