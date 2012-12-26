var N3rd = (function (n3rd) {

    n3rd.Collections.Humans = Backbone.Collection.extend({
        url :"/humans",
        model : N3rd.Models.Human
    });

    return n3rd;
}(N3rd));