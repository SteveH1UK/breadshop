<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<f:view>
   <html>
<head>
<title>Reserve your Bread</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
   <h:form id="reserveForm">

      <div class="container">

         <H2>Reserve your bread with us</H2>

         <h:messages />

         <div>

            <h:dataTable id="dt_bread" value="#{reserveController.reservation.shopItems}" var="reservationItem"
               styleClass="table table-striped"
               cellspacing="5" cellpadding="5">
               <f:facet name="header">
                  <h:outputText value="" />
               </f:facet>
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Description" />
                 </f:facet>
                  <h:outputText id="description"  value="#{reservationItem.description}" />
               </h:column>
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Price" />
                  </f:facet>
                  <h:outputText id="unitPrice" value="#{reservationItem.unitPriceMain}" >
                     <f:convertNumber pattern="##,##0.00"/>
                 </h:outputText>
               </h:column>
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Maximum Available" />
                  </f:facet>
                  <h:outputText id="maxAvailable" styleClass="pull-right"  value="#{reservationItem.numberAvailable}">
                  </h:outputText>
               </h:column>
               <h:column>
                  <f:facet name="header">
                     <h:outputText value="Number to Reserve" />
                  </f:facet>
                      <h:inputText id="numberReserved" styleClass="text-right;allownumericwithoutdecimal" value="#{reservationItem.numberReserved}" >
                      </h:inputText>
                </h:column>     
                     <h:column>
                  <f:facet name="header">
                     <h:outputText value="Sub total" />
                  </f:facet>
                      <h:outputText id="subTotal" styleClass="pull-right" value="0" />
                </h:column>         
            </h:dataTable>
                  <h:panelGroup>
                     <div>
                        <h:inputText id="grandTotalSum" value="0" styleClass="hidden"/>
                        <h:outputText id="grandTotal" styleClass="pull-right lead"  value = "0"/>
                        <h:outputText id="grandtotaldescription" styleClass="pull-right lead" value = "Total Reservation Cost £" />
                    </div>
                   </h:panelGroup>
                   <br> 
                   <br>
                  <h:panelGroup>
                     <h:commandButton id="submit" value="submit" action="#{reserveController.reserveBread}"
                        styleClass="btn btn-primary disabled pull-right" />
                  </h:panelGroup>
         </div>
         

      </div>
      
   </h:form>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script type="text/javascript">
 
        $(function () {
        	
        	$('#reserveForm\\:submit').prop("disabled", true);
        	
            var $tblrows = $("#reserveForm\\:dt_bread tbody tr");

            $tblrows.each(function (index) {
                var $tblrow = $(this);


                $tblrow.find('[id*="numberReserved"]').on('change paste keyup', function () {

                    var qty = $tblrow.find('[id*="numberReserved"]').val();
                    var maxQty = $tblrow.find('[id*="maxAvailable"]').text();
                    if ( ( ! (isNaN(qty)) ) && (qty > maxQty) ) {
                    	qty = maxQty;
                    	$tblrow.find('[id*="numberReserved"]').val(qty);
                    } 
                    if ( isNaN(qty) ) {
                    	qty = 0;
                    	$tblrow.find('[id*="numberReserved"]').val(qty);
                    }

                    var price = $tblrow.find('[id*="unitPrice"]').text();

                    var subTotal = parseInt(qty, 10) * parseFloat(price);
                    
                    if ( isNaN(subTotal) || (subTotal < 0) )  {
                    	subTotal = 0;
                    }
                    else {

                        $tblrow.find('[id*="subTotal"]').text(subTotal.toFixed(2));
                        var grandTotal = 0;

                        $('[id*="subTotal"]').each(function () {
                            var stval = parseFloat($(this).text());
                            grandTotal += isNaN(stval) ? 0 : stval; 
                        });

                        $('#reserveForm\\:grandTotal').text(grandTotal.toFixed(2));
                        $('#reserveForm\\:grandTotalSum').val(grandTotal);     
                        if (grandTotal > 0) {
                        	if ( $('#reserveForm\\:submit').hasClass("disabled")) {
                            	$('#reserveForm\\:submit').removeClass('disabled')
                        	    $('#reserveForm\\:submit').prop("disabled", false);
                           }
                        }
                        else {
                        	$('#reserveForm\\:submit').addClass('disabled')
                        	$('#reserveForm\\:submit').prop("disabled", true);
                        }
                    }
                
                });
            });
        });
</script>
</body>
   </html>
</f:view>