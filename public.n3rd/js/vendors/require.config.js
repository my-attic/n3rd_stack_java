var jam = {
    "packages": [
        {
            "name": "backbone",
            "location": "js/vendors/backbone",
            "main": "backbone.js"
        },
        {
            "name": "css",
            "location": "js/vendors/css",
            "main": "css.js"
        },
        {
            "name": "jquery",
            "location": "js/vendors/jquery",
            "main": "dist/jquery.js"
        },
        {
            "name": "knockback",
            "location": "js/vendors/knockback",
            "main": "knockback.js"
        },
        {
            "name": "knockout",
            "location": "js/vendors/knockout",
            "main": "knockout.js"
        },
        {
            "name": "text",
            "location": "js/vendors/text",
            "main": "text.js"
        },
        {
            "name": "underscore",
            "location": "js/vendors/underscore",
            "main": "underscore.js"
        }
    ],
    "version": "0.2.12",
    "shim": {
        "backbone": {
            "deps": [
                "jquery",
                "underscore"
            ],
            "exports": "Backbone"
        },
        "underscore": {
            "exports": "_"
        }
    }
};

if (typeof require !== "undefined" && require.config) {
    require.config({
    "packages": [
        {
            "name": "backbone",
            "location": "js/vendors/backbone",
            "main": "backbone.js"
        },
        {
            "name": "css",
            "location": "js/vendors/css",
            "main": "css.js"
        },
        {
            "name": "jquery",
            "location": "js/vendors/jquery",
            "main": "dist/jquery.js"
        },
        {
            "name": "knockback",
            "location": "js/vendors/knockback",
            "main": "knockback.js"
        },
        {
            "name": "knockout",
            "location": "js/vendors/knockout",
            "main": "knockout.js"
        },
        {
            "name": "text",
            "location": "js/vendors/text",
            "main": "text.js"
        },
        {
            "name": "underscore",
            "location": "js/vendors/underscore",
            "main": "underscore.js"
        }
    ],
    "shim": {
        "backbone": {
            "deps": [
                "jquery",
                "underscore"
            ],
            "exports": "Backbone"
        },
        "underscore": {
            "exports": "_"
        }
    }
});
}
else {
    var require = {
    "packages": [
        {
            "name": "backbone",
            "location": "js/vendors/backbone",
            "main": "backbone.js"
        },
        {
            "name": "css",
            "location": "js/vendors/css",
            "main": "css.js"
        },
        {
            "name": "jquery",
            "location": "js/vendors/jquery",
            "main": "dist/jquery.js"
        },
        {
            "name": "knockback",
            "location": "js/vendors/knockback",
            "main": "knockback.js"
        },
        {
            "name": "knockout",
            "location": "js/vendors/knockout",
            "main": "knockout.js"
        },
        {
            "name": "text",
            "location": "js/vendors/text",
            "main": "text.js"
        },
        {
            "name": "underscore",
            "location": "js/vendors/underscore",
            "main": "underscore.js"
        }
    ],
    "shim": {
        "backbone": {
            "deps": [
                "jquery",
                "underscore"
            ],
            "exports": "Backbone"
        },
        "underscore": {
            "exports": "_"
        }
    }
};
}

if (typeof exports !== "undefined" && typeof module !== "undefined") {
    module.exports = jam;
}