package com.aiqi.servlets;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.client.model.SkierVertical;
import java.io.IOException;

@WebServlet(name = "SkiersServlet")
public class SkierServlet extends HttpServlet {

  private Gson gson = new Gson();

  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("application/json;charset:utf-8");
    String urlPath = request.getPathInfo();
    // check url
    if (!isPostUrlValid(urlPath)) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write(gson.toJson("Your request is invalid!"));
      return;
    }
    response.setStatus(HttpServletResponse.SC_CREATED);
  }

  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("application/json;charset:utf-8");
    String urlPath = request.getPathInfo();

    // check url
    if (!isUrlValid(urlPath, request)) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write(gson.toJson(new Message("Your request is invalid!")));
    } else {
      String resortId = request.getParameterMap().get("resort")[0];
      if(resortId == null || resortId.length() == 0)
        resortId = urlPath.split("/")[1];
      SkierVertical skierVertical = new SkierVertical();
      skierVertical.setResortID(resortId);
      skierVertical.setTotalVert(100000);
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write(gson.toJson(skierVertical));
    }
  }

  private boolean isPostUrlValid(String urlPath) {
    return urlPath != null && !urlPath.isEmpty() && urlPath.equals("/liftrides");
  }

  private boolean isUrlValid(String urlPath, HttpServletRequest request) throws IllegalArgumentException {
    // /{skierID}/vertical
    if (urlPath == null || urlPath.isEmpty() || urlPath.split("/").length != 6) return false;
    String[] url = urlPath.split("/");
    // Check whether it is /{skierID}/vertical
    if(url.length == 3 && url[2].equals("vertical") && numeric(url[1])) return true;
    // /{resortID}/days/{dayID}/skiers/{skierID}
    if(url.length == 6 && url[2].equals("days") && numeric(url[3]) && url[4].equals("skiers") && numeric(url[5]))
      return true;
    return false;
  }

  private static boolean numeric(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
        return false;
      }
    }
    return true;
  }
}
