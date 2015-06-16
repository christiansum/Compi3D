<%-- 
    Document   : analizador
    Created on : 5/05/2015, 07:55:17 PM
    Author     : tekolin
--%>

<%@ page contentType="text/html; charset=utf-8" import="operacional.*" errorPage=""%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    
        <%
           /* ArrayList estados = new ArrayList();*/
            analizador analizador = new analizador();
            analizador.procesar(request.getParameter("gramatica"));
            out.println(analizador.getDesign());
            analizador.reconocimiento(analizador.getDesign());
            analizador.crear();
            
            //analizador.Hello3d();
            out.println("</br></br></br>");
            /*estados=analizador.getDesign();
            for(int a=0;a<=estados.size()-1;a++ ){
                out.println(estados[a]+"</br>");
            }*/
        %>
        
    </body>
</html>
