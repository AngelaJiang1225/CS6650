package client;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

//import org.apache.httpcomponents.httpclient;

public class HttpClientTutorial {

    private static String url = "http://localhost:8080/lab2_war_exploded/hello";//12/seasonss/2019/day/1/skier/123
    // /12/seasonss/2019/day/1/skier/123
    // Create an instance of HttpClient.
    private static HttpClient client = new HttpClient();
    private static int NUMTHREADS = 100;

    private static void sendGet() {
        // Create a method instance.
        GetMethod method = new GetMethod(url);
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
            // Read the response body.
            byte[] responseBody = method.getResponseBody();
            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            System.out.println("hello xixi!");
            System.out.println(new String(responseBody));
            // responseBody is from servlet

        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
    }

    public static void main(String[] args) throws Exception {
        final RequestCounterBarrier counter = new RequestCounterBarrier();
        CountDownLatch completed = new CountDownLatch(NUMTHREADS);
        // Phase 1
        for (int i = 0; i < NUMTHREADS; i++) {
            System.out.println("start time is: " + System.currentTimeMillis());
            // lambda runnable creation - interface only has a single method so lambda works fine
            Runnable thread = () -> {
                sendGet();
                counter.inc();
                completed.countDown();
            };
            new Thread(thread).start();
            try {
                completed.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("start await");
            completed.await();
            System.out.println("end await");
            System.out.println("end time is: " + System.currentTimeMillis());
        }

        System.out.println("Value should be equal to " + NUMTHREADS + " It is: " + counter.getVal());
//        final RequestCounterBarrier counter = new RequestCounterBarrier();
//        CountDownLatch completed = new CountDownLatch(NUMTHREADS);
//        for (int i = 0; i < NUMTHREADS; i++) {
//            System.out.println("start time is: " + System.currentTimeMillis());
//            // lambda runnable creation - interface only has a single method so lambda works fine
//            Runnable thread = () -> {
//                counter.inc();
//                completed.countDown();
//                // Create a method instance.
//                GetMethod method = new GetMethod(url);
//                // Provide custom retry handler is necessary
//                method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//                        new DefaultHttpMethodRetryHandler(3, false));
//                try {
//                    // Execute the method.
//                    int statusCode = client.executeMethod(method);
//                    if (statusCode != HttpStatus.SC_OK) {
//                        System.err.println("Method failed: " + method.getStatusLine());
//                    }
//                    // Read the response body.
//                    byte[] responseBody = method.getResponseBody();
//                    // Deal with the response.
//                    // Use caution: ensure correct character encoding and is not binary data
//                    System.out.println("hello xixi!");
//                    System.out.println(new String(responseBody));
//
//                } catch (IOException e) {
//                    System.err.println("Fatal transport error: " + e.getMessage());
//                    e.printStackTrace();
//                } finally {
//                    // Release the connection.
//                    method.releaseConnection();
//                }
//
//            };
//            new Thread(thread).start();
//            try {
//                completed.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("end time is: " + System.currentTimeMillis());
//        }
        //System.out.println("Value should be equal to " + NUMTHREADS + " It is: " + counter.getVal());

    }
//    public static void sendGet() {
//        // Create a method instance.
//        GetMethod method = new GetMethod(url);
//        // Provide custom retry handler is necessary
//        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//                new DefaultHttpMethodRetryHandler(3, false));
//
//        try {
//            // Execute the method.
//            int statusCode = client.executeMethod(method);
//
//            if (statusCode != HttpStatus.SC_OK) {
//                System.err.println("Method failed: " + method.getStatusLine());
//            }
//            // Read the response body.
//            byte[] responseBody = method.getResponseBody();
//
//            // Deal with the response.
//            // Use caution: ensure correct character encoding and is not binary data
//            System.out.println("hello xixi!");
//            System.out.println(new String(responseBody));
//
//        } catch (IOException e) {
//            System.err.println("Fatal transport error: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // Release the connection.
//            method.releaseConnection();
//        }
//    }
}