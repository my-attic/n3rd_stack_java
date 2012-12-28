var N3rd = (function (n3rd) {
    /*_id est une spécificité liée à CouchDB (ou MongoDb) */
    n3rd.Models.Human = Backbone.Model.extend({
        urlRoot :"/humans",
        idAttribute: '_id'
    });

    return n3rd;
}(N3rd));