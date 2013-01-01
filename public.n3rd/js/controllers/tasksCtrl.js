define([
    'jquery',
    'backbone',
    'knockout',
    'knockback',
    'js/models/task',
    'js/collections/tasks'
],
function($, Backbone, ko, kb, Task, Tasks) {

    var TasksCtrl = Backbone.View.extend({},{//static
        model : new Task({
            /* initialize fields values */
            label : 'label',who : 'who',done : 'done'

        }),
        collection : new Tasks(),


        //methods
        bindViews:function(){
            var self = this;

            TasksCtrl.collection.fetch({success:function(){
                ko.applyBindings({ tasks:kb.collectionObservable( TasksCtrl.collection) }, $("#listTasksView")[0]);
                ko.applyBindings(kb.viewModel( TasksCtrl.model), $("#formTaskView")[0]);
                ko.applyBindings(kb.viewModel( TasksCtrl.model), $("#messageTaskView")[0]);
            }});
        },
        addTask : function() {
            var tmpModel = TasksCtrl.model.clone();
            tmpModel.save({},{
                success:function(){
                    TasksCtrl.collection.fetch({
                        success: function(data){/**/},
                        error: function(err){throw err;}
                    });
                },
                error: function(err){throw err;}
            });
        },
        //called when link is clicked
        select : function(model) {
            var tmpModel = new Task();
            tmpModel.set("_id", model._id());
            tmpModel.fetch({ //GET
                success:function(){console.log(tmpModel);},
                error:function(err){throw err;}
            });
        },
        //called when delete link is clicked
        selectAndDelete : function(model) {
            var tmpModel = new Task();
            tmpModel.set("_id", model._id());

            tmpModel.destroy({ //DELETE
                success:function(){
                    TasksCtrl.collection.fetch({
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

    return TasksCtrl;

});
