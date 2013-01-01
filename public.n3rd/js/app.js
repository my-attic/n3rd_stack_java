var css = 'css!../../css/bootstrap.css'
    ,cssMin = 'css!../../css/bootstrap-responsive.css';

define([
    'jquery',
    css,
    cssMin
],
function ($, css, cssresponsive) {

    return {
        init: function () {
            $(function (){

                $("h1").html("N3rd.stack:[java]");
                $("h3").html("With TwitterBootstrap, Backbone, Knockout, KnockBack, Require.js, ...");
                $("body").css("visibility","visible");  /*<body style="visibility:hidden">*/


            });
        }
    };

});




