<%-- 
    Document   : index
    Created on : 5/05/2015, 07:04:54 PM
    Author     : tekolin
--%>

<%@ page contentType="text/html; charset=utf-8" import="operacional.*" errorPage=""%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compi3D</title>
    </head>
    <body>
        <form action="analizador.jsp" id="analizar" method="get">
            <textarea name="gramatica" id='gramatica'  maxlength="500000000000000000000000000000000" rows="25" cols="75" ></textarea><br></br>
            <input type="submit" id="analiza" value="Analizar"/>
        </form>

        <h1>Hello World!</h1>
    </body>
</html>
