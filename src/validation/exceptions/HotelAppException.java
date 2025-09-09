package validation.exceptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class HotelAppException extends RuntimeException{
    private final Map<String, Object> metadata;

    protected HotelAppException(String message, Throwable cause) {
        super(message, cause);

        this.metadata = new HashMap<>();
    }

    protected HotelAppException(String message) {
        this(message, null);
    }

    public Map<String, Object> getMetadata() {
        return Collections.unmodifiableMap(this.metadata);
    }

    protected void addMetaData(String key, Object value) {
        metadata.put(key, value);
    }
}
