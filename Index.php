<?PHP

session_start();
session_destroy();

 ?>



<!DOCTYPE html>

<html>
	<head>
		<title>Portal Apeiara - Autenticação </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<style type="text/css">
		div.conteudo{
			background: url(/PORTAL/img/login2.png) no-repeat;
			margin: 0 auto;
			margin-top:200px;
			width:349px;
			height:319px;
		}
		table#form{
			margin: 0 auto;
			padding-top:150px;
			
		}
		.myButton {
			background-color:#91db69;
			-moz-border-radius:28px;
			-webkit-border-radius:28px;
			border-radius:28px;
			border:1px solid #18ab29;
			display:inline-block;
			cursor:pointer;
			color:#030303;
			font-family:Arial;
			font-size:12px;
			font-weight:bold;
			padding:10px 20px;
			text-decoration:none;
			text-shadow:0px 1px 0px #2f6627;
		}
		.myButton:hover {
			background-color:#44c767;
		}
		.myButton:active {
			position:relative;
			top:1px;
			}
			
		body {
			background: url(/PORTAL/img/bg/grunge.jpg) no-repeat;
		}
		</style>
	</head>
	<body>
		
		
		<div class="conteudo">
			  <table id="form">
				<form action="Login.php" method="post">
					  <tr>
						<td> E-mail </td> <td><input name= "usuario" type="text" size="21"/></td>
					  </tr>
					  <tr>
						<td> Senha </td>  <td><input name="senha" type="password" size="21"/></td>
					  </tr>
					  <tr>
					  <td> <br><br> </td> <td> <br><br> </td>
					  </tr>
					  <tr>
						<td><input class="myButton" type="submit" name="Submit" value=" Entrar "/> &nbsp; </td>
					  
				</form>
				<form action="cadastrar.php">			  
						<td>&nbsp;<input class="myButton" type="submit" name="Submit" value="  Novo Usuario  "/></td>
					  </tr>
				</form>
			</table>
		</div>
	</body>
</html>