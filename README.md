#N3rd.stack:[java]

##Présentation

N3rd.stack:[java] est une "petite" stack java agnostique (*Qui peut s’adapter aux composants avec lesquels il interagit. Wikipedia*).

N3rd.stack:[java] vous apporte la structure nécessaire pour faire des web apps de type "Single Page Application" en vous apportant les composants nécessaires aussi bien côté serveur que côté client, notamment les éléments pour mettre en oeuvre des interfaces "RESTful JSON".

###Qu'est-ce qu'il y a dedans ?

Alors comme l'indique son nom, c'est du java, avec (entre autre chose) les composants suivants :

- la base de la stack est le framework **Spark** [http://www.sparkjava.com/](http://www.sparkjava.com/)
- la partie base de données est assurée par **CouchDB**, mais vous verrez que vous pouvez facilement utiliser n'importe quoi, N3rd.stack:[java] est **"framework agnostic"** ... ou presque
- N3rd.stack:[java] peut aussi utiliser **Groovy** (expérimental)
- et d'autres composants que vous pouvez découvrir en lisant les [explications pour créer N3rd.stack:[java] "from scratch"](https://github.com/k33g/n3rd_stack_java_origins/blob/master/doc/README.md)
- il y a même un "bout de code" venant de **Play!>2** pour la partie JSON ;)

Il y a aussi du javascript fourni avec. Pour le moment 2 "stack js" :

- **AngularJS** par [sebmade](https://github.com/sebmade)
- **Backbone+Knockout+Knockback+RequireJs** par votre serviteur

>>**N3rd.stack:[java] est dédiée Webapp de type "Single Page Application**, c'est pour cela que je n'inclus pas de moteur de template (pour le moment), mais vous verrez que cela peut se faire facilement.

>>**N3rd.stack:[java] est pensée pour être le plus simple possible** *si si, j'ai essayé*

>>**N3rd.stack:[java] est une "façon de faire", vous pouvez tout changer, même la base Spark**

###Mes inspirations ?

- PlayFramework
- RESThub
- Ratpack
- Node.js
- Express.js

##Pré-requis

- Maven
- Grunt
- CouchDB

##Préparation

- Downloader [https://github.com/k33g/n3rd_stack_java/archive/master.zip](https://github.com/k33g/n3rd_stack_java/archive/master.zip)
- Dé-zipper
- Dans le répertoire, lancer la commande `mvn dependency:copy-dependencies` (1 fois si nécessaire)
- Dans le répertoire, lancer la commande `mvn compile`

##Lancement

Dans un terminal :

    grunt & mvn jetty:run

Ou :

    ./start.sh

##Utilisation & pseudo "Hot reloading"

[http://localhost:9000/](http://localhost:9000/)

Dès que vous modifiez un code java, grunt le détecte et lance un `mvn compile`. Le plugin jetty, détecte qu'il y a eu une compilation et recharge l'application.

 >>**Remarque :** cela fonctionne très bien avec SublimeText, avec IntelliJ la sauvegarde automatique entre en conflict avec le système, donc utilisez plutôt un simple `mvn jetty:run` et déclenchez le rechargement par une recompilation.

##Paramétrages

Dans `pom.xml` vous devez :

- définir le chemin des fichiers statiques, par ex : `<webAppSourceDirectory>${basedir}/public.angular/app</webAppSourceDirectory>` ou `<webAppSourceDirectory>${basedir}/public.n3rd</webAppSourceDirectory>`, etc. ... Ainsi vous pouvez utilisez différentes stacks javascripts selon vos goûts.
- définir le port http, par ex : `<port>9090</port>`


##Sous Linux

En cas d'erreurs retournées par maven à la compilation, rajoutez ceci dans `pom.xml` :

            <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.0</version>
                    <configuration>
                      <source>1.7</source>
                      <target>1.7</target>
                    </configuration>
            </plugin>


##Sous Windows

>>*Il faudra attendre un peu*


##Utilitaires de génération de code

>>**Remarque :** cette partie est encore expérimentale.

Si dans un terminal, dans le répertoire de la stack, vous tapez la commande `./tools/newjavamodel.js Model_Name`, par exemple :

    ./tools/newjavamodel.js Human '{"firstName":"String","lastName":"String"}'

les codes suivants seront générés :

- `src/models/Human.java`
- `src/repositories/Humans.java` (pour se connecter à CouchDB + les "finders")
- `src/controllers/Humans.java` (les services REST de CRUD)
- `src/routes/Humans.java`

>>**Attention :** Il faut ensuite ajouter la route dans `src/routes/Router.java`

Sauvegardez tout (si tout va bien, cela recompilera et relancera Jetty)

Pour tester, essayez : [http://localhoqt:9090/aliens]

##Au fait ...

###C'est quoi une stack ?

Selon moi, (je parle de stack technique), c'est un ensemble de composants, frameworks, ... qui une fois assemblés permettent de réaliser des projets de développement, tels des sites, des applications web, mobiles, etc. ... La réalisation de la stack doit demander le moins de développement possible, on fait juste de l'assemblage, et le résultat doit être un outil simple à utiliser.

###Pourquoi une nième stack ?

Alors je fais ça à titre d'exercice, ça me permet d'apprendre pas mal de choses, mais aussi ça me sert dans mon travail de tous les jours. Je suis amené régulièrement à réaliser des POC (Proof Of Concept) et de temps en temps je n'ai pas besoin de me ré-installer tout un attirail serveur par exemple. D'aucun me diront, prend Play2!> ou Play1!>, c'est en général mon choix. Mais là j'avais envie de m'amuser.

###Mes objectifs

- Je ne ferais rien de génial, je vais quasiment juste me contenter d'assembler des composants.
- Cela doit être le plus simple possible (un débutant doit s'y retrouver)
- J'ai prévu de m'en servir dans la vrai vie pour mes POCS

###Avertissements

- Je n'ai pas la prétention d'être un développeur Java, donc ne prenez pas ça pour un modèle
- Cependant, si cela vous apprend quelque chose, j'en serais très heureux
- **Pull request acceptées**, surtout si elles sont simples, et améliorent le "design" du code & de l'architecture

**Les explications pour créer N3rd.stack:[java] "from scratch" sont ici : [https://github.com/k33g/n3rd_stack_java_origins/blob/master/doc/README.md](https://github.com/k33g/n3rd_stack_java_origins/blob/master/doc/README.md)**

>>*Attention le répertoire `doc` n'est pas le mode d'emploi de N3rd.stack:[java], mais l'explication de sa "création". Il y aura cependant un chapitre dédié à uniquement son utilisation en l'état... mais plus tard*

>>*J'ai fais tout ça avec IntelliJ car il y avait une grosse promo de décembre, mais sinon, avec Netbeans c'est tout à fait faisable*

>>**Si vous downloadez le projet ici : [https://github.com/k33g/n3rd_stack_java/archive/master.zip](https://github.com/k33g/n3rd_stack_java/archive/master.zip) vous pouvez très bien l'ouvrir avec la version Community d'IntelliJ.**

>>*Il y aura peut-être N3rd.stack:[Node]*