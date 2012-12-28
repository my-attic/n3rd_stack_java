define([
    'backbone',
    'js/models/human'
], function(Backbone, Human) {

    var Humans = Backbone.Collection.extend({
        url :"/humans",
        model : Human
    });

    return Humans;

});
