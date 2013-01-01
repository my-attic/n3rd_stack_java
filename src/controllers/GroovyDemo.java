package controllers;

import groovy.lang.Binding;
import org.k33g.helpers.Groovy;
import spark.Request;
import spark.Response;

public class GroovyDemo {

    public static String about(Request request, Response response) {
        response.type("text/html");
        try {

            Binding binding = new Binding();
            binding.setVariable("h1Title", "N3rd.stack:[java]");
            binding.setVariable("h2Title", "with Groovy XML MarkupBuilder");
            Groovy.run("groovy/about.groovy", binding);

            return binding.getVariable("output").toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



}
