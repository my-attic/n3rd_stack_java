/*
    N3rd.stack:[java]
 */

import static spark.Spark.*;

import spark.*;

import org.k33g.helpers.N3rd;
import org.k33g.helpers.Groovy;
import groovy.lang.Binding;


import org.k33g.helpers.Assets;
import org.k33g.helpers.Json;

import models.*;

public class Main {

    public static void main(String[] args)  {
        setPort(9000);

        N3rd.about();

        Groovy.setScriptsPath("gscripts");

        try {
            Groovy.iniScriptEngine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        get(new Route("/groovy/us") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/html");
                try {

                    Binding binding = new Binding();
                    binding.setVariable("input", "WORLD");
                    Groovy.run("hello.groovy", binding);

                    return binding.getVariable("output").toString();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        });

        get(new Route("/groovy/fr") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/html");
                try {

                    Binding binding = new Binding();
                    binding.setVariable("input","Tout Le Monde");
                    Groovy.run("salut.groovy",binding);

                    return binding.getVariable("output").toString();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        });

        get(new Route("/about") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return "<b>N3rd.stack:[java]</b>";
            }
        });
        get(new Route("/about.json") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                return "{name:'N3rd.stack:[java]',version:'before alpha'}";
            }
        });

        get(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                Human model = new Human("John", "Doe");
                model.id = id;
                response.type("application/json");
                return Json.toJson(model);
            }
        });

        post(new Route("/humans") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                try {

                    Human model = Json.fromJson(Json.parse(request.body()), Human.class);
                    model.id = java.util.UUID.randomUUID().toString();
                    return Json.toJson(model);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return Json.toJson(e);
                }
            }
        });

        //SERVE STATIC FILES
        Assets.setPublicPath("public");
        Assets.setHome("index.html");
        Assets.serveStatic();

    }
}