package an.test.springws.common.model;

/**
 * @author Agustin Navcevich <agusnavce52@gmail.com>
 */
public class ResponseMessage {

    private String message;

    private String requestRef;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, String requestRef) {
        this.message = message;
        this.requestRef = requestRef;
    }

    public String getRequestRef() {
        return requestRef;
    }

    public void setRequestRef(String requestRef) {
        this.requestRef = requestRef;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "message='" + message + '\'' +
                ", requestRef='" + requestRef + '\'' +
                '}';
    }
}
