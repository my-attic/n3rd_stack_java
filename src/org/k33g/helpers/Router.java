package org.k33g.helpers;

import groovy.lang.Binding;
import org.k33g.helpers.Groovy;

import java.io.File;

public class Router {

    public static void routes() {

        /*--- Groovy Support ---*/
        Groovy.setScriptsPath("");
        try {
            Groovy.iniScriptEngine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /*----------------------*/

        String groovyFile;
        File folder = new File("routes");
        File[] listOfFiles = folder.listFiles();

        //System.out.println(Json.toJson(listOfFiles));

        for (int i = 0; i < listOfFiles.length; i++)
        {

            if (listOfFiles[i].isFile())
            {
                groovyFile = listOfFiles[i].getName();
                if (groovyFile.endsWith(".groovy"))
                {
                    try {
                        //Binding binding = new Binding();
                        Groovy.run("routes/"+groovyFile, new Binding());

                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                }
            }
        }

    }
}
