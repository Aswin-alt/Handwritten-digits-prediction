<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	Hello
	<!-- GET -->
  <form name="geturl" method="get" id="geturl" >
  
  		<div class="form-outline mb-4" style="text-align: left;">
			<label class="form-label text-left" for="id">UserID</label> 
			<input type="text" id="id" class="form-control" size="50" name="id"/>
		</div>
  
  	<button type="submit" onclick="get_url()">Get Submit</button>
  </form>
  
  <!-- POST -->
  <form name="posturl" method="post"  action = "usercontroller/v1/users">
  
  	<button type="submit">Post Submit</button>
  </form>
   		
   		
   <!-- PUT -->
  <form name="posturl" method="put"  action = "usercontroller/v1/users">
  
  	<button type="submit">Put Submit</button>
  </form>		
   		
   		
   		
  <script type=text/javascript>
  
  
  
 	function get_url(){
		var id = document.getElementById("id").value;
	    var action = "usercontroller/v1/users/"+id;
	    if(id != ""){
				  document.getElementById("geturl").action = action;
				  document.getElementById("geturl").submit();
			  }
		else{
				  alert("Enter id!");
			  }
	    
	}
 	
  
	</script>
</body>
</html>