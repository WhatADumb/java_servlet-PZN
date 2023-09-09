package study.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import study.servlet.model.SayHello;
import study.servlet.util.JsonUtil;

import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/api/say-hello")
public class ApiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SayHello hello = JsonUtil.getObjectMapper().readValue(req.getReader(), SayHello.class);
        String name = hello.getFirstName() + " " + hello.getLastName();

        Map<String, String> map = Map.of("data", name);

        String json = JsonUtil.getObjectMapper().writeValueAsString(map);

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(json);
    }
}
