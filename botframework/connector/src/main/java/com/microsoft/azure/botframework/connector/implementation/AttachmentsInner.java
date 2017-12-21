/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.botframework.connector.implementation;

import retrofit2.Retrofit;
import com.google.common.reflect.TypeToken;
import com.microsoft.azure.botframework.connector.ErrorResponseException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Attachments.
 */
public class AttachmentsInner {
    /** The Retrofit service to perform REST calls. */
    private AttachmentsService service;
    /** The service client containing this operation class. */
    private BotConnectorImpl client;

    /**
     * Initializes an instance of AttachmentsInner.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public AttachmentsInner(Retrofit retrofit, BotConnectorImpl client) {
        this.service = retrofit.create(AttachmentsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for Attachments to be
     * used by Retrofit to perform actually REST calls.
     */
    interface AttachmentsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.botframework.connector.Attachments getAttachmentInfo" })
        @GET("v3/attachments/{attachmentId}")
        Observable<Response<ResponseBody>> getAttachmentInfo(@Path("attachmentId") String attachmentId, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.microsoft.azure.botframework.connector.Attachments getAttachment" })
        @GET("v3/attachments/{attachmentId}/views/{viewId}")
        Observable<Response<ResponseBody>> getAttachment(@Path("attachmentId") String attachmentId, @Path("viewId") String viewId, @Header("accept-language") String acceptLanguage, @Header("User-Agent") String userAgent);

    }

    /**
     * GetAttachmentInfo.
     * Gets information about the specified attachment, including file name, type, and the available views (e.g., original or thumbnail).
     *
     * @param attachmentId Attachment ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the AttachmentInfoInner object if successful.
     */
    public AttachmentInfoInner getAttachmentInfo(String attachmentId) {
        return getAttachmentInfoWithServiceResponseAsync(attachmentId).toBlocking().single().body();
    }

    /**
     * GetAttachmentInfo.
     * Gets information about the specified attachment, including file name, type, and the available views (e.g., original or thumbnail).
     *
     * @param attachmentId Attachment ID.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<AttachmentInfoInner> getAttachmentInfoAsync(String attachmentId, final ServiceCallback<AttachmentInfoInner> serviceCallback) {
        return ServiceFuture.fromResponse(getAttachmentInfoWithServiceResponseAsync(attachmentId), serviceCallback);
    }

    /**
     * GetAttachmentInfo.
     * Gets information about the specified attachment, including file name, type, and the available views (e.g., original or thumbnail).
     *
     * @param attachmentId Attachment ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the AttachmentInfoInner object
     */
    public Observable<AttachmentInfoInner> getAttachmentInfoAsync(String attachmentId) {
        return getAttachmentInfoWithServiceResponseAsync(attachmentId).map(new Func1<ServiceResponse<AttachmentInfoInner>, AttachmentInfoInner>() {
            @Override
            public AttachmentInfoInner call(ServiceResponse<AttachmentInfoInner> response) {
                return response.body();
            }
        });
    }

    /**
     * GetAttachmentInfo.
     * Gets information about the specified attachment, including file name, type, and the available views (e.g., original or thumbnail).
     *
     * @param attachmentId Attachment ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the AttachmentInfoInner object
     */
    public Observable<ServiceResponse<AttachmentInfoInner>> getAttachmentInfoWithServiceResponseAsync(String attachmentId) {
        if (attachmentId == null) {
            throw new IllegalArgumentException("Parameter attachmentId is required and cannot be null.");
        }
        return service.getAttachmentInfo(attachmentId, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<AttachmentInfoInner>>>() {
                @Override
                public Observable<ServiceResponse<AttachmentInfoInner>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<AttachmentInfoInner> clientResponse = getAttachmentInfoDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<AttachmentInfoInner> getAttachmentInfoDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<AttachmentInfoInner, ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<AttachmentInfoInner>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

    /**
     * GetAttachment.
     * Gets the specified view of the specified attachment as binary content.
     *
     * @param attachmentId Attachment ID.
     * @param viewId View ID from attachmentInfo
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the byte[] object if successful.
     */
    public byte[] getAttachment(String attachmentId, String viewId) {
        return getAttachmentWithServiceResponseAsync(attachmentId, viewId).toBlocking().single().body();
    }

    /**
     * GetAttachment.
     * Gets the specified view of the specified attachment as binary content.
     *
     * @param attachmentId Attachment ID.
     * @param viewId View ID from attachmentInfo
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<byte[]> getAttachmentAsync(String attachmentId, String viewId, final ServiceCallback<byte[]> serviceCallback) {
        return ServiceFuture.fromResponse(getAttachmentWithServiceResponseAsync(attachmentId, viewId), serviceCallback);
    }

    /**
     * GetAttachment.
     * Gets the specified view of the specified attachment as binary content.
     *
     * @param attachmentId Attachment ID.
     * @param viewId View ID from attachmentInfo
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the byte[] object
     */
    public Observable<byte[]> getAttachmentAsync(String attachmentId, String viewId) {
        return getAttachmentWithServiceResponseAsync(attachmentId, viewId).map(new Func1<ServiceResponse<byte[]>, byte[]>() {
            @Override
            public byte[] call(ServiceResponse<byte[]> response) {
                return response.body();
            }
        });
    }

    /**
     * GetAttachment.
     * Gets the specified view of the specified attachment as binary content.
     *
     * @param attachmentId Attachment ID.
     * @param viewId View ID from attachmentInfo
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the byte[] object
     */
    public Observable<ServiceResponse<byte[]>> getAttachmentWithServiceResponseAsync(String attachmentId, String viewId) {
        if (attachmentId == null) {
            throw new IllegalArgumentException("Parameter attachmentId is required and cannot be null.");
        }
        if (viewId == null) {
            throw new IllegalArgumentException("Parameter viewId is required and cannot be null.");
        }
        return service.getAttachment(attachmentId, viewId, this.client.acceptLanguage(), this.client.userAgent())
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<byte[]>>>() {
                @Override
                public Observable<ServiceResponse<byte[]>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<byte[]> clientResponse = getAttachmentDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<byte[]> getAttachmentDelegate(Response<ResponseBody> response) throws ErrorResponseException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<byte[], ErrorResponseException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<byte[]>() { }.getType())
                .register(301, new TypeToken<Void>() { }.getType())
                .register(302, new TypeToken<Void>() { }.getType())
                .registerError(ErrorResponseException.class)
                .build(response);
    }

}
