<?PHP

require_once("apeiaraBD.inc");
$pos = new apeiaraBD;

session_start();

if(!array_key_exists('USER',$_SESSION) && empty($_SESSION['USER'])) {
	$user="null";
 }else{
	$user = $_SESSION['USER'];
	$aux = split(";", $user);

	if($aux[5]=="CUIDADOR") {
		$cuidandos = $pos->getCuidandos($aux[0]);
	} else {
		$cuidandos=null;
	}
  }
 ?>


<!DOCTYPE  html>
<html>
<head>

    
        <meta charset="utf-8">
        <title>Portal Apeiara - Apoio ao Deficiente Visual</title>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/social-icons.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/sky-forms.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/custom.js"></script>
        <script type="text/javascript" src="js/slider.js"></script>
        <script src="js/slides/source/slides.min.jquery.js"></script>
        <script src="js/quicksand.js"></script>		
        <link rel="stylesheet" media="screen" href="css/superfish.css" /> 
        <script type="text/javascript" src="js/superfish-1.4.8/js/hoverIntent.js"></script>
        <script type="text/javascript" src="js/superfish-1.4.8/js/superfish.js"></script>
        <script type="text/javascript" src="js/superfish-1.4.8/js/supersubs.js"></script>		
        <link rel="stylesheet" href="js/poshytip-1.0/src/tip-twitter/tip-twitter.css" type="text/css" />
        <link rel="stylesheet" href="js/poshytip-1.0/src/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
        <script type="text/javascript" src="js/poshytip-1.0/src/jquery.poshytip.min.js"></script>		
        <link rel="stylesheet" href="css/jquery.tweet.css" media="all"  type="text/css"/> 
        <script src="js/tweet/jquery.tweet.js" type="text/javascript"></script> 
        <script type="text/javascript" src="js/prettyPhoto/js/jquery.prettyPhoto.js"></script>
        <link rel="stylesheet" href="js/prettyPhoto/css/prettyPhoto.css" type="text/css" media="screen" />
        <link href='http://fonts.googleapis.com/css?family=Droid+Serif:400italic' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" href="css/mapstyle.css">
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>	
        <!--[if lt IE 9]>
                <link rel="stylesheet" href="css/sky-forms-ie8.css">
        <![endif]-->

        <!--[if lt IE 10]>
                <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
                <script src="js/jquery.placeholder.min.js"></script>
        <![endif]-->		
        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
                <script src="js/sky-forms-ie8.js"></script>
        <![endif]-->
		
<script type="text/javascript">

function userLogged(){
	var user = <?php echo json_encode($user); ?>;
	
	  
	if(user!="null"){
		  
	user = user.split(";");
	    
	  //1;Pedro Henrique Lopes;pepe@lopes.com;1234;29370024;CUIDANDO
	  document.getElementById("id").value=user[0];
	  document.getElementById("name").value =user[1];
	  document.getElementById("email").value =user[2];
	  document.getElementById("cemail").value =user[2];
	  document.getElementById("pass").value =user[3];
	  document.getElementById("cpass").value =user[3];
	  document.getElementById("phone").value =user[4];
      document.getElementById("category").value =user[5];
	  
	  if(user[5]=="CUIDADOR"){
		var aux = <?php echo json_encode($cuidandos); ?>;  
		aux=aux.split(";");
		
		 var newaux, option, select;
		
		for (var i = 0; i <= aux.length; i++) 
        { 
           newaux=aux[i].split("-");
		   
		   
		  if(newaux!="") {
   		   option = document.createElement("option");
	       select = document.getElementById("new_cuidador");
		   option.text =  newaux[1];
	       option.value = newaux[0];
	       select.appendChild(option);
		  }
        } 	 	  
	  }
	  
	  chooseType();
	 }
	  
}

function add(){
	var e = document.getElementById("cuidador");
	
	var option = document.createElement("option");
	var select = document.getElementById("new_cuidador");
	var valida = true;
	var i;
	 for (i = 0; i < select.length; i++) {
			if(select.options[i].value == e.options[e.selectedIndex].value) 
			{
			valida=false;
			}
			
	 }		
	if (valida){
	option.text =  e.options[e.selectedIndex].text;
	option.value = e.options[e.selectedIndex].value;
	select.appendChild(option);
	}
}

function getOFF(){
										
	var select = document.getElementById("new_cuidador");										
	/*select.removeChild(select[ select.selectedIndex]);*/
	select.remove(select.selectedIndex);
	
	}
	
function chooseType(){

	var select = document.getElementById("category");
	var type= select.options[select.selectedIndex].value;
	
	
	
	if(type=="CUIDANDO"){
			document.getElementById("cuidadores_area").style.visibility="hidden";
	} else {	
			document.getElementById("cuidadores_area").style.visibility="initial";
	}
}

 function selectAll() 
    { 
        selectBox = document.getElementById("new_cuidador");

        for (var i = 0; i < selectBox.options.length; i++) 
        { 
             selectBox.options[i].selected = true; 
        } 	
    }
	
