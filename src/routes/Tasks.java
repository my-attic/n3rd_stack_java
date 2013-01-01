package routes;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Tasks {
    public static void routes() {

        /* get one Task by id */
        get(new Route("/tasks/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Tasks.fetch(request, response);
            }
        });

        /* get All Tasks */
        get(new Route("/tasks") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Tasks.getAll(request, response);
            }
        });

        /* create a Task */
        post(new Route("/tasks") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Tasks.add(request, response);
            }
        });

        /* update a Task */
        put(new Route("/tasks/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Tasks.update(request, response);
            }
        });

        /* delete a Task */
        delete(new Route("/tasks/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return controllers.Tasks.delete(request, response);
            }
        });
    }

}
