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
                    <li><a href="products.html">Products</a></li>
                    <li><a href="">About</a></li>
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
        <table id="customer" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>CustomerID</th>
	                <th>UserID</th>
	                <th>Customer Username</th>
	                <th>Customer Firstname</th>
	                <th>Customer AccountNumber</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${customers}" var="item" varStatus="i">
	                <tr>
	                    <td>${item.ID}</td>
	                    <td>${item.user.ID}</td>
	                    <td>${item.user.account.username}</td>
	                    <td>${item.user.fullname.firstname}</td>
	                    <td>${item.accountNum}</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
        <table id="comics" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>ISBN</th>
	                <th>Title</th>
	                <th>Artists Name</th>
	                <th>Author Name</th>
	                <th>Years</th>
	                <th>Series Name</th>
	                <th>Publisher Name</th>
	                <th>Summary</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${listComics}" var="item" varStatus="i">
	                <tr>
	                    <td>${item.ISBN}</td>
	                    <td>${item.title}</td>
	                    <td>${item.artists}</td>
	                    <td>${item.author}</td>
	                    <td>${item.years}</td>
	                    <td>${item.nameSeries}</td>
	                    <td>${item.publisher.name}</td>
	                    <td>${item.summary}</td>
	                    <td>
	                    	<div class="btn btn-primary text-center" onclick="addbook(${item.ISBN})">Edit</div>
						</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
        <table id="textBook" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>Product</th>
	                <th>Quantity</th>
	                <th>Subtotal</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${bookItems}" var="item" varStatus="i">
	                <tr>
	                    <td>
	                        <div class="cart-info">
	                            <img src="images/book.jpg">
	                            <div>
	                                <p>${item.book.title}</p>
	                                <small>${item.price}</small>
	                                <br>
	                                <a href="">Remove</a>
	                            </div>
	                        </div>
	                    </td>
	                    <td><input type="number" value="${bookQuantity[i.index]}"></td>
	                    <td>${bookPrice[i.index]}d</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
        <table id="lightNovel" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>Product</th>
	                <th>Quantity</th>
	                <th>Subtotal</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${bookItems}" var="item" varStatus="i">
	                <tr>
	                    <td>
	                        <div class="cart-info">
	                            <img src="images/book.jpg">
	                            <div>
	                                <p>${item.book.title}</p>
	                                <small>${item.price}</small>
	                                <br>
	                                <a href="">Remove</a>
	                            </div>
	                        </div>
	                    </td>
	                    <td><input type="number" value="${bookQuantity[i.index]}"></td>
	                    <td>${bookPrice[i.index]}d</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
        <form action="/addnewbook">
		    <input type="submit" value="Add Book" />
		</form>
    </div>
    <div id="footer" style="width: 100%; height: 550px; background-color: #e8e8e8; margin-top: 30px;">
        <h3 style="font-size: 40px; text-align: center; padding: 50px 0;">About</h3>
        <div class="row">
            <div class="col-6">
                <ul class="list-group-flush">
                    <li class="list-group-item">
                        <h4>Liên hệ</h4>
                    </li>
                    <li class="list-group-item"><i class="fas fa-map-marker"></i> Địa chỉ: PTIT, Hà Nội</li>
                    <li class="list-group-item"><i class="fas fa-envelope"></i> Email: abcabcbabc@gmail.com</li>
                    <li class="list-group-item"><i class="fas fa-mobile-alt"></i> SĐT: 0123456879</li>
                    <li class="list-group-item"><i class="fas fa-phone"></i> Đường dây nóng: 19000099</li>
                </ul>
            </div>

            <div class="col-6">
                <ul class="list-group-flush">
                    <li class="list-group-item">
                        <h4>Theo dõi chúng tôi trên</h4>
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
    
    <script>
	    $(document).ready(function() {
	        $('#customer').DataTable();
	    } );
    </script>
    <script>
	    $(document).ready(function() {
	        $('#comics').DataTable();
	    } );
    </script>
    <script>
	    $(document).ready(function() {
	        $('#textBook').DataTable();
	    } );
    </script>
    <script>
	    $(document).ready(function() {
	        $('#lightNovel').DataTable();
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