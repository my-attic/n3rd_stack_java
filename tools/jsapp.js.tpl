var css = 'css!../../css/bootstrap.css'
,   cssMin = 'css!../../css/bootstrap-responsive.css';

define([
    'jquery',
    'app/controllers/{{_model_name}}sCtrl',
    css,
    cssMin
],
function ($, {{model_name}}sCtrl, css, cssresponsive) {

    return {
        init: function () {
            $(function (){
                $("h1").html("N3rd.stack:[java]");
                $("h3").html("{{model_name}} sample");

                window.{{model_name}}sCtrl = {{model_name}}sCtrl;
                {{model_name}}sCtrl.bindViews();

                $("body").css("visibility","visible");  /*<body style="visibility:hidden">*/

            });
        }
    };

});


