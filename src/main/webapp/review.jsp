<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
   <html>
<head>
<title>Review your order</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
   <h:form id="reviewForm">

<div class="container">

         <H2>Review your reservation</H2>
         
         <H4><h:outputText value="Reservation ID: " />
         <h:outputText value="#{reviewController.reservation.reservationId}" />
         </br>
         <h:outputText value="Reservation Status: " />
         <h:outputText value="#{reviewController.reservation.status}" /></H4>
            

         <h:dataTable value="#{reviewController.reservation.shopItems}" var="reservationItem"  styleClass="table table-striped"
               cellspacing="5" cellpadding="5">
            <h:column>
               <f:facet name="header">
                  <h:outputText value="Description" />
               </f:facet>
               <h:outputText id="Description" value="#{reservationItem.description}" />
            </h:column>
            <h:column>
               <f:facet name="header">
                  <h:outputText value="Price" />
               </f:facet>
                 <h:outputText id="unitPrice" value="#{reservationItem.unitPriceMain}">
                     <f:convertNumber currencyCode="GBP" type="currency" />
                 </h:outputText>
            </h:column>
            <h:column>
               <f:facet name="header">
                  <h:outputText value="Maximum Available" />
               </f:facet>
               <h:outputText value="#{reservationItem.numberAvailable}">
               </h:outputText>
            </h:column>
            <h:column>
               <f:facet name="header">
                  <h:outputText value="Number to Reserve" />
               </f:facet>
               <h:outputText value="#{reservationItem.numberReserved}" />
            </h:column>
         </h:dataTable>
         <div class="row-xs-1 lead text-center" style="font-weight: bold;" >
               Total Cost of your reservation: 
               <h:outputText value="#{reviewController.reservation.sum}">
                    <f:convertNumber currencyCode="GBP" type="currency" />
               </h:outputText>
         </div>         

         <div class="row-xs-1">

            <h:commandButton id="cancelCommand" type="submit" value="Cancel Reservation" styleClass="btn btn-danger"
               action="#{reviewController.cancel}"  />
         </div>
      </div>
 

   </h:form>
</body>
   </html>
</f:view>