var N3rd = (function () {
    var n3rd = {};

    n3rd.Models = {};
    n3rd.Collections = {};
    n3rd.Views = {};
    n3rd.Controllers = {};
    n3rd.Router = {};

    //https://gist.github.com/2287018
    n3rd.Kind = function() {
        this.initialize && this.initialize.apply(this, arguments);
    };
    n3rd.extend = Backbone.Model.extend;

    n3rd.Controller = function() {
        this.initialize && this.initialize.apply(this, arguments);
    };
    n3rd.Controller.extend = Backbone.Model.extend;

    return n3rd;
}());



