package controllers;

import models.*;
import org.codehaus.jackson.JsonNode;
import org.k33g.helpers.Json;
import org.ektorp.Options;
import org.ektorp.Revision;
import spark.Request;
import spark.Response;

import java.util.List;

public class {{model_name}}s {
    public static JsonNode getAll(Request request, Response response) {
        response.type("application/json");

        try {
            List<{{model_name}}> {{_model_name}}s = repositories.{{model_name}}s.repository.getAll();

            return Json.toJson({{_model_name}}s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode fetch(Request request, Response response) {
        response.type("application/json");

        try {
            String id = request.params(":id");
            {{model_name}} model = repositories.{{model_name}}s.repository.get(id);

            return Json.toJson(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode getAllRevisionsById(Request request, Response response) {
        response.type("application/json");

        try {
            String id = request.params(":id");
            List<Revision> revisions = repositories.{{model_name}}s.repository.getDocRevisions(id);

            return Json.toJson(revisions);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode getOneRevisionById(Request request, Response response) {
        response.type("application/json");
        try {
            String id = request.params(":id");
            String rev = request.params(":rev");
            {{model_name}} model = repositories.{{model_name}}s.repository.get(id, new Options().revision(rev));

            return Json.toJson(model);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }

    public static JsonNode delete(Request request, Response response) {
        response.type("application/json");
        try {
            String id = request.params(":id");
            {{model_name}} model = repositories.{{model_name}}s.repository.get(id);
            repositories.{{model_name}}s.repository.remove(model);

            return Json.toJson(model);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
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

