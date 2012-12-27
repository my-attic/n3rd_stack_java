package org.k33g.helpers;

import org.apache.commons.io.IOUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;

import static spark.Spark.get;

/**
 * User: k33g_org
 * Date: 12/23/12
 * Time: 3:12 PM
 */
public class Assets {

    private static String publicPath;
    private static String home;

    public static String getPublicPath() {
        return publicPath;
    }

    public static void setPublicPath(String path) {
        Assets.publicPath = path;
    }

    public static String getHome() {
        return home;
    }

    public static void setHome(String home) {
        Assets.home = home;
    }

    public static void serveStatic() {
        get(new Route("/*") {
            @Override
            public Object handle(Request request, Response response)  {

                final File pub = new File(Assets.getPublicPath());
                String pathInfo = request.pathInfo();
                String absolutePath = pub.getAbsolutePath();
                final File file = new File(absolutePath + pathInfo);



                if (!file.exists()) {
                    halt(404);
                    return null;
                }

                String mime = URLConnection
                        .getFileNameMap()
                        .getContentTypeFor(file.getName());

                System.out.println(absolutePath + pathInfo);
                System.out.println(mime);

                if(mime==null && file.getName().contains(".css")){
                    mime = "text/css";
                }

                if(mime==null && file.getName().contains(".js")){
                    mime = "application/javascript";
                }

                System.out.println(mime);

                if("/".equals(pathInfo)) {pathInfo="/"+Assets.getHome();}

                try {
                    byte[] out;
                    out = IOUtils.toByteArray(new FileInputStream(absolutePath + pathInfo));
                    response.raw().setContentType(mime+";charset=utf-8");
                    response.raw().getOutputStream().write(out, 0, out.length);
                    halt(200);
                    return null;
                } catch(IOException ex) {
                    response.type("application/json");
                    System.out.println(ex);
                    halt(500);
                    return null;
                    //return Json.toJson(ex);
                }

            }
        });
    }
}
