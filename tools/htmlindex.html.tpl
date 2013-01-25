<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>N3rd.stack:[java]</title>
</head>

<body style="visibility:hidden">
<h1></h1>
<h3></h3>
<div id="form{{model_name}}View">
    <form class="form-inline">
        {{#properties}}
        <input type="text" data-bind="value: {{private_name}}"/><span></span>
        {{/properties}}
        <button class="btn" data-bind="click:{{model_name}}sCtrl.add{{model_name}}">Add Me</button>
    </form>
</div>

<div id="message{{model_name}}View" class="alert alert-info">
    {{#properties}}
    <span data-bind="text: {{private_name}}"></span>
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
        <tbody data-bind="foreach: {{_model_name}}s">
        <tr>
            <td><a data-bind="click:{{model_name}}sCtrl.select, text:_id, attr:{href:_id}"></a></td>
            {{#properties}}
            <td data-bind="text: {{private_name}}"></td>
            {{/properties}}
            <td><a data-bind="click:{{model_name}}sCtrl.selectAndDelete, attr:{href:_id}">Delete</a></td>
        </tr>
        </tbody>
    </table>
</div>

<script data-main="js/app" src="js/vendors/require.js"></script>
<script>
    require(['app/app.{{_model_name}}s'], function (app) {
        app.init();
    });

</script>

<style>
    body {
        margin: 20px;
    }
</style>

</body>
</html>