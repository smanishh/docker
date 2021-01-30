<!DOCTYPE html>   
<html lang="en">

<script>
function submit() {
	document.form1.submit();
}
</script>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Spring Boot Web JSP Example</h1>
	</div>

	</div>

<form name="form1" action="/" method="get">
<button type="submit" onclick="submit">Submit</button></form>


	
</body>

</html>
