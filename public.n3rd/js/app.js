// For any third party dependencies, like jQuery, place them in the lib folder (javascripts/vendors).

// Configure loading modules from the lib directory,
// except for 'app' ones, which are in a sibling
// directory.
requirejs.config({
    baseUrl: 'js/vendors',
    paths: {
        app: '../app'
    },

    shim: {

        underscore: {
            exports: '_'
        },
        backbone: {
            deps: ["underscore", "jquery"],
            exports: "Backbone"
        }
    }
});

// Start loading the main app file. Put all of
// your application logic in there.
//requirejs(['app/main']);