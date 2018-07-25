<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
   <html>
      <head>
      <title>
         Reservation cancelled
      </title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
      </head>
      <body>
     <h:form>
            <div class="container">
      
               <H2>Reservation is now cancelled </H2>
                 <h3> Reservation Id: <h:outputText value="#{cancelController.reservation.reservationId}">
                      </h:outputText>
                </h3>
               <h3> Value of cancelled order: <h:outputText value="#{cancelController.reservation.sum}">
                      <f:convertNumber currencyCode="GBP" type="currency" />
               
               </h:outputText>.
               </h3>

            </div>
            
            </h:form>
      </body>
   </html>
</f:view>