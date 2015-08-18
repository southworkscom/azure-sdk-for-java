package com.microsoft.windowsazure.services.media;

import org.glassfish.jersey.client.ClientResponse;

public class UniformInterfaceException extends RuntimeException {
    /**
     * serial
     */
    private static final long serialVersionUID = -3142049092636270755L;

    private ClientResponse response;
    
    public UniformInterfaceException(String format, ClientResponse response) {
        super(format);
        this.setResponse(response);
    }

    public UniformInterfaceException(ClientResponse clientResponse) {
        this.setResponse(clientResponse);
    }

    /**
     * @return the response
     */
    public ClientResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(ClientResponse response) {
        this.response = response;
    }

}
