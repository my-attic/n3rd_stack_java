#N3rd.stack:[java]

Je me suis refusé à utiliser Maven, car je ne le maîtrise pas, et je voulais faire quelque chose de plus "handmade" ou "from scratch". 

>>*N3rd.stack:[java] se veut être "framework agnostic" (un peu comme Backbone). Elle apporte une base, vous pouvez ensuite utiliser ce que vous voulez pour la base de données ou la partie javascript.*

>>*En ce qui me concerne j'utiliserais CouchDB pour la partie data, et une base de Backbone+Knockout+KnockBack pour la partie javascript*

##Initialisation du projet

Pour initialiser le projet, choisir : File + New Project, et choisir "Command Line App" :
![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/000-new_project.png?raw=true)

Ne rien cocher dans l'écran suivant (clickez sur Finnish) :
![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/001-new_project.png?raw=true)

Et vous obtenez ainsi votre squelette de projet.

###Préparatifs de base

- Ajoutez un fichier README.md à la racine, ça fait "comme les vrais".
- Faites vous répertoire doc, où vous allez raconter la vie de votre projet
- Ajoutez Un petit répertoire lib dans lequel vous mettrez vos librairies externes (jar) et ça vous permettra ainsi de les distribuer simplement via git (je ne vous ai pas dit, on va utiliser github, je n'explique pas comment ça marche, mais vous trouverez de bonnes explications sur le net).

>>**PS :** *l'utilisation de Git & GitHub n'est pas obligatoire, c'est uniquement si vous voulez créer votre propre projet*

J'ai mis un fichier README.md aussi dans le répertoire, pour qu'il ne soit pas vide et qu'il soit créé chez GitHub au moment où je vais "pousser" ma structure de projet.

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/002-new_project.png?raw=true)


###On "envoie" ça à GitHub

Pour vérifier que cela fonctionne, nous allons déjà "pousser" le peu de choses faites à GitHub, en images :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/003-github.png?raw=true)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/004-github.png?raw=true)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/005-github.png?raw=true)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/006-github.png?raw=true)


Et voilà le résultat. *En haut à gauche vous remarquerez la pub pour Mix-It [http://www.mix-it.fr/](http://www.mix-it.fr/). Il faut y aller*.

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/007-github.png?raw=true)

##On fait son marché : Le serveur

J'avais tout d'abord besoin de quelque chose qui fasse office de serveur web et routeur. Mon choix s'est porté sur **Spark** (bookmarqué il y a un moment, c'est [@loicdescotte](https://github.com/loicdescotte) qui m'en avait parlé à l'époque) . Allons donc faire un tour ici [http://www.sparkjava.com/](http://www.sparkjava.com/) pour récupérer le bébé (profitez-en pour lire un peu la doc, ce n'est pas trop long).

Pour télécharger, c'est ici [http://code.google.com/p/spark-java/downloads/list](http://code.google.com/p/spark-java/downloads/list), j'ai utilisé : [http://spark-java.googlecode.com/files/spark-0.9.9.4-SNAPSHOT.zip](http://spark-java.googlecode.com/files/spark-0.9.9.4-SNAPSHOT.zip).

Il faut aussi les dépendances : [http://spark-java.googlecode.com/files/spark-dependencies_0.9.9.3_and_newer.zip](http://spark-java.googlecode.com/files/spark-dependencies_0.9.9.3_and_newer.zip)

Vous dézippez, vous coller `spark-0.9.9.4-SNAPSHOT.jar` dans votre répertoire `lib`. Ensuite allons faire référence à notre jar dans les propriétés du projet : click droit sur la branche du projet et choisir **Open Module Settings**.

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/008-libs.png?raw=true)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/009-libs.png?raw=true)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/010-libs.png?raw=true)

Si le plugin git vous pose une question, du genre "est-ce que vous voukez mettre en conf je ne sais quel fichier xml", dites non (clicker sur non/no).

Faites de même avec les jars contenus dans `spark-dependencies_0.9.9.3_and_newer.zip`

Vous êtes prêts pour entrer dans le vif du sujet ... Tadaaaa

##1ère utilisation de Spark

Dans votre classe **Main**, modifiez le code de la façon suivante :

```java
import static spark.Spark.*;
import spark.*;

public class Main {

    public static void main(String[] args) {
        setPort(9000);

        get(new Route("/about") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return "<b>N3rd.stack:[java]</b>";
            }
        });
        get(new Route("/about.json") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                return "{name:'N3rd.stack:[java]',version:'before alpha'}";
            }
        });

    }
}
```

Compilez, lancez & Ouvrez votre navigateur avec l'url [http://localhost:9000/about](http://localhost:9000/about) puis [http://localhost:9000/about.json](http://localhost:9000/about.json)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/011-launch.png?raw=true)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/012-launch.png?raw=true)

##J'aimerais "servir" des fichiers statiques

Je voudrais pouvoir faire référence à des fichiers statiques. C'est à dire, lorsque je fais [http://localhost:9000](http://localhost:9000), j'arriverais directement sur `index.html` et toutes les autres ressources définies dans la page (css, js, ...) seraient chargées. C'est la partie qui finalement m'a donné le plus de fil à retordre. Pour se faire, mon point de départ, mon inspiration, ... viennent d'ici : [https://github.com/perwendel/spark/issues/3](https://github.com/perwendel/spark/issues/3).

Mais préparons nos fichiers statiques.

###Préparation des fichiers

Avant d'aller modifier le code, préparons notre projet :

