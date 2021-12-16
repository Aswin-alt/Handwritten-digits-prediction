<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Customer</title>
</head>
<body>
	<h2 style = "text-align : center;">Create new Customer</h2>
	<br><br>
	
	<form class="container w-25 text-center" style="margin-top: 100px;" action="createcustomercontroller" method="post">
		
		<div class="form-outline mb-4" style="text-align: left;">
			<label class="form-label text-left" for="name">Customer Name</label> 
			<input type="text" id="name" class="form-control" size="50" name="name" Required/>
		</div>  
		
		<div class="form-outline mb-4" style="text-align: left;">
			<label class="form-label text-left" for="email">Email Id</label> 
			<input type="email" id="email" class="form-control" size="50" name="email" Required/>
		</div>  
		
		<div class="form-outline mb-4" style="text-align: left;">
			<label class="form-label text-left" for="phone">Phone No.</label> 
			<input type="number" id="phone" class="form-control" size="50" name="phone" Required/>
		</div>  
		  
		<button type="submit">Submit</button><br>
	</form>
	
	<form class="container w-25 text-center" style="margin-top: 100px;" action="/AswinSuperMarket/index.jsp" method="post">
		<button type="submit" name="offset" value="0">Back</button>
	</form>
	
</body>
</html>