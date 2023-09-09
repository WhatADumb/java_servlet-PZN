package study.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns = "/form-upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path file = new File(getClass().getResource("/html/form-upload.html").getFile()).toPath();
        String html = Files.readString(file);

        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Part profile = req.getPart("profile");

        Path uploaded = Path.of("upload/" + profile.getSubmittedFileName());
        Files.copy(profile.getInputStream(), uploaded);

        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Upload</title>
                </head>
                <body>
                     <label>
                         Name: $name
                     </label>
                     <br>
                     <label>
                         Profile: <img src="/download?file=$file&download=null" width="400px" height="400px"/>
                     </label>
                </body>
                </html>
                """.replace("$name", name).replace("$file", uploaded.getFileName().toString());

//        resp.getWriter().println("Hello " + name + ", you're profile saved at " + uploaded.toFile());
        resp.getWriter().println(html);
    }
}
