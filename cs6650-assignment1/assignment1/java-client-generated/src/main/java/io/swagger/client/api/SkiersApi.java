package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.swagger.client.model.LiftRide;
import io.swagger.client.model.ResponseMsg;
import io.swagger.client.model.SkierVertical;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkiersApi {
    private ApiClient apiClient;

    public SkiersApi() {
        this(Configuration.getDefaultApiClient());
    }

    public SkiersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getSkierDayVertical
     * @param resortID ID of the resort the skier is at (required)
     * @param dayID ID number of ski day in the ski season (required)
     * @param skierID ID of the skier riding the lift (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getSkierDayVerticalCall(String resortID, String dayID, String skierID, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/skiers/{resortID}/days/{dayID}/skiers/{skierID}"
            .replaceAll("\\{" + "resortID" + "\\}", apiClient.escapeString(resortID.toString()))
            .replaceAll("\\{" + "dayID" + "\\}", apiClient.escapeString(dayID.toString()))
            .replaceAll("\\{" + "skierID" + "\\}", apiClient.escapeString(skierID.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getSkierDayVerticalValidateBeforeCall(String resortID, String dayID, String skierID, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'resortID' is set
        if (resortID == null) {
            throw new ApiException("Missing the required parameter 'resortID' when calling getSkierDayVertical(Async)");
        }
        // verify the required parameter 'dayID' is set
        if (dayID == null) {
            throw new ApiException("Missing the required parameter 'dayID' when calling getSkierDayVertical(Async)");
        }
        // verify the required parameter 'skierID' is set
        if (skierID == null) {
            throw new ApiException("Missing the required parameter 'skierID' when calling getSkierDayVertical(Async)");
        }
        
        com.squareup.okhttp.Call call = getSkierDayVerticalCall(resortID, dayID, skierID, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * 
     * get the total vertical for the skier for the specified ski day
     * @param resortID ID of the resort the skier is at (required)
     * @param dayID ID number of ski day in the ski season (required)
     * @param skierID ID of the skier riding the lift (required)
     * @return SkierVertical
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<SkierVertical> getSkierDayVertical(String resortID, String dayID, String skierID) throws ApiException {
        ApiResponse<SkierVertical> resp = getSkierDayVerticalWithHttpInfo(resortID, dayID, skierID);
        return resp;
    }

    /**
     * 
     * get the total vertical for the skier for the specified ski day
     * @param resortID ID of the resort the skier is at (required)
     * @param dayID ID number of ski day in the ski season (required)
     * @param skierID ID of the skier riding the lift (required)
     * @return ApiResponse&lt;SkierVertical&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<SkierVertical> getSkierDayVerticalWithHttpInfo(String resortID, String dayID, String skierID) throws ApiException {
        com.squareup.okhttp.Call call = getSkierDayVerticalValidateBeforeCall(resortID, dayID, skierID, null, null);
        Type localVarReturnType = new TypeToken<SkierVertical>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * get the total vertical for the skier for the specified ski day
     * @param resortID ID of the resort the skier is at (required)
     * @param dayID ID number of ski day in the ski season (required)
     * @param skierID ID of the skier riding the lift (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getSkierDayVerticalAsync(String resortID, String dayID, String skierID, final ApiCallback<SkierVertical> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getSkierDayVerticalValidateBeforeCall(resortID, dayID, skierID, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<SkierVertical>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getSkierResortTotals
     * @param skierID ID the skier to retrieve data for (required)
     * @param resort resort to filter by (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getSkierResortTotalsCall(String skierID, List<String> resort, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/skiers/{skierID}/vertical"
            .replaceAll("\\{" + "skierID" + "\\}", apiClient.escapeString(skierID.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (resort != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "resort", resort));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getSkierResortTotalsValidateBeforeCall(String skierID, List<String> resort, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'skierID' is set
        if (skierID == null) {
            throw new ApiException("Missing the required parameter 'skierID' when calling getSkierResortTotals(Async)");
        }
        // verify the required parameter 'resort' is set
        if (resort == null) {
            throw new ApiException("Missing the required parameter 'resort' when calling getSkierResortTotals(Async)");
        }
        
        com.squareup.okhttp.Call call = getSkierResortTotalsCall(skierID, resort, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * get the total vertical for the skier for the specified resort
     * get the total vertical for the skier the specified resort.
     * @param skierID ID the skier to retrieve data for (required)
     * @param resort resort to filter by (required)
     * @return SkierVertical
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public SkierVertical getSkierResortTotals(String skierID, List<String> resort) throws ApiException {
        ApiResponse<SkierVertical> resp = getSkierResortTotalsWithHttpInfo(skierID, resort);
        return resp.getData();
    }

    /**
     * get the total vertical for the skier for the specified resort
     * get the total vertical for the skier the specified resort.
     * @param skierID ID the skier to retrieve data for (required)
     * @param resort resort to filter by (required)
     * @return ApiResponse&lt;SkierVertical&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<SkierVertical> getSkierResortTotalsWithHttpInfo(String skierID, List<String> resort) throws ApiException {
        com.squareup.okhttp.Call call = getSkierResortTotalsValidateBeforeCall(skierID, resort, null, null);
        Type localVarReturnType = new TypeToken<SkierVertical>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * get the total vertical for the skier for the specified resort (asynchronously)
     * get the total vertical for the skier the specified resort.
     * @param skierID ID the skier to retrieve data for (required)
     * @param resort resort to filter by (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getSkierResortTotalsAsync(String skierID, List<String> resort, final ApiCallback<SkierVertical> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getSkierResortTotalsValidateBeforeCall(skierID, resort, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<SkierVertical>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for writeNewLiftRide
     * @param body information for new lift ride event (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call writeNewLiftRideCall(LiftRide body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/skiers/liftrides";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call writeNewLiftRideValidateBeforeCall(LiftRide body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling writeNewLiftRide(Async)");
        }
        
        com.squareup.okhttp.Call call = writeNewLiftRideCall(body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * write a new lift ride for the skier
     * Stores new lift ride details in the data store
     * @param body information for new lift ride event (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> writeNewLiftRide(LiftRide body) throws ApiException {
        return writeNewLiftRideWithHttpInfo(body);
    }

    /**
     * write a new lift ride for the skier
     * Stores new lift ride details in the data store
     * @param body information for new lift ride event (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> writeNewLiftRideWithHttpInfo(LiftRide body) throws ApiException {
        com.squareup.okhttp.Call call = writeNewLiftRideValidateBeforeCall(body, null, null);
        return apiClient.execute(call);
    }

    /**
     * write a new lift ride for the skier (asynchronously)
     * Stores new lift ride details in the data store
     * @param body information for new lift ride event (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call writeNewLiftRideAsync(LiftRide body, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = writeNewLiftRideValidateBeforeCall(body, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
}
