#N3rd.stack.java

Je me suis refusé à utiliser Maven, car je ne le maîtrise pas, et je voulais faire quelque chose de plus "handmade" ou "from scratch"


##Initialisation du projet

###Nouveau Projet

###Préparatifs de base

Ajoutez un fichier README.md à la racine, ça fait "comme les vrais".
Faites vous répertoire doc, où vous allez raconter la vie de votre projet
Un petit répertoire lib dans lequel vous mettrez vos librairies externes (jar) et ça vous permettra ainsi de les distribuer simplement via git (je ne vous ai pas dit, on va utiliser github, je n'explique pas comment ça marche, mais vous trouverez de bonnes explications ici []() ou là []())

>>**PS :** *l'utilisation de Git & GitHub n'est pas obligatoire, c'est uniquement si vous voulez créer votre propre projet*

J'ai mis un fichier README.md aussi dans le répertoire, pour qu'il ne soit pas vide et qu'il soit créé chez GitHub au moment où je vais "pousser" ma structure de projet.


###On "envoie" ça à GitHub


En haut à gauche vous remarquerez la pub pour Mix-It [](). Il faut y aller.

##On fait son marché : Le serveur

J'avais tout d'abord besoin de quelque chose qui fasse office de serveur web et routeur. Mon choix s'est porté sur **Spark** (bookmarqué il y a un moment, c'est @loic_d qui m'en avait parlé à l'époque) . Allons donc faire un tour ici [http://www.sparkjava.com/](http://www.sparkjava.com/).

    //TODO : décrire Spark rapidement

Pour télécharger, c'est ici [http://code.google.com/p/spark-java/downloads/list](http://code.google.com/p/spark-java/downloads/list), j'ai utilisé : [http://spark-java.googlecode.com/files/spark-0.9.9.4-SNAPSHOT.zip](http://spark-java.googlecode.com/files/spark-0.9.9.4-SNAPSHOT.zip).

Vous dézippez, vous coller `spark-0.9.9.4-SNAPSHOT.jar` dans votre répertoire `lib`. Ensuite allons faire référence à notre jar dans les propriétés du projet : click droit sur la branche du projet et choisir **Open Module Settings**.

    //TODO: ici les images

le plugin git va vous poser une question, dites non (clicker sur non/no)

###1ère utilisation de Spark






###Avant de passer à la suite on "pousse" tout ça à GitHub

- Pensez à ajouter vos fichiers à Git : Par exemple sur le répertoire `lib` ou directement sur `spark-0.9.9.4-SNAPSHOT.jar`, pensez à faire un click droit + Git + Add
- Puis à tout commiter  : sur la branche projet : click droit + Git + Commit Directory ...






