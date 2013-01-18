#!/usr/bin/env node
var sys = require('sys')
,	path = require('path')
,	fs = require('fs')
,	mustache = require("../node_modules/mustache")
,	dir = ""
,	dir_src = ""
,	arguments = process.argv
,	model_name = arguments[2]
,   props = JSON.parse(arguments[3])
,   properties = []
,   constructor_args=""
,   fields_args = ""
,	_model_name = model_name.charAt(0).toLowerCase() + model_name.slice(1)
,   front=arguments[4];

if(model_name==null) {
	console.log("no model name");
	process.exit(code=0);	
}

if (path.basename(process.cwd())!=='tools') {
	dir = "tools/";
	
} else {
	dir_src = "../";
}

/*================================================*/
/* FRONT END                                      */
/*================================================*/

/*------------------------------------------------*/
/* Create Java Model                              */
/*------------------------------------------------*/
/*
    ie :
 ./tools/newmodel.js Animal '{"name":"String","remark":"String"}'
 ./tools/newmodel.js Human '{"firstName":"String","lastName":"String"}'
 ./tools/newmodel.js Task '{"label":"String", "who":"String", "done":"boolean"}' n3rd

 */
fs.readFile("./"+dir+"javamodel.java.tpl", 'utf8', function(err, data) {
	if (err) throw err;
	var template = data;

    for(var m in props) {
        //console.log(m,props[m]);
        properties.push({private_name:m,type:props[m],name:m.charAt(0).toUpperCase() + m.slice(1)});
        constructor_args+=props[m]+" "+m+",";
    }
    constructor_args = constructor_args.slice(0,constructor_args.length-1);
    //console.log(properties);
    //console.log(constructor_args);

    var source_code = mustache.to_html(template, {
        model_name:model_name,
        properties:properties,
        constructor_args:constructor_args
    });

	fs.writeFile(dir_src+"src/models/"+model_name+".java", source_code, function(err) {
		if (err) throw err;
		console.log("The file", dir_src+"src/models/"+model_name+".java", "was saved!");
	});
});

/*------------------------------------------------*/
/* Create Java CouchDb Repository                 */
/*------------------------------------------------*/
fs.readFile("./"+dir+"javamodelrepository.java.tpl", 'utf8', function(err, data) {
	if (err) throw err;
	var template = data;
	var source_code = mustache.to_html(template, {
        model_name:model_name,
        _model_name:_model_name,
        properties:properties.filter(function(property){return property.type=="String";})
    });

	fs.writeFile(dir_src+"src/repositories/"+model_name+"s.java", source_code, function(err) {
		if (err) throw err;
		console.log("The file", dir_src+"src/repositories/"+model_name+"s.java", "was saved!");
	});
});

/*------------------------------------------------*/
/* Create Java Controller (CRUD)                  */
/*------------------------------------------------*/
fs.readFile("./"+dir+"javamodelcontroller.java.tpl", 'utf8', function(err, data) {
    if (err) throw err;
    var template = data;
    var source_code = mustache.to_html(template, {model_name:model_name,_model_name:_model_name});

    fs.writeFile(dir_src+"src/controllers/"+model_name+"s.java", source_code, function(err) {
        if (err) throw err;
        console.log("The file", dir_src+"src/controllers/"+model_name+"s.java", "was saved!");
    });
});

/*------------------------------------------------*/
/* Create Java Routes                             */
/*------------------------------------------------*/
fs.readFile("./"+dir+"javamodelroutes.java.tpl", 'utf8', function(err, data) {
    if (err) throw err;
    var template = data;
    var source_code = mustache.to_html(template, {model_name:model_name,_model_name:_model_name});

    fs.writeFile(dir_src+"routes/"+model_name+"s.groovy", source_code, function(err) {
        if (err) throw err;
        console.log("The file", dir_src+"routes/"+model_name+"s.groovy", "was saved!");


    });
});


