
/*
    N3rd.stack:[java]
 */

import static spark.Spark.*;
import spark.*;
import org.k33g.helpers.*;


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

        get(new Route("/groovy/us") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.GroovyDemo.helloUS(request, response);
            }
        });

        get(new Route("/groovy/fr") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.GroovyDemo.helloFR(request, response);
            }
        });

        /* get All Humans */
        get(new Route("/groovy/humans") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.GroovyDemo.allHumans(request, response);
            }
        });

        get(new Route("/about") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Application.about(request, response);
            }
        });

        get(new Route("/about.json") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Application.aboutJSON(request, response);
            }
        });

        /* get one Human by id */
        get(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Humans.fetch(request, response);
            }
        });

        /* get All Humans */
        get(new Route("/humans") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Humans.getAll(request, response);
            }
        });

        /* create a Human */
        post(new Route("/humans") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Humans.add(request, response);
            }
        });

        /* update a human */
        put(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Humans.update(request, response);
            }
        });

        /* delete a human */
        delete(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Humans.delete(request, response);
            }
        });

        //SERVE STATIC FILES
        Assets.setPublicPath("public.n3rd");
        //Assets.setPublicPath("public.simple");
        Assets.setHome("index.html");
        Assets.serveStatic();

    }
}