- au même niveau que `lib`, créez un répertoire `public` qui contiendra lui-même `css`, `js` (après vous vous organisez comme vous le sentez)
- dans `public/js`, créez un répertoire `vendors` dans lequel irons tous les frameworks javascripts nécessaires
- téléchargez (et copiez dans `vendors`) :

    - **jQuery** : [http://code.jquery.com/jquery-1.8.3.min.js](http://code.jquery.com/jquery-1.8.3.min.js)
    - **Underscore** : [http://underscorejs.org/underscore-min.js](http://underscorejs.org/underscore-min.js)
    - **Backbone** : [http://backbonejs.org/backbone-min.js](http://backbonejs.org/backbone-min.js)
    - **Knockout** : [http://knockoutjs.com/downloads/knockout-2.2.0.js](http://knockoutjs.com/downloads/knockout-2.2.0.js)
    - **Knockback** : [https://raw.github.com/kmalakoff/knockback/0.16.7/knockback.min.js](https://raw.github.com/kmalakoff/knockback/0.16.7/knockback.min.js)
    - **YepNope** : [https://raw.github.com/SlexAxton/yepnope.js/master/yepnope.1.5.4-min.js](https://raw.github.com/SlexAxton/yepnope.js/master/yepnope.1.5.4-min.js)

Ensuite préparez les styles, nous n'allons pas faire comme tout le monde (twitter bootstrap, après libre à vous de changer), nous allons utiliser **Skeleton** : [http://www.getskeleton.com/#download](http://www.getskeleton.com/#download). Téléchargez, puis copiez les fichiers css dans le répertoire `public/css`.

>>*Nous n'allons pas nous servir de tous les frameworks javascript téléchargés, on se les garde pour plus tard*

###Préparation de main.js

Le fichier `main.js` que vous allez créer dans `public`, sera le fichier qui nous permettra de charger tous nos scripts (et autres dépendances) grâce à **YepNope**. Son contenu sera le suivant :

```javascript
yepnope({
    load: {
        jquery      : 'js/vendors/jquery-1.8.3.min.js',
        underscore  : 'js/vendors/underscore-min.js',
        backbone    : 'js/vendors/backbone-min.js',
        knockout    : 'js/vendors/knockout-2.2.0.js',
        knockback   : 'js/vendors/knockback.min.js',

        //skeleton
        base        : 'css/base.css',
        layout      : 'css/layout.css',
        skeleton    : 'css/skeleton.css',

        app                 : 'js/app.js'
    },

    callback : {
        "app" : function () {
            console.log("app.js loaded ...");
        }
    },
    complete : function () {
        app.start();
    }
});
```

###Préparation de app.js

Le fichier `app.js` que vous allez créer dans `public/js`, sera le script qui contiendra le code "applicatif" :

```javascript
window.app = {
    start : function() {
        $(function (){

            $("h1").html("N3rd.stack:[java]");

            $("body").css("visibility","visible");  /*<body style="visibility:hidden">*/

        }); //Fin jQuery
    }
}
```

###Préparation de index.html

Vous allez créer dans `public`, un fichier `index.html` avec le code suivant :

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>N3rd.stack:[java]</title>

    <style>
        body {
            visibility: hidden;
        }
    </style>
</head>

<body>
    <h1>mon titre</h1>
<script src="js/vendors/yepnope.1.5.4-min.js"></script>
<script src="main.js"></script>
</body>
</html>
```

###Ok, mais pour "servir" les fichiers statiques ?

C'est à partir de là que commence la touche personnelle ;)

Dans IntelliJ, faites un click droit sur le répertoire `src` et choisissez `new+package`, donnez un nom à votre package (au hasard `org.k33g.helpers`) et créez dans celui-ci une classe `Assets` avec le code suivant :

```java
package org.k33g.helpers;

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
}
```

Ensuite avant de modifier le code de `Main.java`, j'ai besoin de la librairie `commons.apache.org/io/` que vous récupérez ici : [http://apache.opensourcemirror.com//commons/io/binaries/commons-io-2.4-bin.zip](http://apache.opensourcemirror.com//commons/io/binaries/commons-io-2.4-bin.zip). Dézippez et copiez `commons-io-2.4.jar` dans `lib`. Pensez à le référencer dans votre projet.

Nous pouvons maintenant modifier le code de `Main.java` :

```java
import static spark.Spark.*;
import spark.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;
import org.k33g.helpers.Assets;


public class Main {

    public static void main(String[] args) {
        setPort(9000);

        get(new Route("/about") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return "<b>N3rd.stack:[java]</b>";
            }
        });
        get(new Route("/about.json") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                return "{name:'N3rd.stack:[java]',version:'before alpha'}";
            }
        });

        //SERVE STATIC FILES
        Assets.setPublicPath("public");
        Assets.setHome("index.html");

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

                if(mime==null && file.getName().contains(".css")){
                    mime = "text/css";
                }

                if(mime==null && file.getName().contains(".js")){
                    mime = "application/javascript";
                }

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
                }

            }
        });

    }
}
```

>>*Pour les noms de classes, variables, etc. ... Vous faites comme vous voulez, c'est au goût de chacun.*

Maintenant, compilez, lancez, ouvrez [http://localhost:9000/](http://localhost:9000/) :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/013-static.png?raw=true)

Ouvrez la console du navigateur, et tapez le code javascript suivant :

```javascript
$.ajax({type:"GET", url:"/about.json",
    error:function(err){ console.log(err); },
    success:function(dataFromServer) { console.log(dataFromServer); }
})
```

Vous obtenez :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/014-static.png?raw=true)

**Votre 1er service JSON avec votre killer stack !**


##J'ai besoin de parler JSON avec mes objets java

Avant de passer à quelque chose de plus sérieux (la persistance ?) je voudrais pouvoir transformer facilement des objets en Json pour les "envoyer" au navigateur, mais aussi facilement mapper des objets Json sur des objets Java, un peu comme Play2!> le fait si bien.

Alors, je ne vais pas m'embêter, je vais "repomper" ce que fait Play2!> : [https://github.com/playframework/Play20/blob/master/framework/src/play/src/main/java/play/libs/Json.java](https://github.com/playframework/Play20/blob/master/framework/src/play/src/main/java/play/libs/Json.java). Si vous lisez le code, vous voyez que nous aurons besoin de la librairie `org.codehaus.jackson`, que nous téléchargeons ici : [http://jackson.codehaus.org/1.9.11/jackson-all-1.9.11.jar](http://jackson.codehaus.org/1.9.11/jackson-all-1.9.11.jar) (la version 2, nécessiterait de modifier le code "repompé" dans Play2!>, donc on reste sur la 1.9.11). Copiez le jar (`jackson-all-1.9.11.jar`) dans `lib` et référencez-le dans votre projet.

Dans le package `org.k33g.helpers`, créez une classe `Json` avec le code suivant (je le répète, honteusement repompé chez Play2!>) :

```java
package org.k33g.helpers;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.node.*;

