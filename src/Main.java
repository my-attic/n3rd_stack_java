
/*
    N3rd.stack:[java]
 */

import static spark.Spark.*;
import routes.Router;
import org.k33g.helpers.*;

public class Main implements spark.servlet.SparkApplication {
    /* standalone mode */
    public static void main(String[] args)  {
        setPort(9000);
        N3rd.about();
        Router.routes();

        //SERVE STATIC FILES (only needed if standalone mode)
        Assets.setPublicPath("public.n3rd"); //<--- static assets path
        //Assets.setPublicPath("public.simple");
        Assets.setHome("index.html");
        Assets.serveStatic();
    }

    /* Web Server mode */
    @Override
    public void init() {
        N3rd.about();
        Router.routes();
        /*
            Try this : mvn jetty:run
         */
    }
}