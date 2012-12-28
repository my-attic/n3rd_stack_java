import groovy.xml.MarkupBuilder

def sw = new StringWriter()
def html = new MarkupBuilder(sw)

html.html {
    head {
        title ('Humans List')
        script(src: '../js/vendors/jquery-1.8.3.min.js','')

    }
    body {
        h1 ("Groovy XML MarkupBuilder")
        ul {
            input.each { human ->
                li("${human.firstName} - ${human.lastName} (${human.id})")
            }
        }

        script('''
            $("h1").css("color","blue");
            $("ul").css("color","red");
        ''')

    }

}

output = sw.toString();