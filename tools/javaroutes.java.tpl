package routes;

public class Router {

    public static void routes() {

        {{#routes}}
        {{.}}.routes();
        {{/routes}}

    }
}
