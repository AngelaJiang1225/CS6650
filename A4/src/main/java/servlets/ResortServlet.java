package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/resort/day/top10vert")
public class ResortServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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
            out.write("getting top10vert");
        }
    }


}
