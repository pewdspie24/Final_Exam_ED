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
    	<h1>Customer List</h1>
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
        <form action="/Final-Exam/addnewbook">
		    <input type="submit" value="Add Book" />
		</form>
        <h1>Comics List</h1>
        <table id="comics" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>ISBN</th>
	                <th>Title</th>
	                <th>Author</th>
	                <th>Years</th>
	                <th>Publisher</th>
	                <th>Summary</th>
	                <th>Artists</th>
	                <th>Series Name</th>
	                <th>Action1</th>
	                <th>Action2</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${listComics}" var="item" varStatus="i">
	                <tr>
	                    <td>${item.ISBN}</td>
	                    <td>${item.title}</td>
	                    <td>${item.author.name}</td>
	                    <td>${item.years}</td>
	                    <td>${item.publisher.name}</td>
	                    <td>${item.summary}</td>
	                    <td>${item.artists}</td>
	                    <td>${item.nameSeries}</td>
	                    <td>
	                    	<form action="editbook">
							  <input type = "hidden" name = "ISBN" value = ${item.ISBN} />
							  <input type="submit" value="Edit">
							</form>
						</td>
						<td>
	                    	<form action="uploadbookitem">
							  <input type = "hidden" name = "ISBN" value = ${item.ISBN} />
							  <input type="submit" value="Upload">
							</form>
						</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
        <h1>Text Book List</h1>
        <table id="textBook" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>ISBN</th>
	                <th>Title</th>
	                <th>Author</th>
	                <th>Years</th>
	                <th>Publisher</th>
	                <th>Summary</th>
	                <th>Editions</th>
	                <th>Volume</th>
	                <th>Num of Pages</th>
	                <th>Action1</th>
	                <th>Action2</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${listTextBook}" var="item" varStatus="i">
	                <tr>
	                    <td>${item.ISBN}</td>
	                    <td>${item.title}</td>
	                    <td>${item.author.name}</td>
	                    <td>${item.years}</td>
	                    <td>${item.publisher.name}</td>
	                    <td>${item.summary}</td>
	                    <td>${item.editions}</td>
	                    <td>${item.volume}</td>
	                    <td>${item.numberOfPages}</td>
	                    <td>
	                    	<form action="editbook">
							  <input type = "hidden" name = "ISBN" value = ${item.ISBN} />
							  <input type="submit" value="Edit">
							</form>
						</td>
						<td>
	                    	<form action="uploadbookitem">
							  <input type = "hidden" name = "ISBN" value = ${item.ISBN} />
							  <input type="submit" value="Upload">
							</form>
						</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
        <h1>Light Novel List</h1>
        <table id="lightNovel" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>ISBN</th>
	                <th>Title</th>
	                <th>Author</th>
	                <th>Years</th>
	                <th>Publisher</th>
	                <th>Summary</th>
	                <th>Editions</th>
	                <th>Volume</th>
	                <th>Translate Language</th>
	                <th>Action1</th>
	                <th>Action2</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${listLightNovel}" var="item" varStatus="i">
	                <tr>
	                    <td>${item.ISBN}</td>
	                    <td>${item.title}</td>
	                    <td>${item.author.name}</td>
	                    <td>${item.years}</td>
	                    <td>${item.publisher.name}</td>
	                    <td>${item.summary}</td>
	                    <td>${item.editions}</td>
	                    <td>${item.volume}</td>
	                    <td>${item.translateLanguage}</td>
	                    <td>
	                    	<form action="editbook">
							  <input type = "hidden" name = "ISBN" value = ${item.ISBN} />
							  <input type="submit" value="Edit">
							</form>
						</td>
						<td>
	                    	<form action="uploadbookitem">
							  <input type = "hidden" name = "ISBN" value = ${item.ISBN} />
							  <input type="submit" value="Upload">
							</form>
						</td>
	                </tr>
	            </c:forEach>
	        </tbody>
        </table>
		<h1>Bookitem List</h1>
		<table id="bookitem" class="table table-striped table-bordered">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>ISBN</th>
	                <th>Title</th>
	                <th>Author</th>
	                <th>Price</th>
	                <th>Discount</th>
	                <th>Uploaded Date</th>
	                <th>InStock Amount</th>
	                <th>Action</th>
	            </tr>
			</thead>
			<tbody>
	            <c:forEach items="${bookitem}" var="item" varStatus="i">
	                <tr>
	                    <td>${item.ID}</td>
	                    <td>${item.book.ISBN}</td>
	                    <td>${item.book.title}</td>
	                    <td>${item.book.author.name}</td>
	                    <td>${item.price}</td>
	                    <td>${item.discount}</td>
	                    <td>${item.uploadDate}</td>
	                    <td>${item.inStock}</td>
	                    <td>
	                    	<form action="editbookitem">
							  <input type = "hidden" name = "ISBN" value = ${item.book.ISBN} />
							  <input type="submit" value="Edit">
							</form>
						</td>
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
	    $(document).ready(function() {
	        $('#bookitem').DataTable();
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