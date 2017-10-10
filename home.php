<?PHP

require_once("apeiaraBD.inc");

$pos = new apeiaraBD;

session_start(); 
if(!$_SESSION['USER']) {
	header("location:index.php");	
}



?>

<!DOCTYPE  html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Portal Apeiara - Apoio ao Deficiente Visual</title>
		
		
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/social-icons.css" type="text/css" media="screen" />
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
		
		<script>
		
			// variável que indica as coordenadas do centro do mapa
			
			
			 
			 function initialize() {
			var pos= new google.maps.LatLng(-23.4392016,-46.5374154);
			  
			  if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(function(position) {
				  
					  pos = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);

					});
				  } 
				  
			   var mapOptions = {
				  center: pos, // variável com as coordenadas Lat e Lng
				  zoom: 15,
			   };
			   var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
			   

			   // variável que define as opções do marcador
			   var marker = new google.maps.Marker({
				  position: pos, // variável com as coordenadas Lat e Lng
				  map: map,
				  title:"Você",

			  });
			}
			
			google.maps.event.addDomListener(window, 'load', initialize);

		</script>
		
		

	</head>
	<body>
	
		
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
	        	
	        	<div id="page-wrap">
	        		<div id="front-slides">
						<div class="slides_container">
							<div class="slide">
							  <img src="img/dummies/01.jpg"  alt="pic" width="940" height="360"  />
								<div class="caption">Tecnologia Assistiva - Apoio ao deslocamento de Deficientes Visuais em Meios Urbanos </div>
						  </div>							
						</div>
						
						<div id="front-slides-cover"></div>
							
						<!-- Headline -->
						<div id="headline"><h6></h6></div>
						<!-- ENDS Headline -->	
					
					</div>
					<!-- ENDS Front slider -->
					
					
					<!-- Reel slider -->
	        		<div id="reel">
						<div class="slides_container">
							
							<div class="slide-box">
								<div class="box-container">
									<img src="img/mono-icons/bargraph32.png" class="box-icon" alt="pic"/>
									<h6>Histórico de Movimentações</h6>
									<div class="box-divider"></div>Acompanhe Todos os Passos do seu "Cuidando"
								</div>
								<div class="box-container">
									<img src="img/mono-icons/boxupload32.png" class="box-icon" alt="pic"/>
									<h6>Atualização de Aplicativos</h6>
									<div class="box-divider"></div>Versão 1.0 disponível para Download
								</div>
								<div class="box-container">
									<img src="img/mono-icons/article32.png" class="box-icon" alt="pic"/>
									<h6>Telefones Úteis</h6>
									<div class="box-divider"></div><p>POLICIA MILITAR: 190 </p><p>BOMBEIROS: 193</p><p>BOMBEIROS: 193</p><p>BOMBEIROS: 193</p>
								</div>
							</div>
							
						  <div class="slide-box">
								<div class="box-container">
									<img src="img/mono-icons/circleright32.png" class="box-icon" alt="pic"/>
									<h6>Parceiros</h6>
									<div class="box-divider"></div>INSTITUTO FEDERAL DE EDUCAÇÃO, CIÊNCIA E TECNOLOGIA DE SÃO PAULO - CAMPUS GUARULHOS
								</div>
								<div class="box-container">
									<img src="img/mono-icons/exchange32.png" class="box-icon" alt="pic"/>
									<h6>Novidades</h6>
									<div class="box-divider"></div>Versão Para Android disponível
								</div>
								<div class="box-container">
									<img src="img/mono-icons/cup32.png" class="box-icon" alt="pic"/>
									<h6>Links Úteis</h6>
									<div class="box-divider"></div>Links Uteis	</div>
							</div>
							
							
							
						</div>
						<a href="#" class="prev"></a>
						<a href="#" class="next"></a>
					</div>
					<!-- ENDS Reel slider -->
					
					
					
					<!-- Featured -->
					<div class="featured-title">
						<div class="ribbon"><span>Localização Atual</span></div>
					</div>
					
					<div id="map-canvas" style="height: 400px; width: 940px" align="center">
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
		<!-- ENDS footer-bottom -->		
	</body>
</html>

