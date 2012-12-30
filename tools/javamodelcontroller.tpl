package controllers;

import models.*;
import org.codehaus.jackson.JsonNode;
import org.k33g.helpers.Json;
import spark.Request;
import spark.Response;

import java.util.List;

public class {{model_name}}s {
    public static JsonNode getAll(Request request, Response response) {
        List<{{model_name}}> {{_model_name}}s = repositories.{{model_name}}s.repository.getAll();
        response.type("application/json");
        return Json.toJson({{_model_name}}s);
    }

    public static JsonNode fetch(Request request, Response response) {
        String id = request.params(":id");
        {{model_name}} model = repositories.{{model_name}}s.repository.get(id);
        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode delete(Request request, Response response) {
        String id = request.params(":id");
        {{model_name}} model = repositories.{{model_name}}s.repository.get(id);
        repositories.{{model_name}}s.repository.remove(model);
        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode add(Request request, Response response) {
        response.type("application/json");
        try {
            {{model_name}} model = Json.fromJson(Json.parse(request.body()), {{model_name}}.class);

            repositories.{{model_name}}s.repository.add(model);

            return Json.toJson(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode update(Request request, Response response) {
        response.type("application/json");
        try {
            {{model_name}} model = Json.fromJson(Json.parse(request.body()), {{model_name}}.class);
            //String id = request.params(":id");
            repositories.{{model_name}}s.repository.update(model);

            return Json.toJson(model);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

}

