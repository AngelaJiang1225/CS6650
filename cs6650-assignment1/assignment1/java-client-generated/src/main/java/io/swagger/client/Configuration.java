package io.swagger.client;

import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-30T04:22:21.099Z[GMT]")public class Configuration {
    private static ApiClient defaultApiClient;

    static {
        try {
            defaultApiClient = new ApiClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @return Default API client
     */
    public static ApiClient getDefaultApiClient() {
        return defaultApiClient;
    }

    /**
     * Set the default API client, which would be used when creating API
     * instances without providing an API client.
     *
     * @param apiClient API client
     */
    public static void setDefaultApiClient(ApiClient apiClient) {
        defaultApiClient = apiClient;
    }
}
