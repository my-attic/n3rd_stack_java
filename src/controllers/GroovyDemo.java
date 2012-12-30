package controllers;

import groovy.lang.Binding;
import models.Human;
import org.k33g.helpers.Groovy;
import spark.Request;
import spark.Response;

import java.util.List;

public class GroovyDemo {

    public static String helloUS(Request request, Response response) {
        response.type("text/html");
        try {

            Binding binding = new Binding();
            binding.setVariable("input", "WORLD");
            Groovy.run("groovy/hello.groovy", binding);

            return binding.getVariable("output").toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public  static String helloFR(Request request, Response response) {
        response.type("text/html");
        try {

            Binding binding = new Binding();
            binding.setVariable("input","Tout Le Monde");
            Groovy.run("groovy/salut.groovy",binding);

            return binding.getVariable("output").toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String allHumans(Request request, Response response) {
        List<Human> humans = repositories.Humans.repository.getAll();

        try {
            response.type("text/html");
            Binding binding = new Binding();
            binding.setVariable("input",humans);
            Groovy.run("groovy/humans.groovy",binding);
            String html = binding.getVariable("output").toString();
            return html;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
