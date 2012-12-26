var N3rd = (function (n3rd) {

    n3rd.Models.Human = Backbone.Model.extend({
        urlRoot :"/humans",
        idAttribute: '_id'
    });

    return n3rd;
}(N3rd));