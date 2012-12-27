N3rd.Controllers.HumansCtrl = N3rd.Kind.extend({

},{//static
    model : new N3rd.Models.Human({firstName:"John", lastName:"Doe"}),
    collection : new N3rd.Collections.Humans(),

    bindViews:function(){
        var self = this;

        N3rd.Controllers.HumansCtrl.collection.fetch({success:function(){
            ko.applyBindings({humans:kb.collectionObservable(N3rd.Controllers.HumansCtrl.collection)}, $("#listHumansView")[0]);
            ko.applyBindings(kb.viewModel(N3rd.Controllers.HumansCtrl.model), $("#formHumanView")[0]);
            ko.applyBindings(kb.viewModel(N3rd.Controllers.HumansCtrl.model), $("#messageHumanView")[0]);
        }});

    },
    addHuman : function() {
        var tmpModel = N3rd.Controllers.HumansCtrl.model.clone();
        tmpModel.save({},{
            success:function(){
                N3rd.Controllers.HumansCtrl.collection.fetch({
                    success: function(data){/**/},
                    error: function(err){throw err;}
                });
            },
            error: function(err){throw err;}
        });
    },
    //called when link is clicked
    select : function(model) {
        var tmpModel = new N3rd.Models.Human();
        tmpModel.set("_id", model._id());
        tmpModel.fetch({ //GET
            success:function(){console.log(tmpModel);},
            error:function(err){throw err;}
        });
    },
    //called when delete link is clicked
    selectAndDelete : function(model) {
        var tmpModel = new N3rd.Models.Human();
        tmpModel.set("_id", model._id());

        tmpModel.destroy({ //DELETE
            success:function(){
                N3rd.Controllers.HumansCtrl.collection.fetch({
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