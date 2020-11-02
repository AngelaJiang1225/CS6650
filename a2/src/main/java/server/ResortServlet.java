package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.SkierRecordsDao;
import entity.SkierRecords;

@WebServlet("/resort/day/top10vert")
public class ResortServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        //response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        String urlPath = request.getPathInfo();
        String[] urlParts = urlPath.split("/");

        if (urlPath == null || urlPath.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Invalid path");
        } else if (isValidPostURL(urlParts)) {
            //matches skiers/liftrides
            String body = request.getReader().lines().collect(Collectors.joining());
            SkierRecords ride = extractPostValidBody(body);
            boolean isSuccess =(new SkierRecordsDao()).createSkierRecord(ride);
            if (isSuccess) {
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\":\"is invalid\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String resort = request.getParameter("resort");
        String dayID  = request.getParameter("dayID");
        PrintWriter out = response.getWriter();
        if (resort == null || dayID == null ) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.write("\"message\":\"Invalid Parameters\"");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            out.write("get top 10 vertical");
        }
    }

    public boolean isValidPostURL(String[] urlPath) {
        // /resort/day/top10vert
        return urlPath.length == 3 &&
                urlPath[0].equals("resort") &&
                urlPath[1].equals("day") &&
                urlPath[2].equals("top10vert");

    }
    private SkierRecords extractPostValidBody(String jsonString){
        Gson gson = new Gson();
        SkierRecords ride = gson.fromJson(jsonString, SkierRecords.class);
        ride.setVertical();
        return ride;
    }


}
