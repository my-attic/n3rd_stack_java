var css = 'css!../../css/bootstrap.css'
    ,cssMin = 'css!../../css/bootstrap-responsive.css';

define([
    'jquery',
    'js/controllers/tasksCtrl',
    css,
    cssMin
],
function ($, TasksCtrl, css, cssresponsive) {

    return {
        init: function () {
            $(function (){
                $("h1").html("N3rd.stack:[java]");
                $("h3").html("Task sample");

                window.TasksCtrl = TasksCtrl;
                TasksCtrl.bindViews();

                $("body").css("visibility","visible");  /*<body style="visibility:hidden">*/

            });
        }
    };

});


