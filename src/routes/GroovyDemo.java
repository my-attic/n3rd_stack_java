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

        /* get All Humans */
        get(new Route("/groovy") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.GroovyDemo.about(request, response);
            }
        });
    }
}
