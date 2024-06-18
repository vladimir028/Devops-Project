package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.service.AuthorService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "//author")
public class АuthorServlet extends HttpServlet {

    private final AuthorService authorService;
    private final SpringTemplateEngine springTemplateEngine;

    public АuthorServlet(AuthorService authorService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context =  new WebContext(webExchange);
//        context.setVariable("authors", authorService.listAuthors());
//
//        springTemplateEngine.process(
//                "authorList.html",
//                context,
//                resp.getWriter()
//        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);
        List<Author> authors = authorService.listAuthors();
        context.setVariable("authors", authorService.listAuthors());
        String isbn = req.getParameter("bookIsbn");
        context.setVariable("isbn", isbn);
        springTemplateEngine.process(
                "authorList.html",
                context,
                resp.getWriter()
        );
    }
}