/*================================================*/
/* FRONT END                                      */
/*================================================*/
if(front=="n3rd") {
    /*------------------------------------------------*/
    /* Create front end backbone model                */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"jsmodel.js.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;
        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name
            //properties:properties
        });

        fs.writeFile(dir_src+"public.n3rd/js/models/"+_model_name+".js", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.n3rd/js/models/"+_model_name+".js", "was saved!");
        });
    });

    /*------------------------------------------------*/
    /* Create front end backbone collection           */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"jscollection.js.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;
        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name
            //properties:properties
        });

        fs.writeFile(dir_src+"public.n3rd/js/collections/"+_model_name+"s.js", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.n3rd/js/collections/"+_model_name+"s.js", "was saved!");
        });
    });

    /*------------------------------------------------*/
    /* Create front end controller (sample)           */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"jscontroller.js.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;

        fields_args="";
        for(var m in props) {
            fields_args+=m + " : '"+ m +"',"
        }
        fields_args = fields_args.slice(0,fields_args.length-1);

        //firstName:"John", lastName:"Doe"

        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name,
            fields_args:fields_args
            //properties:properties
        });

        fs.writeFile(dir_src+"public.n3rd/js/controllers/"+_model_name+"sCtrl.js", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.n3rd/js/controllers/"+_model_name+"sCtrl.js", "was saved!");
        });
    });

    /*------------------------------------------------*/
    /* Create front end app.js (sample)           */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"jsapp.js.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;

        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name
            //properties:properties
        });

        fs.writeFile(dir_src+"public.n3rd/js/app."+_model_name+"s.js", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.n3rd/js/app."+_model_name+"s.js", "was saved!");
        });
    });

    /*------------------------------------------------*/
    /* Create front end index.html (sample)           */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"htmlindex.html.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;

        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name,
            properties:properties
        });

        fs.writeFile(dir_src+"public.n3rd/index."+_model_name+"s.html", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.n3rd/index."+_model_name+"s.html", "was saved!");
        });
    });

}


//TODO
if(front=="angular") {

    //angularjscontroller.js.tpl
    /*------------------------------------------------*/
    /* Create front end controller (sample)           */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"angularjscontroller.js.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;

        fields_args="";
        for(var m in props) {
            fields_args+=m +","
        }
        fields_args = fields_args.slice(0,fields_args.length-1);

        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name,
            properties:properties,
            fields_args:fields_args
        });

        fs.writeFile(dir_src+"public.angular/app/scripts/controllers/"+_model_name+"sCtrl.js", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.angular/app/scripts/controllers/"+_model_name+"sCtrl.js", "was saved!");
        });
    });

    /*------------------------------------------------*/
    /* Create front end app.js (sample)           */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"angularjsapp.js.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;

        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name
            //properties:properties
        });

        fs.writeFile(dir_src+"public.angular/app/scripts/app."+_model_name+"s.js", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.angular/app/scripts/app."+_model_name+"s.js", "was saved!");
        });
    });

    /*------------------------------------------------*/
    /* Create front end html view (sample)            */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"angularhtmlview.html.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;

        fields_args="";
        for(var m in props) {
            fields_args+=m +","
        }
        fields_args = fields_args.slice(0,fields_args.length-1);

        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name,
            properties:properties,
            fields_args:fields_args,
            open:"{{", close:"}}"
        });

        fs.writeFile(dir_src+"public.angular/app/views/main."+_model_name+"s.html", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.angular/app/views/main."+_model_name+"s.html", "was saved!");
        });
    });

    /*------------------------------------------------*/
    /* Create front end index.html (sample)           */
    /*------------------------------------------------*/
    fs.readFile("./"+dir+"angularhtmlindex.html.tpl", 'utf8', function(err, data) {
        if (err) throw err;
        var template = data;

        var source_code = mustache.to_html(template, {
            model_name:model_name,
            _model_name:_model_name,
        });

        fs.writeFile(dir_src+"public.angular/app/index."+_model_name+"s.html", source_code, function(err) {
            if (err) throw err;
            console.log("The file", dir_src+"public.angular/app/index."+_model_name+"s.html", "was saved!");
        });
    });


}