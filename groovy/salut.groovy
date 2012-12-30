output = """
<!DOCTYPE html>
<html>
<body>
    <h1>Salut, ${input}! C'est la N3rd.stack:[java] ... </h1>
<!--<script src="../js/vendors/jquery/dist/jquery.js"></script>-->
<script src="../js/vendors/require.js"></script>
<script>
    require(['jquery'], function (\$) {
    \$("h1").css("color","blue");
    });
</script>
</body>
</html>
"""

