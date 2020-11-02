//package a1_server;
//
//import com.google.gson.Gson;
////import io.swagger.client.model.TopTen;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "ResortServlet")
//public class ResortServlet extends HttpServlet {
//
//  @Override
//  protected void doGet(HttpServletRequest request,
//                       HttpServletResponse response)
//      throws ServletException, IOException {
//    response.setContentType("application/json;charset:utf-8");
//    String urlPath = request.getPathInfo();
//
//    Gson gson = new Gson();
//
//    // check we have a URL!
//    if (!isUrlValid(urlPath)) {
//      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//      InvalidReturnMessage message = new InvalidReturnMessage();
//      message.setMessage("Invalid request.");
//      response.getWriter().write(gson.toJson(message));
//      return;
//    }
//
//    response.setStatus(HttpServletResponse.SC_OK);
//    TopTen topTen = new TopTen();
//    response.getWriter().write(gson.toJson(topTen));
//  }
//
//  private boolean isUrlValid(String urlPath) {
//    return urlPath != null && !urlPath.isEmpty() && urlPath.startsWith("/day/top10vert");
//  }
//}
