import groovy.xml.MarkupBuilder

def sw = new StringWriter()
def html = new MarkupBuilder(sw)

html.html {
    head {
        title ('Humans List')
        link(href:'../css/bootstrap.css', type:'text/css', rel:'stylesheet')
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

        style(type:"text/css", '''  
            body {
                margin: 20px;
            }
        ''')  

    }

}

output = sw.toString();

