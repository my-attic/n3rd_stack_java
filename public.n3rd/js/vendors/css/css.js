/**
 * @license RequireJS css 0.1 - Justin Winslow
 * Available via the MIT or new BSD license.
 */

//The text plugin path must be aliased to 'text' in your require config
define(['text'], {
    load: function (name, require, load, config) {
        require(['text!' + name], function (css) {
            if (typeof window !== "undefined" && window.document) {
                //Store DOM elements
                var head = document.getElementsByTagName('head')[0],
                    style = document.createElement('style');

                //Set it's "type" attribute
                style.type = 'text/css';

                //Check if the element has the styleSheet property
                if (style.styleSheet) {
                    style.styleSheet.cssText = css;
                } else {
                    style.appendChild(document.createTextNode(css));
                }

                //Append new style node to the document
                head.appendChild(style);
            }

            //Tell require the module is done loading
            load(css);
        });
    }
});