/**
 * Helper functions to handle JsonNode values.
 */
public class Json {

    /**
     * Convert an object to JsonNode.
     *
     * @param data Value to convert in Json.
     */
    public static JsonNode toJson(final Object data) {
        try {
            return new ObjectMapper().valueToTree(data);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert a JsonNode to a Java value
     *
     * @param json Json value to convert.
     * @param clazz Expected Java value type.
     */
    public static <A> A fromJson(JsonNode json, Class<A> clazz) {
        try {
            return new ObjectMapper().treeToValue(json, clazz);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a new empty ObjectNode.
     */
    public static ObjectNode newObject() {
        return new ObjectMapper().createObjectNode();
    }

    /**
     * Convert a JsonNode to its string representation.
     */
    public static String stringify(JsonNode json) {
        return json.toString();
    }

    /**
     * Parse a String representing a json, and return it as a JsonNode.
     */
    public static JsonNode parse(String src) {
        try {
            return new ObjectMapper().readValue(src, JsonNode.class);
        } catch(Throwable t) {
            throw new RuntimeException(t);
        }
    }

}
```

###Et comment tester ceci ?

Commencez par créer un package `models`, puis, créez (dans `models`) une classe `Human` :

```java
package models;

public class Human {
    public String firstName;
    public String lastName;
    public String id;

    public Human() {
    }

    public Human(String firstName, String lastName) {
        this.id=null;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Human{" + "firstName=" + firstName +
                ", lastName=" + lastName + ", id=" + id + '}';
    }
}
```

Ensuite dans `Main.java` :

Ajoutez :

```java
import org.k33g.helpers.Json;

import models.*;
```

Puis ajoutez la "route" suivante :

```java
get(new Route("/humans/:id") {
    @Override
    public Object handle(Request request, Response response) {
        String id = request.params(":id");
        Human model = new Human("John", "Doe");
        model.id = id;
        response.type("application/json");
        return Json.toJson(model);
    }
});
```

Ainsi que :

```java
post(new Route("/humans") {
    @Override
    public Object handle(Request request, Response response) {
        response.type("application/json");
        try {

            Human model = Json.fromJson(Json.parse(request.body()), Human.class);
            model.id = java.util.UUID.randomUUID().toString();
            return Json.toJson(model);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }
});
```

Maintenant, compilez, lancez, ouvrez [http://localhost:9000/](http://localhost:9000/) :

Ouvrez la console du navigateur, et tapez le code javascript suivant pour faire une requête de type GET (et appeler notre "route" `get(new Route("/humans/:id")`) :

```javascript
var Human = Backbone.Model.extend({urlRoot:'/humans'});
var john = new Human({id:'000'});
john.fetch({success:function(model){ console.log(model);}});
```

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/015-model.png?raw=true)

Vous remarquez donc que vous conservez votre id, mais que le serveur vous a "rempli" `firstName` et `lastName`.

Maintenant, pour faire une requête de type POST (et appeler notre "route" `post(new Route("/humans")`), toujours dans la console du navigateur, tapez ceci (et validez) :

```javascript
var bob = new Human({firstName:"Bob", lastName:"Morane"});
bob.save({},{success:function(model){ console.log(model);}});
```

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/016-model.png?raw=true)

Et là, on n'est pas peu fiers ;)

###Un peu de refactoring pour faire plus propre

Nous allons faire un peu de rangement : nous allons déplacer le code relatif aux ressources statiques dans la classe `Assets` :

```java
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

                if(mime==null && file.getName().contains(".css")){
                    mime = "text/css";
                }

                if(mime==null && file.getName().contains(".js")){
                    mime = "application/javascript";
                }

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
```

Et `Main.java` devient :

```java
import static spark.Spark.*;
import spark.*;

import org.k33g.helpers.Assets;
import org.k33g.helpers.Json;

import models.*;

public class Main {

    public static void main(String[] args) {
        setPort(9000);

        get(new Route("/about") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("text/html");
                return "<b>N3rd.stack:[java]</b>";
            }
        });
        get(new Route("/about.json") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                return "{name:'N3rd.stack:[java]',version:'before alpha'}";
            }
        });

        get(new Route("/humans/:id") {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                Human model = new Human("John", "Doe");
                model.id = id;
                response.type("application/json");
                return Json.toJson(model);
            }
        });

        post(new Route("/humans") {
            @Override
            public Object handle(Request request, Response response) {
                response.type("application/json");
                try {

                    Human model = Json.fromJson(Json.parse(request.body()), Human.class);
                    model.id = java.util.UUID.randomUUID().toString();
                    return Json.toJson(model);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    return Json.toJson(e);
                }
            }
        });

        //SERVE STATIC FILES
        Assets.setPublicPath("public");
        Assets.setHome("index.html");

        Assets.serveStatic();
    }
}
```

##Avant de passer à la suite on "pousse" tout ça à GitHub

- Pensez à ajouter vos fichiers à Git : Par exemple sur le répertoire `lib` ou directement sur `spark-0.9.9.4-SNAPSHOT.jar`, pensez à faire un click droit + Git + Add (faire ça pour tous les jar de `lib`)
- Puis à tout commiter  : sur la branche projet : click droit + Git + Commit Directory ...
- Puis on "pousse" à GitHub : sur la branche projet : click droit + Git + Repository + Pull

##Support de Groovy

Groovy est un langage qui me plaît énormément, donc j'ai rajouté à N3rd.stack la possibilité d'exécuter des scripts Groovy de manière très très simple pour pouvoir notamment utiliser les **gstrings**. Téléchargez donc [http://dist.groovy.codehaus.org/distributions/groovy-binary-2.0.6.zip](http://dist.groovy.codehaus.org/distributions/groovy-binary-2.0.6.zip), dézippez et copiez dans `lib` le jar `groovy-all-2.0.6-indy.jar`. N'oubliez pas de référencer le jar dans votre projet.

Ensuite dans le package `org.k33g.helpers`, créez une classe `Groovy.java` avec le code ci-dessous :

```java
package org.k33g.helpers;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.File;
import java.io.IOException;

public class Groovy {

    public static String getScriptsPath() {
        return groovyScriptsPath;
    }

    public static void setScriptsPath(String groovyScriptsPath) {
        Groovy.groovyScriptsPath = groovyScriptsPath;
    }

    private static String groovyScriptsPath;

    private static GroovyScriptEngine gse = null;

    public static void iniScriptEngine() throws IOException {
        if(gse==null) {
            String absolutePath = (new File(groovyScriptsPath)).getAbsolutePath();
            String[] roots = new String[] { absolutePath };
            gse = new GroovyScriptEngine(roots);
        }
    }

    public static void run(String script, Binding binding) throws IOException, ResourceException, ScriptException {
        iniScriptEngine();
        gse.run(script, binding);
    }
}
```

###Création de scripts Groovy

Tout d'abord, crééz un répertoire `gscripts` (au même niveau que `public`), dans lequel nous allons créer `hello.groovy` et `salut.groovy`.

**hello.groovy :**

```groovy
output = "Hello, ${input}! This is N3rd.stack:[java] ..."
```

**salut.groovy :**

```groovy
output = """
<!DOCTYPE html>
<html>
<body>
    <h1>Salut, ${input}! C'est la N3rd.stack:[java] ... </h1>
<script src="../js/vendors/jquery-1.8.3.min.js"></script>
<script>
        \$("h1").css("color","green");
</script>
</body>
</html>
"""
```

###Utilisation dans Main.java

Il faut tout d'abord importer ceci :

```java
import org.k33g.helpers.Groovy;
import groovy.lang.Binding;
```

Ensuite il faut préciser le répertoire des scripts :

```java
Groovy.setScriptsPath("gscripts");
```

Puis finalement initiliser le GroovyScriptEngine au démarrage de N3rd.stack (ce n'est pas obligatoire, car l'exécution du 1er script le fait, mais cela permet de gagner du temps et de rendre l'exécution du 1er script plus rapide) :

```java
try {
    Groovy.iniScriptEngine();
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

###Création de 2 nouvelles routes

```java
get(new Route("/groovy/us") {
    @Override
    public Object handle(Request request, Response response) {
        response.type("text/html");
        try {

            Binding binding = new Binding();
            binding.setVariable("input", "WORLD");
            Groovy.run("hello.groovy", binding);

            return binding.getVariable("output").toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
});
```

>>**Remarque :** `binding.setVariable("input", "WORLD");` signifie que l'on crée une variable `input` avec la valeur `WORLD`, elle sera "lue" par le script Groovy `output = "Hello, ${input}! This is N3rd.stack:[java] ..."`, le résultat est donc mis dans une variable `output` que java peut "lire" avec `binding.getVariable("output")`.

```java
get(new Route("/groovy/fr") {
    @Override
    public Object handle(Request request, Response response) {
        response.type("text/html");
        try {

            Binding binding = new Binding();
            binding.setVariable("input","Tout Le Monde");
            Groovy.run("salut.groovy",binding);

            return binding.getVariable("output").toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
});
```

###Testez

[http://localhost:9000/groovy/us](http://localhost:9000/groovy/us)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/017-groovy.png?raw=true)

[http://localhost:9000/groovy/fr](http://localhost:9000/groovy/fr)

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/018-groovy.png?raw=true)

>>**Le petit truc sympa :** vous pouvez bien sûr modifier vos scripts groovy sans redémarrer N3rd.stack ;)


##Persistence

###Le serveur de base de données

J'étais parti sur du **Redis**, ou du **MongoDB**, mais pour des besoins personnels, j'ai du utiliser du **CouchDB**, donc ce sera du **CouchDB**, que vous récupérerez ici : 

[http://couchdb.apache.org/](http://couchdb.apache.org/)

###Le driver java

Nous allons utiliser **Ektorp** : [https://github.com/helun/Ektorp/downloads](https://github.com/helun/Ektorp/downloads). Copiez le jar `org.ektorp-1.2.2.jar` dans le répertoire `lib`. Pensez à le référencer (je radote). Vous aurez aussi besoin des dépendances de **Apache HttpComponents**, prenez la partie "HttpClient" [http://mirrors.ircam.fr/pub/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.2.2-bin.zip](http://mirrors.ircam.fr/pub/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.2.2-bin.zip).

###Nous avons besoin d'initialiser la base

Dans le package `org.k33g.helpers`, créez une classe `CouchDB` avec le code ci-dessous :

```java
package org.k33g.helpers;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;

public class CouchDB {

    public static CouchDbConnector getDb(String dbName, String url) {
        HttpClient httpClient = null;
        try {
            httpClient = new StdHttpClient.Builder()
                    .url(url)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector db = new StdCouchDbConnector(dbName, dbInstance);

        db.createDatabaseIfNotExists();
        return db;
    }
}
```

Pour l'initialiser, nous utiliserons le code suivant (ou quelque chose dans ce style) `CouchDbConnector db = CouchDB.getDb("nom_de_ma_base","http://localhost:5984");` mais nous y reviendrons plus tard.

###Modifions notre modèle Human

Modifiez le code source de la classe `Human` (dans `models`) pour le rendre "compatible" avec **Ektorp** :

```java
package models;

import org.codehaus.jackson.annotate.*;

public class Human {

    @JsonProperty("_id")
    public String id;

    @JsonProperty("_rev")
    public String revision;    
    
    public String firstName;
    public String lastName;


    public Human() {
    }

    public Human(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Human{" + "firstName=" + firstName +
                ", lastName=" + lastName + ", id=" + id + '}';
    }
}
```

###Nous aurons besoin d'un repository

Il nous faudra un objet "repository" pour gérer les opérations de CRUD sur `Human`. Créez un package `repositories` avec une classe `HumanRepository` qui contiendra la code suivant :

```java
package repositories;

import models.Human;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

public class HumanRepository extends CouchDbRepositorySupport<Human> {
    public HumanRepository(CouchDbConnector db) {
        super(Human.class, db);
    }
}
```

##Il est temps de d'écrire des routes qui servent à quelque chose

Nous allons écrire les routes permettant de lire et écrire dans la base. Mais tout d'abord initialisons notre base dans la classe `Main` :

Ajoutez les imports nécessaires :

```java
import org.ektorp.CouchDbConnector;
import repositories.HumanRepository;
```

Puis le bout de code pour initialiser la base  (ou "ouvrir" la base si elle existe déjà) :

```java
CouchDbConnector db = CouchDB.getDb("humansdb","http://localhost:5984");
HumanRepository humanRepository = new HumanRepository(db);
```
>>**PS :** le serveur CouchDB doit bien sûr être lancé.

###"Route de création"

Modifions la route `post(new Route("/humans")` existante, comme ceci (verbe REST : POST) :

```java
post(new Route("/humans") {
    @Override
    public Object handle(Request request, Response response) {
        response.type("application/json");
        try {
            Human model = Json.fromJson(Json.parse(request.body()), Human.class);

            humanRepository.add(model);

            return Json.toJson(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }
});
```

###Testons la création

Lancez le serveur **CouchDB**

Démarrez l'application web : [http://localhost:9000/](http://localhost:9000/)

Puis ouvrez la console du navigateur et saisissez ce code :

```javascript
var Human = Backbone.Model.extend({urlRoot:'/humans', idAttribute: '_id'});
var bob = new Human({firstName:"Bob", lastName:"Morane"});
bob.save({},{success:function(model){ console.log(model);}});
```

Vous pouvez vérifier que vous obtenez en retour un id (donc le modèle a bien été créé en base) :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/019-couchdb.png?raw=true)

Et nous pouvons tout de suite le vérifier en allant faire un tour dans l'administration de **CouchDB** (pour y accéder [http://127.0.0.1:5984/_utils/index.html](http://127.0.0.1:5984/_utils/index.html)):

>>**Remarque importante :** Notez bien `idAttribute: '_id'` dans la définition du modèle, cela permet de dire à **Backbone** que `ìd` n'est plus `id` mais `_id` et ainsi "communiquer" plus facilement avec **CouchDB** qui affecte un id à ses documents(modèles) ayant pour nom `_id`. Et tout cela sans être obligé de re-écrire une quelconque mécanique. Pour de meilleures explications : [http://backbonejs.org/#Model-idAttribute](http://backbonejs.org/#Model-idAttribute) *PS: ça marche aussi pour MongoDB.*

On note que la base "humansdb" a bien été créée et qu'elle contient un enregistrement :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/020-couchdb.png?raw=true)

Si on sélectionne la base, on obtient la liste des enregistrements (enfin de l'enregistrement) :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/021-couchdb.png?raw=true)

Si on sélectionne l'enregistrement, on obtient le détail de celui-ci :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/022-couchdb.png?raw=true)

Ajoutons donc encore quelques "humains" (via la console du navigateur) avant de passer à la suite :

```javascript
var john = new Human({firstName:"John", lastName:"Doe"});
john.save({},{success:function(model){ console.log(model);}});
var jane = new Human({firstName:"Jane", lastName:"Doe"});
jane.save({},{success:function(model){ console.log(model);}});
```

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/023-couchdb.png?raw=true)

Vous pouvez vérifier qu'ils ont bien été "persistés" en base :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/024-couchdb.png?raw=true)

###"Route d'interrogation"

Nous allons créer la "route" java qui va nous permettre de "récupérer" tous les humains de la base. Donc dans `Main.java` ajoutez cette route (verbe REST : GET) :

```java
get(new Route("/humans") {
    @Override
    public Object handle(Request request, Response response) {
        List<Human> humans = humanRepository.getAll();

        response.type("application/json");
        return Json.toJson(humans);
    }
});
```

Sauvegardez, compilez, lancez et testez avec le code suivant dans la console du navigateur :

```javascript
var Human = Backbone.Model.extend({urlRoot:'/humans', idAttribute: '_id'});
var Humans = Backbone.Collection.extend({url:'/humans'});
var humans = new Humans();
humans.fetch({success:function(models){ 
    models.forEach(function(model){ console.log(model.get("firstName"), model.get("lastName")); });    
}});
```

Vous obtiendrez ceci :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/025-couchdb.png?raw=true)


Maintenant, nosu voulons "récupérer" un humain par son id. Modifiez donc la route `get(new Route("/humans/:id")` existante comme ceci (verbe REST : GET) : 

```java
get(new Route("/humans/:id") {
    @Override
    public Object handle(Request request, Response response) {
        String id = request.params(":id");
        Human model = humanRepository.get(id);
        response.type("application/json");
        return Json.toJson(model);
    }
});
```

Puis vérifiez comme précédement (récupérez l'id d'un des modèles) dans la console du navigateur :

```javascript
var Human = Backbone.Model.extend({urlRoot:'/humans', idAttribute: '_id'});
var john = new Human({_id:"11e483a3c583425cbc73e6b08b00893d"});
john.fetch({success:function(model){ console.log(model.get("firstName"), model.get("lastName")); }});
```

Vous obtiendrez bien votre modèle :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/026-couchdb.png?raw=true)

###"Route de mise à jour"

Il faut maintenant créer une route de "modification" (verbe REST : PUT) :

```java
put(new Route("/humans/:id") {
    @Override
    public Object handle(Request request, Response response) {
        response.type("application/json");
        try {
            Human model = Json.fromJson(Json.parse(request.body()), Human.class);
            //String id = request.params(":id");
            humanRepository.update(model);

            return Json.toJson(model);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return Json.toJson(e);
        }
    }
});
```

Vous sauvegardez, compilez, relancez et re-testez comme ceci :

```javascript
var Human = Backbone.Model.extend({urlRoot:'/humans', idAttribute: '_id'});
var john = new Human({_id:"11e483a3c583425cbc73e6b08b00893d"});
john.fetch({success:function(model){ console.log(model.get("firstName"), model.get("lastName")); }});
john.set({firstName:"JOHN", lastName:"DOE"})
john.save({},{success:function(model){ console.log(model);}});
```

Et vous obtenez bien ceci :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/027-couchdb.png?raw=true)

Et vous pouvez vérifier en bade que les modifications ont bien été prises en compte :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/028-couchdb.png?raw=true)

###"Route de suppression"

Une dernière pour la route (je sais c'est lourd) : la suppression (verbe REST : DELETE) :

```java
delete(new Route("/humans/:id") {
    @Override
    public Object handle(Request request, Response response) {
        String id = request.params(":id");
        Human model = humanRepository.get(id);
        humanRepository.remove(model);

        response.type("application/json");
        return Json.toJson(model);
    }
});
```

On teste : 

```javascript
var Human = Backbone.Model.extend({urlRoot:'/humans', idAttribute: '_id'});
var john = new Human({_id:"11e483a3c583425cbc73e6b08b00893d"});
john.fetch({success:function(model){ console.log(model.get("firstName"), model.get("lastName")); }});
john.destroy({success:function(model){ console.log(model);}});
```

Vous obtenez :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/029-couchdb.png?raw=true)

Vous vérifiez côté **CouchDB**

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/030-couchdb.png?raw=true)

