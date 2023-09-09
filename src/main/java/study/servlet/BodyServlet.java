package study.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns = "/body")
public class BodyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Path path = Path.of(BodyServlet.class.getResource("/html/body.html").getFile()); Buggy Issue

        //Both below code works fine
//        Path path = new File(getClass().getResource("/html/body.html").getFile()).toPath();
        Path path = new File(BodyServlet.class.getResource("/html/body.html").getPath()).toPath();
        String html = Files.readString(path);
        resp.getWriter().println(html);
    }
}
