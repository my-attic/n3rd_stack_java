define([
    'jquery',
    'backbone',
    'knockout',
    'knockback',
    'js/models/{{_model_name}}',
    'js/collections/{{_model_name}}s'
], function($, Backbone, ko, kb, {{model_name}}, {{model_name}}s) {

    var {{model_name}}sCtrl = Backbone.View.extend({},{//static
        model : new {{model_name}}({
            /* initialize fields values */
            {{{fields_args}}}

        }),
        collection : new {{model_name}}s(),


        //methods
        bindViews:function(){
            var self = this;

            {{model_name}}sCtrl.collection.fetch({success:function(){
                ko.applyBindings({ {{_model_name}}s:kb.collectionObservable( {{model_name}}sCtrl.collection) }, $("#list{{model_name}}sView")[0]);
                ko.applyBindings(kb.viewModel( {{model_name}}sCtrl.model), $("#form{{model_name}}View")[0]);
                ko.applyBindings(kb.viewModel( {{model_name}}sCtrl.model), $("#message{{model_name}}View")[0]);
            }});
        },
        add{{model_name}} : function() {
            var tmpModel = {{model_name}}sCtrl.model.clone();
            tmpModel.save({},{
                success:function(){
                    {{model_name}}sCtrl.collection.fetch({
                        success: function(data){/**/},
                        error: function(err){throw err;}
                    });
                },
                error: function(err){throw err;}
            });
        },
        //called when link is clicked
        select : function(model) {
            var tmpModel = new {{model_name}}();
            tmpModel.set("_id", model._id());
            tmpModel.fetch({ //GET
                success:function(){console.log(tmpModel);},
                error:function(err){throw err;}
            });
        },
        //called when delete link is clicked
        selectAndDelete : function(model) {
            var tmpModel = new {{model_name}}();
            tmpModel.set("_id", model._id());

            tmpModel.destroy({ //DELETE
                success:function(){
                    {{model_name}}sCtrl.collection.fetch({
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

    return {{model_name}}sCtrl;

});