Voilà voilà. Vous avez déjà quelque chose de sympa qui mérite un peu de refactoring, mais vous puvez déjà commencer à vous amuser.

##Partie javascript

Nous avions préparé le terrain, nous allons maintenant organiser nos fichiers et coder le nécessaire pour afficher nos données dans notre page web, et permettre aussi d'ajouter de nouveaux éléments.

###Core

Dans `public/js`, il faut créer une répertoire `core` dans lequel nous allons "coller" un peu de code "fédérateur". Créez dans `core` un fichier `n3rd.stack.js` avec le code suivant :

```javascript
var N3rd = (function () {
    var n3rd = {};

    n3rd.Models = {};
    n3rd.Collections = {};
    n3rd.Views = {};
    n3rd.Controllers = {};
    n3rd.Router = {};

    n3rd.Kind = function() {
        this.initialize && this.initialize.apply(this, arguments);
    };
    n3rd.Kind.extend = Backbone.Model.extend;

    return n3rd;
}());
```

Ce sera le module de base de notre application. La partie ci-dessous :

```javascript
    var n3rd = {};

    n3rd.Models = {};
    n3rd.Collections = {};
    n3rd.Views = {};
    n3rd.Controllers = {};
    n3rd.Router = {};
```

Nous permettra d'écrire des choses comme celle-ci :

