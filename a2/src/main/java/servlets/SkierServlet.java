package servlets;

import com.google.gson.Gson;
import dao.SkierRecordsDao;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SkierDayVertical;

import entity.SkierRecords;

@WebServlet("/skiers/*")
public class SkierServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
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
            //bad path
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\":\"invalid path\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String urlPath = request.getPathInfo();
        String[] urlParts = urlPath.split("/");
        if (urlPath == null || urlPath.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("missing path variables");
        } else if (isValidTotalVerticalURLString(urlParts)) {
            //matches /skiers/{resortID}/days/{dayID}/skiers/{skierID}
            String resortID = request.getParameter("resort");
//            String resortID = urlParts[1];
                    //String resortID = request.getParameter("resort");
            String dayID = urlParts[3];
            String skierID = urlParts[5];
            if (resortID == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"message\":\"invalid parameters, must supply resort\"}");
            } else {
                SkierDayVertical result = (new SkierRecordsDao()).getSkierDayVertical(resortID, dayID, skierID);
                response.setStatus(HttpServletResponse.SC_OK);
                if (result != null) {
                    sendSuccessfulResponse(response, result);
                } else {
                    sendNoContentResponse(response);
                }
            }
        } else if (isValidDayVerticalURLString(urlParts)){
            //matches /skiers/{resortID}/days/{dayID}/skiers/{skierID}
            String resortID = urlParts[1];
            String dayID = urlParts[3];
            String skierID = urlParts[5];
            SkierDayVertical result = (new SkierRecordsDao()).getSkierDayVertical(resortID, dayID, skierID);
            if (result != null) {
                sendSuccessfulResponse(response, result);
            } else {
                sendNoContentResponse(response);
            }
        } else {
            //bad path
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\":\"invalid path\"}");
        }
    }

    private void sendNoContentResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        response.getWriter().write("{\"message\":\"Data not found\"}");
    }

    private void sendSuccessfulResponse(HttpServletResponse response, SkierDayVertical result) throws IOException {
        Gson gson = new Gson();
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(gson.toJson(result));
    }

    private boolean isValidTotalVerticalURLString(String[] urlPath) {
        return urlPath.length == 3 &&  urlPath[2].equals("vertical");
    }

    private boolean isValidDayVerticalURLString(String[] urlPath) {
        if (urlPath.length == 6) {
            boolean containsDays = urlPath[2].equals("days");
            boolean containsSkiers = urlPath[4].equals("skiers");
            return containsDays && containsSkiers;
        } else {
            return false;
        }
    }

    private SkierRecords extractPostValidBody(String jsonString){
        Gson gson = new Gson();
        SkierRecords ride = gson.fromJson(jsonString, SkierRecords.class);
        ride.setVertical();
        return ride;
    }

    private boolean isValidPostURL(String[] urlPath) {
        return urlPath.length == 2 && urlPath[1].equals("liftrides");
    }

}
