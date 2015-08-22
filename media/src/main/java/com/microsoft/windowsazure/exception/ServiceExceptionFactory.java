/**
 * Copyright Microsoft Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.windowsazure.exception;

import java.net.SocketTimeoutException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.microsoft.windowsazure.core.ServiceTimeoutException;

public abstract class ServiceExceptionFactory {
    public static ServiceException process(String serviceName,
            ServiceException exception) {
        Throwable cause = exception.getCause();

        for (Throwable scan = cause; scan != null; scan = scan.getCause()) {
            Class<?> scanClass = scan.getClass();
            if (ServiceException.class.isAssignableFrom(scanClass)) {
                return populate(exception, serviceName, (ServiceException) scan);
            } else if (WebApplicationException.class
                    .isAssignableFrom(scanClass)) {
                return populate(exception, serviceName,
                        (WebApplicationException) scan);
            } else if (SocketTimeoutException.class.isAssignableFrom(scanClass)) {
                return populate(exception, serviceName,
                        (SocketTimeoutException) scan);
            }
        }

        exception.setServiceName(serviceName);

        return exception;
    }

    static ServiceException populate(ServiceException exception,
            String serviceName, WebApplicationException cause) {
        exception.setServiceName(serviceName);

        if (cause != null) {
            Response response = cause.getResponse();
            if (response != null) {
                // Set status
                StatusType status = response.getStatusInfo();
                if (status == null) {
                    status = Response.Status.fromStatusCode(response.getStatus());
                }
                if (status == null) {
                    exception.setHttpStatusCode(response.getStatus());
                } else {
                    exception.setHttpStatusCode(status.getStatusCode());
                    exception.setHttpReasonPhrase(status.getReasonPhrase());
                }

                // Set raw response body
                if (response.hasEntity()) {
                    try {
                        String body = response.readEntity(String.class);
                        exception.setRawResponseBody(body);
                    } catch (Exception e) {
                        // Skip exceptions as getting the response body as a
                        // string is a best effort thing
                    }
                }
            }
        }
        return exception;
    }

    static ServiceException populate(final ServiceException exception,
            final String serviceName, final ServiceException cause) {
        exception.setServiceName(cause.getServiceName());
        exception.setHttpStatusCode(cause.getHttpStatusCode());
        exception.setHttpReasonPhrase(cause.getHttpReasonPhrase());
        exception.setErrorCode(cause.getErrorCode());
        exception.setErrorMessage(cause.getErrorMessage());
        exception.setRawResponseBody(cause.getRawResponseBody());
        exception.setErrorValues(cause.getErrorValues());
        return exception;
    }

    static ServiceException populate(ServiceException exception,
            String serviceName, SocketTimeoutException cause) {
        ServiceTimeoutException newException = new ServiceTimeoutException(
                cause.getMessage(), cause);
        newException.setServiceName(serviceName);
        return newException;
    }
}
