import groovy.xml.MarkupBuilder

def sw = new StringWriter()
def html = new MarkupBuilder(sw)

html.html {
    head {
        title ('Humans List')
        script(src: '../js/vendors/require.js','')
    }
    body {
        h1 ("Groovy XML MarkupBuilder ...")
        ul {
            input.each { human ->
                li("${human.firstName} - ${human.lastName} (${human.id})")
            }
        }

        script('''
            require(['jquery'], function ($) {
                $("h1").css("color","blue");
                $("ul").css("color","green");
            });
        ''')

    }

}

output = sw.toString();

