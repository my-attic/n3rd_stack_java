define(['backbone'],
function(Backbone) {

    var Human = Backbone.Model.extend({
        urlRoot :"/humans",
        idAttribute: '_id'
    });

    return Human;
});


