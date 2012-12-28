css
===

Plugin for Require.js to add modular stylesheets as dependencies.

#### How it works
This plugin uses the text.js plugin which should be aliased in Require to "text". It allows you to add your stylesheets as a dependency in the define(). In development, stylesheets that are added as dependencies will be loaded and appended to the DOM in a &lt;style&gt; tag. Then, when you are ready to compile your assets the CSS dependencies can be included as part of your build.

#### Example

define([css!path/to/my.css], function(css){
	//Be awesome.	
});

Note: As of now the passed argument from the css plugin is just the stringified contents of the css file. This isn't really of any use in your modules but you can assign the argument slot a name - like the example above - for clarity.