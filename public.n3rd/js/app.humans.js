var css = 'css!../../css/bootstrap.css'
    ,cssMin = 'css!../../css/bootstrap-responsive.css';

define([
    'jquery',
    'js/controllers/humansCtrl',
    css,
    cssMin
],
function ($, HumansCtrl, css, cssresponsive) {

    return {
        init: function () {
            $(function (){
                $("h1").html("N3rd.stack:[java]");
                $("h3").html("With TwitterBootstrap & Require.js");

                window.HumansCtrl = HumansCtrl;
                HumansCtrl.bindViews();

                $("body").css("visibility","visible");  /*<body style="visibility:hidden">*/

            });
        }
    };

});


