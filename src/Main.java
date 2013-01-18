
/*
    N3rd.stack:[java]
 */

import org.k33g.helpers.Router;
import org.k33g.helpers.*;

import static spark.Spark.setPort;

public class Main implements spark.servlet.SparkApplication {
    /* standalone mode for tests*/
    public static void main(String[] args)  {
        setPort(9090);
        N3rd.about();
        Router.routes();
        //SERVE STATIC FILES (only needed if standalone mode)
        //Assets.setPublicPath("public.naked"); //<--- static assets path
        Assets.setPublicPath("public.n3rd");
        //Assets.setPublicPath("public.angular/app");
        Assets.setHome("index.html");
        Assets.serveStatic();

    }

    /* Web Server mode */
    @Override
    public void init() {
        N3rd.about();
        Router.routes();
    }
}