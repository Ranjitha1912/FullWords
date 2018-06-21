<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
h1 {
	text-align: center;
	font-size: 50px;
	font-family: "Times New Roman", Times, serif;
}

body {
	background-color: #cfe7f1;
}

img {
	border-radius: 50%;
	position: absolute;
	right: 0px;
	top: 0px;
	z-index: -1;
}

div.username {
	position: fixed;
	right: 10%;
	top: 5%;
	width: 300px;
}

div.logout {
	position: fixed;
	right: 50px;
	top: 100px;
}

input[type="text"] {
	width: 40em;
	line-height: 5em;
}

input[type=submit] {
	width: 544px;
	background-color: #1a0f00;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.button {
	width: 100px;
	background-color: #1a0f00;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

div.mainform {
	position: fixed;
	left: 30%;
	top: 20%;
	width: 300px;
}
</style>
<h1>Full Words</h1>
<script type="text/javascript">
	function check() {
		var word = document.forms["f3"]["Word"].value;
		if (word == "") {
			alert("Enter a word");
		} else {
			var word1 = word;
		}
		var meaning = document.forms["f3"]["Meaning"].value;
		if (meaning == "") {
			alert("Enter the meaning");
		} else {
			var meaning1 = meaning;
		}
		var source = document.forms["f3"]["Source"].value;
		if (source == "") {
			alert("Enter the source");
		} else {
			var source1 = source;
		}

		var xmlHttp = new XMLHttpRequest();

		xmlHttp.onreadystatechange = function() {

			if (xmlHttp.status == 200 && xmlHttp.readyState == 4)

			{

				alert(this.responseText);

			}

		};

		xmlHttp.open("POST", "/FullWord", false);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send("word2=" + word1 + "&meaning2=" + meaning1 + "&source2="
				+ source1);

	}
</script>
</head>
<title>FullWords</title>

<body>
	<%
		String userInfo1[] = null;
		try {

			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			session = request.getSession(false);
			String userInfo = (String) session.getAttribute("userinfo");
			userInfo1 = userInfo.split(",");
		} catch (Exception e) {
			out.println("Invalid input");
		}
	%>
	<div class="username">
		<p style='text-align: right'>

			<b><%=userInfo1[1]%></b>
	</div>
	<img src=<%=userInfo1[2]%> height="100" width="100" />
	</p>

	<form name='f2' action='../Logout' method='post'>
		<div class="logout">
			<p style='text-align: right'>
				<button type='submit' class='button'>Logout</button>
			</p>
		</div>
	</form>
	<div class="mainform">
		<form name='f3' onSubmit="check()">
			<p style='text-align: left'>
				<b>Word</b><br> <input type="text" name="Word"><br>
				<br> <b>Meaning</b><br> <input type="text" name="Meaning"><br>
				<br> <b>Source</b><br> <input type="text" name="Source"><br>
				<br> <input name="Submit" type="Submit" value="Submit">
			</p>
		</form>
	</div>
</body>
</html>