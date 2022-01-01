<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Products | Ecommerce</title><link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <div class="container">
        <div class="navbar">
            <div class="logo">
                <a href="home"><img src="images/logo.png" alt="logo" width="125px"></a>
            </div>
            <nav>
                <ul id="MenuItems">
                    <li><a href="home">Home</a></li>
                    <li><a href="admin">Admin Site</a></li>
                    <li><a href="orderlists">Order Histories</a></li>
                    <li><a href="">Contact</a></li>
                    <c:if test="${customerID}">
                        <li><a href="logout">Logout</a></li>
                    </c:if>
                    <c:if test="${!customerID}">
                        <li><a href="account.html">Account</a></li>
                    </c:if>
                </ul>
            </nav>
            <a href="cart"><img src="images/cart.png" width="30px" height="30px"></a>
            <img src="images/menu.png" class="menu-icon" onclick="menutoggle()">
        </div>
    </div>

    <!-- Cart items details -->
    <div class="small-container">
        <table id="order" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>OrderID</th>
	                <th>Total Prices</th>
	                <th>Total Items</th>
	                <th>Date</th>
	                <th>Payment Method</th>
	                <th>Shipment Method</th>
	                <th>Voucher Method</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${listOrders}" var="item" varStatus="i">
	                <tr>
	                    <td>${item.ID}</td>
	                    <td>${item.cart.totalPrice}</td>
	                    <td>${item.cart.totalQuantity}</td>
	                    <td>${item.date}</td>
	                    <td>${item.payment.names}</td>
	                    <td>${item.shipment.names}</td>
	                    <td>${item.voucher.names}</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
    </div>

    <script>
        var MenuItems = document.getElementById("MenuItems");
        MenuItems.style.maxHeight = "0px";
        function menutoggle() {
            if (MenuItems.style.maxHeight == "0px") {
                MenuItems.style.maxHeight = "200px"
            }
            else {
                MenuItems.style.maxHeight = "0px"
            }
        }
    </script>
    
    <script>
	    $(document).ready(function() {
	        $('#order').DataTable();
	    } );
    </script>
    <script>
        function editbook(ISBN) {
            $.ajax({
                url: 'editbook',
                type: 'POST',
                data: {
                	ISBN,
                },
                complete: function (xhr, textStatus) {
                    console.log(xhr.status);
                }
            });
        }
      </script>

</body>

</html>