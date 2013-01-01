package controllers;

import models.*;
import org.codehaus.jackson.JsonNode;
import org.k33g.helpers.Json;
import spark.Request;
import spark.Response;

import java.util.List;

public class Tasks {
    public static JsonNode getAll(Request request, Response response) {
        List<Task> tasks = repositories.Tasks.repository.getAll();
        response.type("application/json");
        return Json.toJson(tasks);
    }

    public static JsonNode fetch(Request request, Response response) {
        String id = request.params(":id");
        Task model = repositories.Tasks.repository.get(id);
        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode delete(Request request, Response response) {
        String id = request.params(":id");
        Task model = repositories.Tasks.repository.get(id);
        repositories.Tasks.repository.remove(model);
        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode add(Request request, Response response) {
        response.type("application/json");
        try {
            Task model = Json.fromJson(Json.parse(request.body()), Task.class);

            repositories.Tasks.repository.add(model);

            return Json.toJson(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode update(Request request, Response response) {
        response.type("application/json");
        try {
            Task model = Json.fromJson(Json.parse(request.body()), Task.class);
            //String id = request.params(":id");
            repositories.Tasks.repository.update(model);

            return Json.toJson(model);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

}