```javascript
N3rd.Models.Human = Backbone.Model.extend({});
```

l'autre partie, nous permettra d'utiliser le modèle objet de **Backbone** 

```javascript
    n3rd.Kind = function() {
        this.initialize && this.initialize.apply(this, arguments);
    };
    n3rd.Kind.extend = Backbone.Model.extend;
```

Et nous pourront écrire des choses comme celle-là :

```javascript
N3rd.Controllers.HumansCtrl = N3rd.Kind.extend({})
```

et de définir nos propres composants (classes/kind).

###Modèles

Dans `public/js`, il faut créer une répertoire `models`. Créez dans `models` un fichier `human.js` avec le code suivant :

```javascript
var N3rd = (function (n3rd) {

    n3rd.Models.Human = Backbone.Model.extend({
        urlRoot :"/humans",
        idAttribute: '_id'
    });

    return n3rd;
}(N3rd));
```

>>**Rappel :** `idAttribute: '_id'` est une spécificité liée à **CouchDB** (et **MongoDB**).

###Collections

Dans `public/js`, il faut créer une répertoire `collections`. Créez dans `collections` un fichier `humans.js` avec le code suivant :

```javascript
var N3rd = (function (n3rd) {

    n3rd.Collections.Humans = Backbone.Collection.extend({
        url :"/humans",
        model : N3rd.Models.Human
    });

    return n3rd;
}(N3rd));
```

