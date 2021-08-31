package de.iwa;

import com.google.gson.Gson;
import de.iwa.model.Idee;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import spark.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@Component
public class App {

    @Autowired
    private Service service;

    public void initialize() {
        Spark.get("infos", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hallo Welt";
            }
        });
        Spark.post("create", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String bezeichnung = request.queryParams("bezeichnung");
                String beschreibung = request.queryParams("beschreibung");
                service.speichern(bezeichnung, beschreibung);
                response.redirect("index.html");
                return null;
            }
        });
        Spark.get("search", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String query = request.queryParams("query");
                response.type("application/json");
                return new Gson().toJson(service.suchen(query));
            }
        });
        service.speichern("Origami Schwan","eine schöne Beschreibung zum Origami Schwan");
    }

    public static void main(String[] args) {
        Spark.staticFileLocation("html");
        Spark.staticFileLocation("html/css/*");
        Spark.init();
        Spark.awaitInitialization();
        AnnotationConfigApplicationContext ctxt = new AnnotationConfigApplicationContext(AppConfig.class);
        ctxt.getBean(App.class).initialize();
    }
}



