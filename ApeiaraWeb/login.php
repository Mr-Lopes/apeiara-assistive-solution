<!DOCTYPE html>
<html>
	<head>
		<title>Portal Apeiara - Login</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />		
	</head>
	<body>
		<?php
			require_once("apeiaraBD.inc");
			if (isset ($_POST['usuario'])) //verifica se veio pelo método post
			{
				$user = $_POST['usuario']; //carrego a variável
				$senha = $_POST['senha'];
			}
			else
			{
					$user = $_GET['usuario'];
					$senha = $_GET['senha'];
			}
			
			if ($user != 'null')
			{
				$login = new apeiaraBD;
				$result = $login->BuscarUsu($user,$senha);
				if ($result!= "naoexiste")
				{
					session_start(); 
					$_SESSION['USER']= $result["ID"].";".$result["NAME"].";".$result["EMAIL"].";".$result["PASS"].";".$result["PHONE"].";".$result["CATEGORY"];
						
					header("location:home.php");
				}
				else
				{
					echo ("Usuario nao encontrado!");					
				}
			}
			else
			{
				echo ("Digite Usuário e Senha");
			}
				
		?>
	</body>
</html>