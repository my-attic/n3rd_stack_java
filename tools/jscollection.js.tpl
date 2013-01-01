define([
    'backbone',
    'js/models/{{_model_name}}'
],
function(Backbone, {{model_name}}) {

    var {{model_name}}s = Backbone.Collection.extend({
        url :"/{{_model_name}}s",
        model : {{model_name}}
    });

    return {{model_name}}s;

});