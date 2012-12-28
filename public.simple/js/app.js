yepnope({
    load: {
        human               : 'js/models/human.js',
        humans              : 'js/collections/humans.js',
        humansCtrl          : 'js/controllers/humansCtrl.js'
    },
    complete : function () {

        $(function (){
            $("h1").html("N3rd.stack:[java]");

            N3rd.Controllers.HumansCtrl.bindViews();

            $("body").css("visibility","visible");  /*<body style="visibility:hidden">*/

        }); //Fin jQuery
    }
});


