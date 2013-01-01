define(['backbone'],
function(Backbone) {

    var Task = Backbone.Model.extend({
        urlRoot :"/tasks",
        idAttribute: '_id'
    });

    return Task;
});