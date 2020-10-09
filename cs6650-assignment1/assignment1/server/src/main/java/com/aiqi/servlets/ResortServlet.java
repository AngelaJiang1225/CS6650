package com.aiqi.servlets;

import com.google.gson.Gson;
import com.aiqi.entity.SkierVertical;
import com.aiqi.entity.TopTenSkiers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ResortServlet")
public class ResortServlet extends HttpServlet {

  private Gson gson = new Gson();

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/json");
    String urlPath = request.getPathInfo();

    // check URL
    if (!isUrlValid(urlPath, request)) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      System.out.println("Your request is invalid!");
      response.getWriter().write(gson.toJson("Your request is invalid!"));
    } else {
      response.setStatus(HttpServletResponse.SC_OK);
      List<SkierVertical> skierVerticalList = new ArrayList<>();
      skierVerticalList.add(new SkierVertical(888899, 30400));
      TopTenSkiers topTen = new TopTenSkiers(skierVerticalList);
      response.getWriter().write(gson.toJson(topTen));
    }
  }

  private boolean isUrlValid(String urlPath, HttpServletRequest request) throws IllegalArgumentException {
    if(urlPath == null || urlPath.isEmpty() || urlPath.split("/").length != 3) return false;
    return urlPath.startsWith("/day/top10vert") && request.getParameter("resort") != null && request.getParameter("dayID") != null;
    }
  }

