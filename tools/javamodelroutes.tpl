package routes;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

/*TODO :
  add Animals.routes(); to routes.Router Class
*/

public class {{model_name}}s {
    public static void routes() {

        /* get one {{model_name}} by id */
        get(new Route("/{{_model_name}}s/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.{{model_name}}s.fetch(request, response);
            }
        });

        /* get All {{model_name}}s */
        get(new Route("/{{_model_name}}s") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.{{model_name}}s.getAll(request, response);
            }
        });

        /* create a {{model_name}} */
        post(new Route("/{{_model_name}}s") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.{{model_name}}s.add(request, response);
            }
        });

        /* update a {{model_name}} */
        put(new Route("/{{_model_name}}s/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.{{model_name}}s.update(request, response);
            }
        });

        /* delete a {{model_name}} */
        delete(new Route("/{{_model_name}}s/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.{{model_name}}s.delete(request, response);
            }
        });
    }

}
