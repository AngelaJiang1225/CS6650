import client.HttpClientTutorial;
import client.RequestCounterBarrier;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.HttpClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;

// /hello means http://localhost:8080/lab2_war_exploded/hello
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private String msg;
    @Override
    public void init() throws ServletException {
        // Initialization
        msg = "Hello World";
    }
    // handle a GET request
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type to text
        response.setContentType("text/html");
        // sleep for 1000ms. You can vary this value for different tests
        try {
            Thread.sleep(1000);
//            HttpClientTutorial.main();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Send the response
        PrintWriter out = response.getWriter();
        out.println("<h1>" + msg + "</h1>");
        System.out.println("nonononon");
    }
    @Override
    public void destroy() {
        // nothing to do here

    }
}