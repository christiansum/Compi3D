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
        <style>
            #log_sintactico {
                margin-left:  50px; 
                float: left; 
                width: 45%; 
                text-align: justify;
                 border-right: 5px solid #4BB495  ;
            }
            #log_semantico {
                margin-right:  80px; 
                float: right; 
                width: 45%; 
                text-align: justify;
            } 
            #infor {
                margin-left:  25px;
            }
        </style>
    </head>
    <body>
        <%
            analizador analizador = new analizador();
            if(analizador.procesar(request.getParameter("gramatica"))){
                analizador.crear();
            }
        %>
        <div id="resumen">
            <h2>Proyecto Compiladores</h2>
            <div id="infor">
                <h3>Informacion</h3>
                <%
                    ;
                    Integer[] estados=analizador.getDetLex();
                    out.println("Se han leido "+estados[0]+" signos.</br>");
                    out.println("Se han leido "+estados[1]+" identificadores.</br>");
                    out.println("Se han leido "+estados[2]+" numeros.</br>");
                    out.println("Se han leido "+estados[3]+" lineas.</br>");
                    out.println("Se han leido "+estados[4]+" operadores aritmeticos.</br>");
                    
                %>
            </div>
        </div>
        <div id="main">
            <div id="log_sintactico" style="">
                <h3>Log Sintactico</h3>
            <%
               /* ArrayList estados = new ArrayList();*/
                

                out.println(analizador.getErrorSin());
                //analizador.Hello3d();
                out.println("</br></br></br>");
                /*estados=analizador.getDesign();
                for(int a=0;a<=estados.size()-1;a++ ){
                    out.println(estados[a]+"</br>");
                }*/
            %>
            </div>
            <div id="log_semantico">
                <h3>Log Semantico</h3>
                <%
                    
                    out.println(analizador.getErrorSem());
                %>
            </div>
        </div>
    </body>
</html>