Nous avons donc toute la mécanique nécessaire en ce qui concerne la partie données. Maintenant, il va falloir afficher ces données.

###index.html - 1ère partie

Définissons l'ihm de notre Webapp :

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>N3rd.stack:[java]</title>
</head>

<body style="visibility:hidden">
    <h1>mon titre</h1>
    <div id="formHumanView">
        <form>
            <input data-bind="value: firstName"/><span> </span>
            <input data-bind="value: lastName"/>
        </form>
    </div>

    <div id="messageHumanView">
        <span data-bind="text: firstName"></span>
        <span data-bind="text: lastName"></span>
    </div>

    <div id="listHumansView">
        <table>
            <thead>
            <tr><th>id</th><th>FirstName</th><th>LastName</th><th>Action</th></tr>
            </thead>
            <tbody data-bind="foreach: humans">
            <tr>
                <td><a data-bind="text:_id, attr:{href:_id}"></a></td>
                <td data-bind="text: firstName"></td>
                <td data-bind="text: lastName"></td>
                <td><a data-bind="attr:{href:_id}">Delete</a></td>
            </tr>
            </tbody>
        </table>
    </div>

    <script src="js/vendors/yepnope.1.5.4-min.js"></script>
    <script src="main.js"></script>

    <style>
        body {
            margin: 20px;
        }

        table, th, td, tr {
            border-style: solid;
            border-width: 1px;
            border-color: #000000;
        }        
    </style>
