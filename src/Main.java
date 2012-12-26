/*
    N3rd.stack:[java]
 */

import static spark.Spark.*;

import org.ektorp.CouchDbConnector;

import org.k33g.helpers.*;
import repositories.HumanRepository;
import spark.*;

import groovy.lang.Binding;

import models.*;

import java.util.List;


public class Main {

    public static void main(String[] args)  {
        setPort(9000);

        N3rd.about();

        /*--- Groovy Support ---*/
        Groovy.setScriptsPath("gscripts");

        try {
            Groovy.iniScriptEngine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*----------------------*/

        /*---     CouchDB    ---*/
        CouchDbConnector db = CouchDB.getDb("humansdb","http://localhost:5984");
        final HumanRepository humanRepository = new HumanRepository(db);
        /*----------------------*/

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

        /* get one Human by id */
        get(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                Human model = humanRepository.get(id);
                response.type("application/json");
                return Json.toJson(model);
            }
        });

        /* get All Humans */
        get(new Route("/humans") {
            @Override
            public Object handle(Request request, Response response) {
                List<Human> humans = humanRepository.getAll();

                response.type("application/json");
                return Json.toJson(humans);
            }
        });

        /* create a Human */
        post(new Route("/humans") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                try {
                    Human model = Json.fromJson(Json.parse(request.body()), Human.class);

                    humanRepository.add(model);

                    return Json.toJson(model);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return Json.toJson(e);
                }
            }
        });

        /* update a human */
        put(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                try {
                    Human model = Json.fromJson(Json.parse(request.body()), Human.class);
                    //String id = request.params(":id");
                    humanRepository.update(model);

                    return Json.toJson(model);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    return Json.toJson(e);
                }
            }
        });

        /* delete a human */
        delete(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                Human model = humanRepository.get(id);
                humanRepository.remove(model);

                response.type("application/json");
                return Json.toJson(model);
            }
        });

        //SERVE STATIC FILES
        Assets.setPublicPath("public");
        Assets.setHome("index.html");
        Assets.serveStatic();

    }
}