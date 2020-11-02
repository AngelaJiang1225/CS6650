//import java.io.IOException;
//import java.net.URI;
//import java.net.http.*;
//import java.time.Duration;
//
//
//public class HttpClientSynchronous {
////    private static final HttpClient httpClient = HttpClient.newBuilder()
////            .version(HttpClient.Version.HTTP_2)
////            .followRedirects(HttpClient.Redirect.NORMAL)
////            .connectTimeout(Duration.ofSeconds(20))
////            .proxy(ProxySelector.of(new InetSocketAddress("proxy.yourcompany.com", 80)))
////            .authenticator(Authenticator.getDefault())
////            .build();
////
////    public static void main(String[] args) throws IOException, InterruptedException {
////
////        HttpRequest request = HttpRequest.newBuilder()
////                .GET()
////                .uri(URI.create("https://httpbin.org/get"))
////                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
////                .build();
////
////        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
////
////        // print response headers
////        HttpHeaders headers = response.headers();
////        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
////
////        // print status code
////        System.out.println(response.statusCode());
////
////        // print response body
////        System.out.println(response.body());
////
////    }
//
//}