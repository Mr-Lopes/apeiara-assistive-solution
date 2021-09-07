<?php
        include_once 'apeiaraBD.inc';
        
        // new database
        $con = new apeiaraBD;
		
		session_start(); 

        // define variables and set to empty values
        $name = $email = $password = $category = $phone = " ";

        if ($_SERVER["REQUEST_METHOD"] == "POST") {
           
		    $id   = test_input($_POST["id"]);
		    $name = test_input($_POST["name"]);
            $email = test_input($_POST["email"]);
            $password = test_input($_POST["pass"]);
            $category = test_input($_POST["category"]);
			$phone = test_input($_POST["phone"]);
            
            
			if($id==null){
				$cuidador = $con->CadastrarUsu($name, $email, $password, $phone, $category);	
			} else {
				$lines=$con->AtualizarUsu($id, $name, $email, $password, $phone, $category);
				$cuidador=$id;
				
				if($category=="CUIDADOR"){
					$lines=$con->RemoveCuidando($id);
				}				
			}
			
			
			if($category=="CUIDADOR") {
			   foreach ($_POST['selected_cuidandos'] as $cui)
			   {
				$lines=$con->CadastrarCuidando($cui, $cuidador);
			   }
			}   
			
			$_SESSION['USER']= $id.";".$name.";".$email.";".$password.";".$phone.";".$category;
				
           $location = "/PORTAL/home.php";
		   header("Location: " . "http://" . $_SERVER['HTTP_HOST'] . $location);
           die();
            
        }

        function test_input($data) {
            $data = trim($data);
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }
?>

