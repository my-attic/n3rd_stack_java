package routes;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;


public class Application {
    public static void routes() {
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
    }
}
