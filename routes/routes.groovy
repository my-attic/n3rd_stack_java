import org.k33g.helpers.Json
import spark.Request
import spark.Response
import spark.Route

import static spark.Spark.get

/*=== routes for Application controller ===*/


get(new Route("/hello") {
    def handle(Request request, Response response) {
        response.type("application/json");
        Json.toJson("Hello")
    }
})

get(new Route("/about") {
    def handle(Request request, Response response) {
        controllers.Application.about(request, response)
    }
})

get(new Route("/about.json") {
    def handle(Request request, Response response) {
        controllers.Application.aboutJSON(request, response)
    }
})

get(new Route("/sessions") {
    def handle(Request request, Response response) {
        response.type("application/json");
        Json.toJson(request.session().id())
    }
})

get(new Route("/cookie") {
    def handle(Request request, Response response) {
        response.type("application/json");
        Json.toJson(request.headers("cookie"))
    }
})

//http://stackoverflow.com/questions/1798431/how-to-remove-a-cookie-in-apache
//http://curl.haxx.se/rfc/cookie_spec.html

get(new Route("/setcookie") {
    def handle(Request request, Response response) {
        response.type("application/json")
        response.header("Set-Cookie","N3RD="+request.session().id())
        Json.toJson(request.headers("cookie"))
    }
});

get(new Route("/delcookie") {
    def handle(Request request, Response response) {
        response.type("application/json")
        response.header("Set-Cookie","N3RD=;expires=Wednesday, 09-Nov-99 23:12:40 GMT; Max-Age=0; path=/")
        Json.toJson(request.headers("cookie"))
    }
});

/*pour tests*/
get(
    new Route("/phil") {
        def handle(Request request, Response response) {
            controllers.Application.about(request, response)
        }
    }
)

get(new Route("/groovy"){
    def handle(Request request, Response response) {
        controllers.GroovyDemo.about(request, response);
    }
})