</body>
</html>
```

- le 1er DIV `<div id="formHumanView">` permettra de saisir des données (pour plus tard les insérer en base).
- le 2ème DIV `<div id="messageHumanView">` affichera la même chose que le formulaire de saisie, et sera modifié si les données de saisie sont modifiées
- le 3ème DIV `<div id="listHumansView">` affichera la liste des humains

>>**Remarquez** les attributs de type `data-bind="value: field_name"`, `data-bind="text: field_name"`, `data-bind="foreach: collection_name"`. C'est ce qui permmettra à **Knockout** et **Knockback** de faire le lien entre le DOM et les modèles et collections **Backbone**.

###Le contrôleur pour gérer tout ça

Dans `public/js`, il faut créer une répertoire `controllers`. Créez dans `controllers` un fichier `humansCtrl.js` avec le code suivant :

```javascript
N3rd.Controllers.HumansCtrl = N3rd.Kind.extend({

},{//static
    model : new N3rd.Models.Human({firstName:"John", lastName:"Doe"}),
    collection : new N3rd.Collections.Humans(),

    bindViews:function(){
        var self = this;

        N3rd.Controllers.HumansCtrl.collection.fetch({success:function(){
            ko.applyBindings({humans:kb.collectionObservable(N3rd.Controllers.HumansCtrl.collection)}, $("#listHumansView")[0]);
            ko.applyBindings(kb.viewModel(N3rd.Controllers.HumansCtrl.model), $("#formHumanView")[0]);
            ko.applyBindings(kb.viewModel(N3rd.Controllers.HumansCtrl.model), $("#messageHumanView")[0]);
        }});

    }
});
```  

J'ai pris le parti de faire un contrôleur statique (grâce au modèle objet de **Backbone**).

###Lions tout ceci

Modifiez le fichier `public/main.js` afin de charger les bonnes dépendances :

```javascript
yepnope({
    load: {
        jquery      : 'js/vendors/jquery-1.8.3.min.js',
        underscore  : 'js/vendors/underscore-min.js',
        backbone    : 'js/vendors/backbone-min.js',
        knockout    : 'js/vendors/knockout-2.2.0.js',
        knockback   : 'js/vendors/knockback.min.js',

        core        : 'js/core/n3rd.stack.js',

        //skeleton
        base        : 'css/base.css',
        layout      : 'css/layout.css',
        skeleton    : 'css/skeleton.css',

        app         : 'js/app.js'
    },

    callback : {
        "app" : function () {
            console.log("app.js loaded ...");
        }
    },
    complete : function () {
        //foo
    }
});
```

Puis créez le code applicatif dans `public/js/app.js` :

```javascript
yepnope({
    load: {
        human               : 'js/models/human.js',
        humans              : 'js/collections/humans.js',
        humansCtrl          : 'js/controllers/humansCtrl.js'
    },
    complete : function () {

        $(function (){
            $("h1").html("N3rd.stack:[java]");

            N3rd.Controllers.HumansCtrl.bindViews();

            $("body").css("visibility","visible");  /*<body style="visibility:hidden">*/

        }); //Fin jQuery
    }
});
```

Dans le 2ème "bout de code", je charge mes modèles, collections, contrôleurs, puis je "bind" mes data au DOM de ma page. Si ce n'est pas déjà fait, relancez l'application et ouvrez la page dans le navigateur : [http://localhost:9000/](http://localhost:9000/).

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/031-js.png?raw=true)

Vous pouvez vérifier que si vous changez les données dans les zones de saisie, l'affichage en dessous se met à jour quand vous perdez le focus de la zone de saisie :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/032-js.png?raw=true)

Maintenant, nous souhaitons que lorsque l'on clique sur le lien de gauche (qui affiche l'id du modèle), cela aille chercher les infos du modèle côté serveur, que lorsque l'on clique sur le lien "Delete", cela supprime le modèle du serveur, et que l'on puisse ajouter un modèle à partir des zones de saisie.

###Modifions notre contrôleur

Nous allons ajouter 3 méthodes (statiques) au contrôleur :

- `addHuman`
- `select`
- `selectAndDelete`

de cette manière :

```javascript
N3rd.Controllers.HumansCtrl = N3rd.Kind.extend({

},{//static
    model : new N3rd.Models.Human({firstName:"John", lastName:"Doe"}),
    collection : new N3rd.Collections.Humans(),

    bindViews:function(){
        var self = this;

        N3rd.Controllers.HumansCtrl.collection.fetch({success:function(){
            ko.applyBindings({humans:kb.collectionObservable(N3rd.Controllers.HumansCtrl.collection)}, $("#listHumansView")[0]);
            ko.applyBindings(kb.viewModel(N3rd.Controllers.HumansCtrl.model), $("#formHumanView")[0]);
            ko.applyBindings(kb.viewModel(N3rd.Controllers.HumansCtrl.model), $("#messageHumanView")[0]);
        }});

    },
    addHuman : function() {
        var tmpModel = N3rd.Controllers.HumansCtrl.model.clone();
        tmpModel.save({},{
            success:function(){
                N3rd.Controllers.HumansCtrl.collection.fetch({
                    success: function(data){/**/},
                    error: function(err){throw err;}
                });
            },
            error: function(err){throw err;}
        });
    },
    //called when link is clicked
    select : function(model) {
        var tmpModel = new N3rd.Models.Human();
        tmpModel.set("_id", model._id());
        tmpModel.fetch({ //GET
            success:function(){console.log(tmpModel);},
            error:function(err){throw err;}
        });
    },
    //called when delete link is clicked
    selectAndDelete : function(model) {
        var tmpModel = new N3rd.Models.Human();
        tmpModel.set("_id", model._id());

        tmpModel.destroy({ //DELETE
            success:function(){
                N3rd.Controllers.HumansCtrl.collection.fetch({
                    success: function(data){
                        console.log("Collections fetched after delete : ", data);
                    },
                    error: function(err){throw err;}
                });
            },
            error:function(err){throw err;}
        });
    }
});
```

###Lions les méthodes du contrôleur au DOM

Modifiez le code html de cette façon :

```html
<div id="formHumanView">
    <form>
        <input data-bind="value: firstName"/><span> </span>
        <input data-bind="value: lastName"/>
        <button data-bind="click:N3rd.Controllers.HumansCtrl.addHuman">ADD ME</button>
    </form>
