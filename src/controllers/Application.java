package controllers;

import spark.Request;
import spark.Response;

public class Application {
    public static String about(Request request, Response response) {
        response.type("text/html");
        return "<b>--- N3rd.stack:[java] ---</b>";
    }

    public static String aboutJSON(Request request, Response response) {
        response.type("application/json");
        return "{name:'N3rd.stack:[java]',version:'beta'}";
    }
}
