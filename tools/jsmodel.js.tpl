define(['backbone'],
    function(Backbone) {

        var {{model_name}} = Backbone.Model.extend({
        urlRoot :"/{{_model_name}}s",
        idAttribute: '_id'
    });

    return {{model_name}};
});