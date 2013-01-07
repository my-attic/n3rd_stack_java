package routes;

import org.k33g.helpers.Json;
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


        get(new Route("/sessions") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                return Json.toJson(request.session().id());
            }
        });

        get(new Route("/cookie") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                return Json.toJson(request.headers("cookie"));
            }
        });

        //http://stackoverflow.com/questions/1798431/how-to-remove-a-cookie-in-apache
        //http://curl.haxx.se/rfc/cookie_spec.html

        get(new Route("/setcookie") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                response.header("Set-Cookie","N3RD="+request.session().id());
                return Json.toJson(request.headers("cookie"));
            }
        });

        get(new Route("/delcookie") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                response.header("Set-Cookie","N3RD=;expires=Wednesday, 09-Nov-99 23:12:40 GMT; Max-Age=0; path=/");
                return Json.toJson(request.headers("cookie"));
            }
        });

    }
}
