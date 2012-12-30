package routes;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

/*TODO :
  add Animals.routes(); to routes.Router Class
*/

public class Animals {
    public static void routes() {

        /* get one Animal by id */
        get(new Route("/animals/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Animals.fetch(request, response);
            }
        });

        /* get All Animals */
        get(new Route("/animals") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Animals.getAll(request, response);
            }
        });

        /* create a Animal */
        post(new Route("/animals") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Animals.add(request, response);
            }
        });

        /* update a Animal */
        put(new Route("/animals/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Animals.update(request, response);
            }
        });

        /* delete a Animal */
        delete(new Route("/animals/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Animals.delete(request, response);
            }
        });
    }

}
