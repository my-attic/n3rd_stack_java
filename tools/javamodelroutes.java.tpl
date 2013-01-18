import spark.Request
import spark.Response
import spark.Route

import static spark.Spark.delete
import static spark.Spark.get
import static spark.Spark.post
import static spark.Spark.put


/* get one {{model_name}} by id */
get(new Route("/{{_model_name}}s/:id") {
    def handle(Request request, Response response) {
        controllers.{{model_name}}s.fetch(request, response)
    }
})

/* get revisions id of {{model_name}} by id */
get(new Route("/{{_model_name}}s/:id/revisions") {
    def handle(Request request, Response response) {
        controllers.{{model_name}}s.getAllRevisionsById(request, response)
    }
})

/* get one revision of {{model_name}} by id and rev()id */
get(new Route("/{{_model_name}}s/:id/rev/:rev") {
    def handle(Request request, Response response) {
        controllers.{{model_name}}s.getOneRevisionById(request, response)
    }
})

/* get All {{model_name}}s */
get(new Route("/{{_model_name}}s") {
    def handle(Request request, Response response) {
        controllers.{{model_name}}s.getAll(request, response)
    }
})

/* create a {{model_name}} */
post(new Route("/{{_model_name}}s") {
    def handle(Request request, Response response) {
        controllers.{{model_name}}s.add(request, response)
    }
})

/* update a {{model_name}} */
put(new Route("/{{_model_name}}s/:id") {
    def handle(Request request, Response response) {
        controllers.{{model_name}}s.update(request, response)
    }
})

/* delete a {{model_name}} */
delete(new Route("/{{_model_name}}s/:id") {
    def handle(Request request, Response response) {
        controllers.{{model_name}}s.delete(request, response)
    }
})

