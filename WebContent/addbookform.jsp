<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new Book</title>
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
	  <form action="processAdd" method="POST">
	    <div class="title">	
	      <i class="fas fa-pencil-alt"></i> 
	      <h2>Insert New Book Information</h2>
	    </div>
	    <div class="info">
	    	<div class="left-entry">
	      	  <input type="text" name="title" placeholder="Title" required>
		      <input type="text" name="years" placeholder="Years" required>
		      <input type="text" name="nameAuthor" placeholder="Author Name" required>
		      <input type="text" name="shortBio" placeholder="Author ShortBio" required>
		      <input type="text" name="birth" placeholder="Author Birth" required>
		      <input type="text" name="namePublisher" placeholder="Publisher Name" required>
		      <input type="text" name="address" placeholder="Publisher Address" required>
		      <input type="text" name="operateYears" placeholder="Publisher Operated Years" required>
		      <input type="text" name="summary" placeholder="Summary" required>
	      	</div>
	      	<div class="left-entry">
	      <select id="choices" name="type">
	        <option value="LN" selected>Light Novel</option>
	        <option value="CM">Comics</option>
	        <option value="TX">Text Book</option>
	      </select>
	      <input type="text" name="translateLanguage" placeholder="Translated Language" class="LN" style="display: block;" required>
	      <input type="text" name="volume" placeholder="Volume" class="LN" style="display: block;" required>
	      <input type="text" name="editions" placeholder="Editions" class="LN" style="display: block;" required>
	      
	      <input type="text" name="numberOfPages" placeholder="Number Of Pages" class="TX" style="display: none;" required>
	      <input type="text" name="volumeTX" placeholder="Volume" class="TX" style="display: none;" required>
	      <input type="text" name="editionsTX" placeholder="Editions" class="TX" style="display: none;" required>
	      
	      <input type="text" name="nameSeries" placeholder="Series Name" class="CM" style="display: none;" required>
	      <input type="text" name="artists" placeholder="Artist Name" class="CM" style="display: none;" required>
	        </div>
	    </div>
	    <button type="submit" value="Submit">Submit</button>
	  </form>
	</div>
	<script
  src="https://code.jquery.com/jquery-3.6.0.slim.js"
  integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY="
  crossorigin="anonymous"></script>
	<script>
	$("#choices").change(function () {
		  var selected_option = $('#choices').val();

		  if (selected_option === 'CM') {
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
		  if (selected_option === 'LN') {
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
		  if (selected_option === 'TX') {
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
		})
	</script>
	<script>
		function submitForm() {
		   // Get the first form with the name
		   // Usually the form name is not repeated
		   // but duplicate names are possible in HTML
		   // Therefore to work around the issue, enforce the correct index
		   var frm = document.getElementsByName('contact-form')[0];
		   frm.submit(); // Submit the form
		   frm.reset();  // Reset all form data
		   return false; // Prevent page refresh
		}
	</script>
</body>
</html>