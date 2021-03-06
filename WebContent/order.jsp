<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Products | Ecommerce</title><link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <div class="container">
        <div class="navbar">
            <div class="logo">
                <a href=""><img src="images/logo.png" alt="logo" width="125px"></a>
            </div>
            <nav>
                <ul id="MenuItems">
                    <li><a href="home">Home</a></li>
                    <li><a href="admin">Admin Site</a></li>
                    <li><a href="orderlists">Order Histories</a></li>
                    <li><a href="">Contact</a></li>
                    <li><a href="account.html">Account</a></li>
                </ul>
            </nav>
            <a href="cart"><img src="images/cart.png" width="30px" height="30px"></a>
            <img src="images/menu.png" class="menu-icon" onclick="menutoggle()">
        </div>
    </div>

    <!-- Cart items details -->
    <div class="small-container cart-page">
        <table>
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Subtotal</th>
            </tr>
            <c:forEach items="${bookItems}" var="item" varStatus="i">
                <tr>
                    <td>
                        <div class="cart-info">
                            <img src="images/book.jpg">
                            <div>
                                <p>${item.book.title}</p>
                                <small>${item.price}</small>
                            </div>
                        </div>
                    </td>
                    <td><input type="number" value="${bookQuantity[i.index]}"></td>
                    <td>${bookPrice[i.index]}d</td>
                </tr>
            </c:forEach>
        </table>
        <div class="total-price">
            <table>
                <tr>
                    <td>Subtotal</td>
                    <td>${totalPrice}d</td>
                </tr>
                <tr>
                    <td>Shipment</td>
                    <td>${shipmentPrice}d</td>
                </tr>
                <tr>
                    <td>Voucher Discount</td>
                    <td>${voucherDiscount}d</td>
                </tr>
                <tr>
                    <td>Discount</td>
                    <td>${discount}d</td>
                </tr>
                <tr>
                    <td>Total</td>
                    <td>${total}d</td>
                </tr>
                
            </table>
        </div>
    </div>
    <div id="footer" style="width: 100%; height: 550px; background-color: #e8e8e8; margin-top: 30px;">
        <h3 style="font-size: 40px; text-align: center; padding: 50px 0;">About</h3>
        <div class="row">
            <div class="col-6">
                <ul class="list-group-flush">
                    <li class="list-group-item">
                        <h4>Li??n h???</h4>
                    </li>
                    <li class="list-group-item"><i class="fas fa-map-marker"></i> ?????a ch???: PTIT, H?? N???i</li>
                    <li class="list-group-item"><i class="fas fa-envelope"></i> Email: abcabcbabc@gmail.com</li>
                    <li class="list-group-item"><i class="fas fa-mobile-alt"></i> S??T: 0123456879</li>
                    <li class="list-group-item"><i class="fas fa-phone"></i> ???????ng d??y n??ng: 19000099</li>
                </ul>
            </div>

            <div class="col-6">
                <ul class="list-group-flush">
                    <li class="list-group-item">
                        <h4>Theo d??i ch??ng t??i tr??n</h4>
                    </li>
                    <li class="list-group-item"><i class="fab fa-facebook-square"></i> Facebook</li>
                    <li class="list-group-item"><i class="fab fa-instagram"></i> Instagram</li>
                    <li class="list-group-item"><i class="fab fa-twitter-square"></i> Twiter</li>
                </ul>
            </div>
        </div>
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

</body>

</html>