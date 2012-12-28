var jam = {
    "packages": [
        {
            "name": "backbone",
            "location": "js/vendor/backbone",
            "main": "backbone.js"
        },
        {
            "name": "css",
            "location": "js/vendor/css",
            "main": "css.js"
        },
        {
            "name": "jquery",
            "location": "js/vendor/jquery",
            "main": "dist/jquery.js"
        },
        {
            "name": "knockback",
            "location": "js/vendor/knockback",
            "main": "knockback.js"
        },
        {
            "name": "knockout",
            "location": "js/vendor/knockout",
            "main": "knockout.js"
        },
        {
            "name": "text",
            "location": "js/vendor/text",
            "main": "text.js"
        },
        {
            "name": "underscore",
            "location": "js/vendor/underscore",
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
            "location": "js/vendor/backbone",
            "main": "backbone.js"
        },
        {
            "name": "css",
            "location": "js/vendor/css",
            "main": "css.js"
        },
        {
            "name": "jquery",
            "location": "js/vendor/jquery",
            "main": "dist/jquery.js"
        },
        {
            "name": "knockback",
            "location": "js/vendor/knockback",
            "main": "knockback.js"
        },
        {
            "name": "knockout",
            "location": "js/vendor/knockout",
            "main": "knockout.js"
        },
        {
            "name": "text",
            "location": "js/vendor/text",
            "main": "text.js"
        },
        {
            "name": "underscore",
            "location": "js/vendor/underscore",
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
            "location": "js/vendor/backbone",
            "main": "backbone.js"
        },
        {
            "name": "css",
            "location": "js/vendor/css",
            "main": "css.js"
        },
        {
            "name": "jquery",
            "location": "js/vendor/jquery",
            "main": "dist/jquery.js"
        },
        {
            "name": "knockback",
            "location": "js/vendor/knockback",
            "main": "knockback.js"
        },
        {
            "name": "knockout",
            "location": "js/vendor/knockout",
            "main": "knockout.js"
        },
        {
            "name": "text",
            "location": "js/vendor/text",
            "main": "text.js"
        },
        {
            "name": "underscore",
            "location": "js/vendor/underscore",
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