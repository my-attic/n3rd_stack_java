package routes;

import org.k33g.helpers.Groovy;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

public class GroovyDemo {
    public static void routes() {
        //EXPERIMENTAL

        /*--- Groovy Support ---*/
        Groovy.setScriptsPath("");
        try {
            Groovy.iniScriptEngine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*----------------------*/

        get(new Route("/us") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.GroovyDemo.helloUS(request, response);
            }
        });

        get(new Route("/fr") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.GroovyDemo.helloFR(request, response);
            }
        });

        /* get All Humans */
        get(new Route("/groovy_humans") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.GroovyDemo.allHumans(request, response);
            }
        });
    }
}
