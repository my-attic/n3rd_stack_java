package controllers;

import models.Human;
import org.codehaus.jackson.JsonNode;
import org.k33g.helpers.Json;
import repositories.Repositories;
import spark.Request;
import spark.Response;

import java.util.List;





public class Humans {
    public static JsonNode getAll(Request request, Response response) {
        List<Human> humans = Repositories.humanRepository.getAll();

        response.type("application/json");
        return Json.toJson(humans);
    }

    public static JsonNode fetch(Request request, Response response) {
        String id = request.params(":id");
        Human model = Repositories.humanRepository.get(id);
        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode delete(Request request, Response response) {
        String id = request.params(":id");
        Human model = Repositories.humanRepository.get(id);
        Repositories.humanRepository.remove(model);

        response.type("application/json");
        return Json.toJson(model);
    }

    public static JsonNode add(Request request, Response response) {
        response.type("application/json");
        try {
            Human model = Json.fromJson(Json.parse(request.body()), Human.class);

            Repositories.humanRepository.add(model);

            return Json.toJson(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode update(Request request, Response response) {
        response.type("application/json");
        try {
            Human model = Json.fromJson(Json.parse(request.body()), Human.class);
            //String id = request.params(":id");
            Repositories.humanRepository.update(model);

            return Json.toJson(model);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

}

