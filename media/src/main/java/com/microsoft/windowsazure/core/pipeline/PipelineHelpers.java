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
package com.microsoft.windowsazure.core.pipeline;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public final class PipelineHelpers {
    private PipelineHelpers() {
    }

    private static String createErrorMessage(Response clientResponse) {
        clientResponse.bufferEntity();
        String errorMessage = clientResponse.toString();
        if (clientResponse.hasEntity()) {
            errorMessage = errorMessage + " "
                    + clientResponse.readEntity(String.class);
        }
        return errorMessage;
    }

    public static void throwIfNotSuccess(Response clientResponse) {
        int statusCode = clientResponse.getStatus();

        if ((statusCode < 200) || (statusCode >= 300)) {
            String errorMessage = createErrorMessage(clientResponse);
            throw new RuntimeException(errorMessage);
        }
    }

    public static void throwIfError(Response clientResponse) {
        if (clientResponse.getStatus() >= 400) {
            String errorMessage = createErrorMessage(clientResponse);
            throw new RuntimeException(errorMessage); 
        }
    }

    public static WebTarget addOptionalQueryParam(WebTarget webTarget,
            String key, Object value) {
        if (value != null) {
            webTarget = webTarget.queryParam(key, value.toString());
        }
        return webTarget;
    }

    public static WebTarget addOptionalQueryParam(WebTarget webTarget,
            String key, int value, int defaultValue) {
        if (value != defaultValue) {
            webTarget = webTarget.queryParam(key, Integer.toString(value));
        }
        return webTarget;
    }

}
