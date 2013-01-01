
/*
    N3rd.stack:[java]
 */

import static spark.Spark.*;
import routes.Router;
import org.k33g.helpers.*;

public class Main implements spark.servlet.SparkApplication {
    /* standalone mode for tests*/
    public static void main(String[] args)  {
        setPort(9000);
        N3rd.about();
        Router.routes();
        //SERVE STATIC FILES (only needed if standalone mode)
        Assets.setPublicPath("public.naked"); //<--- static assets path
        //Assets.setPublicPath("public.n3rd");
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