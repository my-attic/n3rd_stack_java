
/*
    N3rd.stack:[java]
 */

import groovy.lang.Binding;
import org.k33g.helpers.Router;
import org.k33g.helpers.*;

import static spark.Spark.setPort;

public class Main implements spark.servlet.SparkApplication {
    /* standalone mode for tests*/
    public static void main(String[] args)  {

        try {
            Binding binding = new Binding();
            Groovy.run("config/config.groovy", binding);
            setPort((int) binding.getVariable("httpport"));
            Router.routes();
            //SERVE STATIC FILES (only needed if standalone mode)
            Assets.setPublicPath((String) binding.getVariable("publicpath"));
            Assets.setHome((String) binding.getVariable("homepage"));
            Assets.serveStatic();
            N3rd.about();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /* Web Server mode */
    @Override
    public void init() {
        N3rd.about();
        Router.routes();
    }
}