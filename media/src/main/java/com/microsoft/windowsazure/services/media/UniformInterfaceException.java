package com.microsoft.windowsazure.services.media;

import javax.ws.rs.core.Response;

public class UniformInterfaceException extends RuntimeException {
    /**
     * serial
     */
    private static final long serialVersionUID = -3142049092636270755L;

    private Response response;
    
    public UniformInterfaceException(String format, Response response) {
        super(format);
        this.setResponse(response);
    }

    public UniformInterfaceException(Response clientResponse) {
        this.setResponse(clientResponse);
    }

    /**
     * @return the response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(Response response) {
        this.response = response;
    }
}
