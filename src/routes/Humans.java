package routes;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Humans {
    public static void routes() {

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

        /* update a Human */
        put(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Humans.update(request, response);
            }
        });

        /* delete a Human */
        delete(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Humans.delete(request, response);
            }
        });
    }

}
