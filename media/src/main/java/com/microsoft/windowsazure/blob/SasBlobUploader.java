package com.microsoft.windowsazure.blob;

import java.net.URI;
import java.net.URISyntaxException;

public class SasBlobUploader {
    
    private URI blobUri;
    
    public SasBlobUploader(String sasUrl) throws URISyntaxException {
        this(new URI(sasUrl));
    }

    public SasBlobUploader(URI uri) {
        setBlobUri(uri);
    }

    /**
     * @return the blobUri
     */
    public URI getBlobUri() {
        return blobUri;
    }

    /**
     * @param blobUri the blobUri to set
     */
    public void setBlobUri(URI blobUri) {
        this.blobUri = blobUri;
    }

}
