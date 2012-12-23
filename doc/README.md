#N3rd.stack:[java]

Je me suis refusé à utiliser Maven, car je ne le maîtrise pas, et je voulais faire quelque chose de plus "handmade" ou "from scratch"

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

##La suite ?

- Mettre en oeuvre la persistence
- Améliorer l'exemple javascript et html







