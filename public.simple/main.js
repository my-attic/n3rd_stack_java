
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