</script>
		
		
		
    </head>
    <body onload="userLogged();">
        <div id="headerimgs">
            <div id="headerimg1" class="headerimg"></div>
            <div id="headerimg2" class="headerimg"></div>
        </div>

        <div id="top-gap"></div>

        <div class="wrapper">
            <a href="index.php"><img  id="logo" src="img/logo.png" alt="Home"></a>
            <div id="nav-bar-holder">
                <ul id="nav" class="sf-menu">
                    <li class="current-menu-item"><a href="home.php">Home</a></li>	
                    <li class="current-menu-item"><a href="cadastrar.php">Cadastrar</a></li>
					<li class="current-menu-item"><a href="log.php">Logs</a></li>
					<li class="current-menu-item"><a href="index.php">Deslogar</a></li>
                </ul>
            </div>

            <div id="content-wrap">
                <!-- Featured -->
                <div class="featured-title">
                    <div class="ribbon"><span>Cadastro</span></div>
                </div>

                <div id="page-wrap">
                    <form action="registrar.php" class="sky-form" method="POST">

                        <fieldset>	
								
								      <input id="id" name="id" type="hidden">
						
							<section>
                                <label class="input">
                                    <i class="icon-append icon-user"></i>
                                    <input id="name" name="name" type="text" placeholder="Nome">
                                    <b class="tooltip tooltip-bottom-right">Nome completo</b>
                                </label>
                            </section>						
                            <section>
                                <label class="input">
                                    <i class="icon-append icon-user"></i>
                                    <input id="email" name="email"type="email" placeholder="Email">
                                    <b class="tooltip tooltip-bottom-right">exemplo@email.com</b>
                                </label>
                            </section>

                            <section>
                                <label class="input">
                                    <i class="icon-append icon-envelope-alt"></i>
                                    <input id="cemail" name="cemail" type="email" placeholder="Repetir o email">
                                    <b class="tooltip tooltip-bottom-right">exemplo@email.com</b>
                                </label>
                            </section>

                            <section>
                                <label class="input">
                                    <i class="icon-append icon-envelope-alt"></i>
                                    <input id="pass" name="pass" type="password" placeholder="Senha">
                                    <b class="tooltip tooltip-bottom-right">Senha maior que 4 caracteres.</b>
                                </label>
                            </section>
							<section>
                                <label class="input">
                                    <i class="icon-append icon-lock"></i>
                                    <input id="cpass" name="cpass" type="password" placeholder="Confirmar senha">
                                    <b class="tooltip tooltip-bottom-right">Confirmar a senha</b>
                                </label>
                            </section>
							<section>
								<label class="input">
                                    <i class="icon-append icon-lock"></i>
                                    <input id="phone" name="phone" type="text" placeholder="Telefone">
                                    <b class="tooltip tooltip-bottom-right">(XX)XXX-XXX-XXX</b>
                                </label>
                            </section>
							<section>
                                <label class="select">
                                    <select id="category" name="category" id="category" onchange="chooseType()">
                                        <option value="NULL" selected="" disabled="">Categoria</option>
                                        <option value="CUIDADOR">Cuidador</option>
                                        <option value="CUIDANDO">Cuidando</option>
                                    </select>
                                  </label>
                            </section>
                        </fieldset>
						<fieldset id="cuidadores_area">
					
						
						Cuidandos Dispon&iacute;veis  			
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
						Cuidandos Selecionados
	     				
						<div class="row">
						<section class="col col-1">
							<label id="selectbox">
                                    <select name="select_list" id="cuidador" class="selectbox" size="8">
									   <?php $pos->listCuidandos();?>
                                    </select>
                              </label>						  
						</section>
						
		
						<section class="col col-5">
							<input type="button"  value=">" onclick="add()" class="button"></input></br></br></br>
							<input type="button"  value="<" onclick="getOFF()" class="button"></input>
						</section>
						<section class="col col-6">
							<label id="selectbox">
                                    <select name="selected_cuidandos[]" id="new_cuidador" class="selectbox" size="8"  multiple>

                                    </select>
                                    <i></i>
							</label>
						</section>
						</div>
						</fieldset>
                        <fieldset>
                            <section>
                                <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Eu concordo com os termos de serviços</label>
                            </section>
                        </fieldset>
                        <footer>
                            <button type="submit" class="button" onclick="selectAll();">Salvar</button>
                        </footer>
                    </form>		
                </div>

                <!-- ENDS Wrapper -->

                <!-- FOOTER -->
                <div id="footer">
                    <div class="footer-wrapper">


                        <!-- footer-cols -->
                        <ul id="footer-cols">
                            <li>
                                Trabalho de Conclusão do Curso de Análise e Desenvolvimento de Sistemas </br> Aluno Pedro Henrique Lopes  
                                <p>&nbsp;</p> 
                            </li>

                        </ul>
                        <!-- ENDS footer-cols -->

                        <div id="footer-glare"></div>

                    </div>
                </div>
                <!-- ENDS FOOTER -->

                <!-- footer-bottom -->
                <div id="footer-bottom">
                    <div class="bottom-wrapper">
                        <div id="bottom-left">
                            &copy; INSTITUTO FEDERAL DE EDUCAÇÃO, CIÊNCIA E TECNOLOGIA DE SÃO PAULO - CAMPUS GUARULHOS
                        </div>
                    </div>
                </div>
				<!-- ENDS FOOTER BOTTOM-->
            </div>
        </div>	
    </body>
</html>

