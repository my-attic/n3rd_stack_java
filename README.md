#N3rd.stack:[java]

##???

A java-noSQL(*) stack born to create SPA

>>(*): this is not mandatory, but the basic stack works with CouchDB.

##Installation

    git clone https://github.com/k33g/n3rd_stack_java.git

Or you can download [https://github.com/k33g/n3rd_stack_java/archive/master.zip](https://github.com/k33g/n3rd_stack_java/archive/master.zip)

###Prerequisites

- **Maven**
- **Nodejs** and **npm** [http://nodejs.org/](http://nodejs.org/)
- **Grunt** [http://gruntjs.com/](http://gruntjs.com/) *(`npm install -g grunt`)*

>>*Nodejs and Grunt are here only as tools to generate source codes.*

##Playing with it

In the stack directory, type (in a Terminal) :

    mvn jetty:run

>>The **CouchDB** server must be started.

Then, open [http://localhost:9090/](http://localhost:9090/) with your browser.

>>*By default, the root route is `/index.html`. See `public.naked/index.html`*

You can test this :

- [http://localhost:9090/about](http://localhost:9090/about)
- [http://localhost:9090/about.json](http://localhost:9090/about.json)
- [http://localhost:9090/groovy](http://localhost:9090/groovy)


###Create your first services

Open an other Terminal (jetty is still running) and type this command :

    ./tools/newmodel.js Animal '{"name":"String","species":"String"}'

4 java files has been generated and `Router.java` has been updated :

    The file src/models/Animal.java was saved!
    The file src/repositories/Animals.java was saved!
    The file src/controllers/Animals.java was saved!
    The file src/routes/Animals.java was saved!
    The file src/routes/Router.java was saved!

- `src/models/Animal.java` : our model
- `src/repositories/Animals.java` : code to persist model
- `src/controllers/Animals.java` : CRUD REST services
- `src/routes/Animals.java` : routes for calling services
- `src/routes/Router.java` is generated from list of java files in `routes` package

In the same Terminal, type this command :

    mvn compile

>>If all is ok, you can see that **Jetty** restart and reload files

###Test services

####Create records

Refresh [http://localhost:9090/](http://localhost:9090/) and open the browser console, and try this :

    $.ajax({
      type: 'POST',
      url: '/animals',
      data: '{"name":"Wolf", "species":"Dog"}',
      success: function(data) { console.log(data); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });

You have just created "an Animal" in the database, try again :

    $.ajax({
      type: 'POST',
      url: '/animals',
      data: '{"name":"Hello Kitty", "species":"Cat"}',
      success: function(data) { console.log(data); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });

You can verify that database has been created and records has been persisted by opening **CouchDB** administration console : [http://127.0.0.1:5984/_utils/database.html?animalsdb](http://127.0.0.1:5984/_utils/database.html?animalsdb).

####Get All records

Try this (in the browser console) :

    $.ajax({
      type: 'GET',
      url: '/animals',
      success: function(data) { console.log(data); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });

You'll get an array of to javascript objects. Copy somewhere the value of `_id` field of one of those two records (ie: `"54b928c6b9e47d947edd7260dc001bdc"` which is automatically set by **CouchDB**) and the value of `_rev` field (revision field).

####Get one record

Try this (in the browser console) :

    $.ajax({
      type: 'GET',
      url: '/animals/54b928c6b9e47d947edd7260dc001bdc',
      success: function(data) { console.log(data.name, data.species); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });

And you obtain the name and species of the Animal :`Hello Kitty Cat`

####Update the record

Try this (in the browser console) :

    $.ajax({
      type: 'PUT',
      url: '/animals/54b928c6b9e47d947edd7260dc001bdc',
      data: '{"_id":"54b928c6b9e47d947edd7260dc001bdc", "name":"HELLO_KITTY", "species":"Cat", "_rev":"1-f826de25c4e9bb2ad22c184452c33915"}',
      success: function(data) { console.log(data); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });

And recall again the record to verify :

    $.ajax({
      type: 'GET',
      url: '/animals/54b928c6b9e47d947edd7260dc001bdc',
      success: function(data) { console.log(data.name, data.species); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });

And you obtain the name (changed) and species of the Animal :`HELLO_KITTY Cat`

####Delete a record

Try this (in the browser console) :

    $.ajax({
      type: 'DELETE',
      url: '/animals/54b928c6b9e47d947edd7260dc001bdc',
      processData:false,
      success: function(data) { console.log(data); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });

And recall all the records to verify :

    $.ajax({
      type: 'GET',
      url: '/animals',
      success: function(data) { console.log(data); },
      error: function(err) { console.log(err); },
      dataType: "json"
    });


##I want something prettier

Firstly, stop **Jetty**. In file `pom.xml` change :

    <webAppSourceDirectory>${basedir}/public.naked</webAppSourceDirectory>

by :

    <webAppSourceDirectory>${basedir}/public.n3rd</webAppSourceDirectory>

>>**public.naked** is a minimalistic front-end javascript "stack", with only **jQuery**.

>>**public.n3rd** offers more things : **Backbone**, **Underscore**, **jQuery**, **Knockout**, **Knockback**, **RequireJs** and **TwitterBootstrap**

You can now type (in a terminal), this command :

    ./tools/newmodel.js Animal '{"name":"String","species":"String"}' n3rd

Now you have sample javascript and html source codes in `/public.n3rd` :

- `public.n3rd/index.animals.html`
- `public.n3rd/js/app.animals.js`
- `public.n3rd/js/models/animal.js`
- `public.n3rd/js/collection/animals.js`
- `public.n3rd/js/controllers/animalsCtrl.js`

Compile your code :

    mvn compile

Launch **Jetty** :

    mvn jetty:run

And open : [http://localhost:9090/index.animals.html](http://localhost:9090/index.animals.html)

##I want something prettier with AngularJS

Apply the same principle as previously :

In file `pom.xml` :

<webAppSourceDirectory>${basedir}/public.angular/app</webAppSourceDirectory>

You can now type (in a terminal), this command :

    ./tools/newmodel.js Animal '{"name":"String","species":"String"}' angular

Now you have sample javascript and html source codes in `/public.angular` :

- `public.angular/app/index.animals.html`
- `public.angular/app/views/main.animals.html`
- `public.angular/app/scripts/controllers/animalsCtrl.js`
- `public.angular/app/scripts/app.animals.js`

Compile your code :

    mvn compile

Launch **Jetty** :

    mvn jetty:run

And open : [http://localhost:9090/index.animals.html](http://localhost:9090/index.animals.html)

##Kind of "Hot Reloading"

>>**CAUTION** : This is EXPERIMENTAL.

Type this command :

    grunt & mvn jetty:run

Or :

    ./start.sh

As soon as you modify java source code, **Grunt** detects the update and automatically launch `mvn compile`. Then **Jetty** plugin detects update of `.class` files and reload the application.

##Groovy support

- see `src/controllers/GroovyDemo.java`
- see `groovy/about.groovy`

>>*this section is to detail more precisely*

##Next steps

- sessions management
- MongoDB support
- Redis support
