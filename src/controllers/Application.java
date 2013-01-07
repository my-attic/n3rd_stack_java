package controllers;

import spark.Request;
import spark.Response;

public class Application {
    public static String about(Request request, Response response) {
        response.type("text/html");
        return "Application N3rd.stack:[java] (c) 2012-2013 by @k33g_org";
    }

    public static String aboutJSON(Request request, Response response) {
        response.type("application/json");
        return "{name:'N3rd.stack:[java]',version:'beta 1.00', author:'@k33g_org'}";
    }


}