</div>
```

Nous avons ajouté un bouton et avons affecté la méthode du contrôleur `N3rd.Controllers.HumansCtrl.addHuman` à l'évènement `click` du bouton.

```html
<div id="listHumansView">
    <table>
        <thead>
        <tr><th>id</th><th>FirstName</th><th>LastName</th><th>Action</th></tr>
        </thead>
        <tbody data-bind="foreach: humans">
        <tr>
            <td><a data-bind="click:N3rd.Controllers.HumansCtrl.select, text:_id, attr:{href:_id}"></a></td>
            <td data-bind="text: firstName"></td>
            <td data-bind="text: lastName"></td>
            <td><a data-bind="click:N3rd.Controllers.HumansCtrl.selectAndDelete, attr:{href:_id}">Delete</a></td>
        </tr>
        </tbody>
    </table>
</div>
```

Nous avons affecté la méthode du contrôleur `N3rd.Controllers.HumansCtrl.select` à l'évènement `click` du 1er lien et la méthode du contrôleur `N3rd.Controllers.HumansCtrl.selectAndDelete` à l'évènement `click` du 2ème lien.

Sauvegardez et rafraichissez votre page (oui pas besoin de recompiler pour la partie statique) et testez :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/033-js.png?raw=true)

Si vous ouvrez la console du navigateur et que vous cliquez sur le lien qui affiche l'id du modèle, vous pouvez vérifier qu'il y a bien un traitement :

![n3rd](https://github.com/k33g/n3rd_stack_java/blob/master/doc/rsrc/034-js.png?raw=true)

Et voilà. Votre stack est terminée (ok, il faut le dire vite, mais vous avez de quoi continuer par vos propre moyens).

##On devient "pro" : Maven

Nous allons "Maveniser" notre projet (dans IntelliJ, il faut avoir installé le plugin Maven).

- Créez à la racine du projet un fichier `pom.xml` (merci à [Sébastien Letélié](https://github.com/sebmade) pour le pom :-)) :

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.k33g</groupId>
    <artifactId>n3rd-stack</artifactId>
    <version>0.1</version>
    <build>
        <sourceDirectory>src</sourceDirectory>
        <testSourceDirectory>test</testSourceDirectory>
        <resources>
            <resource>
                <directory>src</directory>
                <excludes>
                    <exclude>**/*.java</exclude>
                </excludes>
            </resource>
        </resources>
    </build>
    <dependencies>
        <dependency>
            <groupId>spark</groupId>
            <artifactId>spark</artifactId>
            <version>0.9.9.4-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.ektorp</groupId>
            <artifactId>org.ektorp</artifactId>
            <version>1.2.2</version>
        </dependency>
        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy-all</artifactId>
            <version>2.0.6</version>
        </dependency>
    </dependencies>
    <repositories>
        <repository>
            <id>Spark repository</id>
            <url>http://www.sparkjava.com/nexus/content/repositories/spark/</url>
        </repository>
    </repositories>
</project>
```

- Supprimez le répertoire `lib` (celui où il y a tous les jars)
- faites un click-droit sur `pom.xml` et sélectionnez **Add as Maven project**
- patientez quelques instants, et voilà votre projet est "mavenisé"

##La suite ... ?

Il faudra refactorer ...







