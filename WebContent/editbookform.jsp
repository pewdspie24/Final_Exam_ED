<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Book</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
<style>
  html, body {
  min-height: 100%;
  }
  body, div, form, input, select, p { 
  padding: 0;
  margin: 0;
  outline: none;
  font-family: Roboto, Arial, sans-serif;
  font-size: 16px;
  color: #eee;
  }
  body {
  background: url("/uploads/media/default/0001/01/b5edc1bad4dc8c20291c8394527cb2c5b43ee13c.jpeg") no-repeat center;
  background-size: cover;
  }
  h1, h2 {
  text-transform: uppercase;
  font-weight: 400;
  }
  h2 {
  margin: 0 0 0 8px;
  }
  .main-block {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  padding: 25px;
  background: rgba(0, 0, 0, 0.5); 
  }
  .left-part, form {
  padding: 25px;
  }
  .left-part {
  text-align: center;
  }
  .fa-graduation-cap {
  font-size: 72px;
  }
  form {
  background: rgba(0, 0, 0, 0.7); 
  }
  .title {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  }
  .info {
  display: flex;
  flex-direction: row;
  }
  input, select {
  padding: 5px;
  margin-bottom: 30px;
  background: transparent;
  border: none;
  border-bottom: 1px solid #eee;
  }
  input::placeholder {
  color: #eee;
  }
  option:focus {
  border: none;
  }
  option {
  background: black; 
  border: none;
  }
  .checkbox input {
  margin: 0 10px 0 0;
  vertical-align: middle;
  }
  .checkbox a {
  color: #26a9e0;
  }
  .checkbox a:hover {
  color: #85d6de;
  }
  .btn-item, button {
  padding: 10px 5px;
  margin-top: 20px;
  border-radius: 5px; 
  border: none;
  background: #26a9e0; 
  text-decoration: none;
  font-size: 15px;
  font-weight: 400;
  color: #fff;
  }
  .btn-item {
  display: inline-block;
  margin: 20px 5px 0;
  }
  button {
  width: 100%;
  }
  button:hover, .btn-item:hover {
  background: #85d6de;
  }
  @media (min-width: 568px) {
  html, body {
  height: 100%;
  }
  .main-block {
  flex-direction: row;
  height: calc(100% - 50px);
  }
  .left-part, form {
  flex: 1;
  height: auto;
  }
  .left-entry {
    flex: 50%;'
	}
	.right-entry {
    width: 50%;
    float: right;
	}
  }
</style>
</head>
<body>
	<div class="main-block">
	  <form action="processedit" method="POST">
	    <div class="title">	
	      <i class="fas fa-pencil-alt"></i> 
	      <h2>Edit Book Information</h2>
	    </div>
	    <div class="info">
	    	<div class="left-entry">
	    	  <p>ISBN</p><input type="text" name="ISBN" placeholder="ISBN" required value=${ISBN} readonly>
	      	  <p>Title</p><input type="text" name="title" placeholder="Title" required value=${title}>
		      <p>Years</p><input type="text" name="years" placeholder="Years" required value=${years}>
		      <p>Author Name</p><input type="text" name="nameAuthor" placeholder="Author Name" required value=${nameAuthor}> 
		      <p>Author ShortBio</p><input type="text" name="shortBio" placeholder="Author ShortBio" required value=${shortBio}>
		      <p>Author Birth</p><input type="text" name="birth" placeholder="Author Birth" required value=${birth}>
		      <p>Publisher Name</p><input type="text" name="namePublisher" placeholder="Publisher Name" required value=${namePublisher}>
		      <p>Publisher Address</p><input type="text" name="address" placeholder="Publisher Address" required value=${address}>
		      <p>Publisher Operated Years</p><input type="text" name="operateYears" placeholder="Publisher Operated Years" required value=${operateYears}>
		      <p>Summary</p><input type="text" name="summary" placeholder="Summary" required value=${summary}>
	      	</div>
	      	<div class="left-entry">
	      <input id="booktype" type="text" name="type" placeholder="Type" required value=${type} readonly>
	      <input type="text" name="ID" placeholder="ID" required value=${ID} readonly>
	      <input type="text" name="translateLanguage" placeholder="Translated Language" class="LN" style="display: none;" required value=${translateLanguage}>
	      <input type="text" name="volume" placeholder="Volume" class="LN" style="display: none;" required value=${volume}>
	      <input type="text" name="editions" placeholder="Editions" class="LN" style="display: none;" required value=${editions}>
	      
	      <input type="text" name="numberOfPages" placeholder="Number Of Pages" class="TX" style="display: none;" required value=${numberOfPages}>
	      <input type="text" name="volumeTX" placeholder="Volume" class="TX" style="display: none;" required value=${volumeTX}>
	      <input type="text" name="editionsTX" placeholder="Editions" class="TX" style="display: none;" required value=${editionsTX}>
	      
	      <input type="text" name="nameSeries" placeholder="Series Name" class="CM" style="display: none;" required value=${nameSeries}>
	      <input type="text" name="artists" placeholder="Artist Name" class="CM" style="display: none;" required value=${artists}>
	        </div>
	    </div>
	    <button type="submit" value="Submit">Submit</button>
	  </form>
	</div>
	<script
  src="https://code.jquery.com/jquery-3.6.0.slim.js"
  integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY="
  crossorigin="anonymous"></script>
	<script defer>
		  var myInput = document.getElementById("booktype").value;
		  console.log(myInput);
		  if (myInput === "Comics") {
		  [].forEach.call(document.querySelectorAll('.CM'), function (el) {
			  el.required = true;
			  el.style.display = 'block';
			});
		  [].forEach.call(document.querySelectorAll('.TX'), function (el) {
			  el.style.display = 'none';
			  el.required = false;
			});
		  [].forEach.call(document.querySelectorAll('.LN'), function (el) {
			  el.style.display = 'none';
			  el.required = false;
			});
		  }
		  if (myInput === "LightNovel") {
			  [].forEach.call(document.querySelectorAll('.CM'), function (el) {
				  el.style.display = 'none';
				  el.required = false;
				});
			  [].forEach.call(document.querySelectorAll('.TX'), function (el) {
				  el.style.display = 'none';
				  el.required = false;
				});
			  [].forEach.call(document.querySelectorAll('.LN'), function (el) {
				  el.style.display = 'block';
				  el.required = true;
				});
		  }
		  if (myInput === "TextBook") {
			  [].forEach.call(document.querySelectorAll('.CM'), function (el) {
				  el.style.display = 'none';
				  el.required = false;
				});
			  [].forEach.call(document.querySelectorAll('.TX'), function (el) {
				  el.style.display = 'block';
				  el.required = true;
				});
			  [].forEach.call(document.querySelectorAll('.LN'), function (el) {
				  el.style.display = 'none';
				  el.required = false;
				});
		  }
	</script>
</body>
</html>