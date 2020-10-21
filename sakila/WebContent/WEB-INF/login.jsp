<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>http://www.blueb.co.kr</title>
	<script src='https://kit.fontawesome.com/a076d05399.js'></script>
	<link rel="stylesheet" type="text/css" href="/sakila/loginCss.css">
</head>
<body>


<div class="login">
	<h1><i class='fas fa-user-alt' style="font-size:80px;"></i></h1>
    <form method="post">
    	<input type="text" name="staffId" placeholder="Username" required="required" />
        <input type="password" name="password" placeholder="Password" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
        <div>
			Today : ${stats.day} (visitant ${stats.cnt}) --- (All : ${sumCount})
		</div>
    </form>
</div>


</body>
</html>