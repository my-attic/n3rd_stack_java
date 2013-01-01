import groovy.xml.MarkupBuilder

def sw = new StringWriter()
def html = new MarkupBuilder(sw)

html.html {
    head {
        title ('N3rd.stack:[java]')
    }
    body {
        h1 (h1Title)
        h2 (h2Title)

        style(type:"text/css", '''  
            body {
                margin: 20px;
            }
        ''')
    }

}

output = sw.toString();

