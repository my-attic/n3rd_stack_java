define([
    'jquery',
    'backbone',
    'knockout',
    'knockback',
    'js/models/human',
    'js/collections/humans'
], function($, Backbone, ko, kb, Human, Humans) {

    var HumansCtrl = Backbone.View.extend({},{//static
        model : new Human({firstName:"John", lastName:"Doe"}),
        collection : new Humans(),

        bindViews:function(){
            var self = this;

            HumansCtrl.collection.fetch({success:function(){
                ko.applyBindings({humans:kb.collectionObservable(HumansCtrl.collection)}, $("#listHumansView")[0]);
                ko.applyBindings(kb.viewModel(HumansCtrl.model), $("#formHumanView")[0]);
                ko.applyBindings(kb.viewModel(HumansCtrl.model), $("#messageHumanView")[0]);
            }});
        },
        addHuman : function() {
            var tmpModel = HumansCtrl.model.clone();
            tmpModel.save({},{
                success:function(){
                    HumansCtrl.collection.fetch({
                        success: function(data){/**/},
                        error: function(err){throw err;}
                    });
                },
                error: function(err){throw err;}
            });
        },
        //called when link is clicked
        select : function(model) {
            var tmpModel = new Human();
            tmpModel.set("_id", model._id());
            tmpModel.fetch({ //GET
                success:function(){console.log(tmpModel);},
                error:function(err){throw err;}
            });
        },
        //called when delete link is clicked
        selectAndDelete : function(model) {
            var tmpModel = new Human();
            tmpModel.set("_id", model._id());

            tmpModel.destroy({ //DELETE
                success:function(){
                    HumansCtrl.collection.fetch({
                        success: function(data){
                            console.log("Collections fetched after delete : ", data);
                        },
                        error: function(err){throw err;}
                    });
                },
                error:function(err){throw err;}
            });
        }

    });

    return HumansCtrl;

});
