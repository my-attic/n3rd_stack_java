<h1>N3rd.stack:[java]</h1>
<h3>{{model_name}} sample</h3>
<div id="form{{model_name}}View">
    <form class="form-inline">

        {{#properties}}
        <input type="text" ng-model="{{private_name}}"/><span></span>
        {{/properties}}

        <button class="btn" ng-click="add{{model_name}}({{fields_args}})">Add Me</button>
    </form>
</div>

<div id="message{{model_name}}View" class="alert alert-info">
    {{#properties}}
    <span ng-bind="selected{{model_name}}.{{private_name}}"></span>
    {{/properties}}
</div>

<div id="list{{model_name}}sView">
    <table class="table table-striped table-bordered table-hover">
        <thead>
            <tr>
                <th>id</th>
                {{#properties}}
                <th>{{name}}</th>
                {{/properties}}
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <tr ng-repeat="{{_model_name}} in {{_model_name}}s">

            <td><a ng-click="select({{_model_name}})">{{open}}{{_model_name}}._id{{close}};</a></td>

            {{#properties}}
            <td ng-bind="{{_model_name}}.{{private_name}}"></td>
            {{/properties}}

            <td><a ng-click="selectAndDelete({{_model_name}})">Delete</a></td>
        </tr>
        </tbody>
    </table>
</div>
