package cn.bridge;

import okhttp3.MediaType;

/**
 * @author timpkins
 */
public class RequestOption {
    public static final String MEDIA_FORM = "multipart/form-data";
    public static final String MEDIA_JSON = "application/json";

    private MediaType mediaType;

    public RequestOption() {
        mediaType = MediaType.get(MEDIA_JSON);
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = MediaType.get(mediaType);
    }
}
