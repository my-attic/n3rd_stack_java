#!/usr/bin/env node
var sys = require('sys')
,	path = require('path')
,	fs = require('fs')
,	mustache = require("../node_modules/mustache")
,	dir = ""
,	dir_src = ""
,	arguments = process.argv
,	model_name = arguments[2]
,	_model_name = model_name.charAt(0).toLowerCase() + model_name.slice(1);
//,	template
//,	source_code;

//TODO:add properties

if(model_name==null) {
	console.log("no model name");
	process.exit(code=0);	
}

if (path.basename(process.cwd())!=='tools') {
	dir = "tools/";
	
} else {
	dir_src = "../";
}

fs.readFile("./"+dir+"javamodel.tpl", 'utf8', function(err, data) {
	if (err) throw err;
	var template = data;
	var source_code = mustache.to_html(template, {model_name:model_name});
	//sys.puts(source_code);

	fs.writeFile(dir_src+"src/models/"+model_name+".java", source_code, function(err) {
		if (err) throw err;
		console.log("The file", dir_src+"src/models/"+model_name+".java", "was saved!");
	});
});

fs.readFile("./"+dir+"javamodelrepository.tpl", 'utf8', function(err, data) {
	if (err) throw err;
	var template = data;
	var source_code = mustache.to_html(template, {model_name:model_name,_model_name:_model_name});
	//sys.puts(source_code);

	fs.writeFile(dir_src+"src/repositories/"+model_name+"s.java", source_code, function(err) {
		if (err) throw err;
		console.log("The file", dir_src+"src/repositories/"+model_name+"s.java", "was saved!");
	});
});

fs.readFile("./"+dir+"javamodelcontroller.tpl", 'utf8', function(err, data) {
    if (err) throw err;
    var template = data;
    var source_code = mustache.to_html(template, {model_name:model_name,_model_name:_model_name});
    //sys.puts(source_code);

    fs.writeFile(dir_src+"src/controllers/"+model_name+"s.java", source_code, function(err) {
        if (err) throw err;
        console.log("The file", dir_src+"src/controllers/"+model_name+"s.java", "was saved!");
    });
});

//javamodelroutes.tpl

fs.readFile("./"+dir+"javamodelroutes.tpl", 'utf8', function(err, data) {
    if (err) throw err;
    var template = data;
    var source_code = mustache.to_html(template, {model_name:model_name,_model_name:_model_name});
    //sys.puts(source_code);

    fs.writeFile(dir_src+"src/routes/"+model_name+"s.java", source_code, function(err) {
        if (err) throw err;
        console.log("The file", dir_src+"src/routes/"+model_name+"s.java", "was saved!");
    });
});

