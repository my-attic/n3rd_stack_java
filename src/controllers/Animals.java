package controllers;

import models.*;
import org.codehaus.jackson.JsonNode;
import org.k33g.helpers.Json;
import spark.Request;
import spark.Response;

import java.util.List;

public class Animals {
    public static JsonNode getAll(Request request, Response response) {
        List<Animal> animals = repositories.Animals.repository.getAll();
        response.type("application/json");
        return Json.toJson(animals);
    }

    public static JsonNode fetch(Request request, Response response) {
        String id = request.params(":id");
        Animal model = repositories.Animals.repository.get(id);
        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode delete(Request request, Response response) {
        String id = request.params(":id");
        Animal model = repositories.Animals.repository.get(id);
        repositories.Animals.repository.remove(model);
        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode add(Request request, Response response) {
        response.type("application/json");
        try {
            Animal model = Json.fromJson(Json.parse(request.body()), Animal.class);

            repositories.Animals.repository.add(model);

            return Json.toJson(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode update(Request request, Response response) {
        response.type("application/json");
        try {
            Animal model = Json.fromJson(Json.parse(request.body()), Animal.class);
            //String id = request.params(":id");
            repositories.Animals.repository.update(model);

            return Json.toJson(model);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

}

