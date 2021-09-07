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
		</link>
		
		<script type="text/javascript" src="//code.jquery.com/jquery-1.12.0.min.js"></script>	
		<script type="text/javascript" src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" type="text/css"/> 
		
	<style type="text/css" class="init">
	
		td.btn {
			background: url('img/details_open.png') no-repeat center center;
			cursor: pointer;
			height: 10px;
		}
		td.btn:hover {
			background: url('img/details_open.png') no-repeat center center;
			cursor: pointer;
		}
		tr.shown td.btn {
			background: url('img/details_close.png') no-repeat center center;
		}
	</style>
		
		
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
		
<script type="text/javascript" language="javascript" class="init">
	
function createMap(id,latitude, longitude){	

			
			 // variável que indica as coordenadas do centro do mapa
			 var mSec = new google.maps.LatLng(latitude,longitude);
			
			 // variável que indica as coordenadas do marcador
			 var mPrim = new google.maps.LatLng(latitude,longitude);

			 function initialize() {
			   var mapOptions = {
				  center: mSec, // variável com as coordenadas Lat e Lng
				  zoom: 15,
				//  mapTypeId: google.maps.MapTypeId.HYBRID
			   };
			   var map = new google.maps.Map(document.getElementById("map-canvas-"+id+""),
			 mapOptions);
			   
				// variável que define o URL para a nova imagem do marcador
			   var minhaImagem = 'images/farol.png';

			   // variável que define as opções do marcador
			   var marker = new google.maps.Marker({
				  position: mPrim, // variável com as coordenadas Lat e Lng
				  map: map,
				  title:"Solicitacao de Ajuda - Deficiente Visual",
				  icon: minhaImagem // define a nova imagem do marcador
			  });
			}
			
			google.maps.event.addDomListener(window, 'load', initialize());
	}


function format(id) {
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
            '<tr>'+
				'<td>ID:</td>'+
				'<td>'+ id +'</td>'+
			'</tr>'+				
            '</table>'+
	        '<div id="map-canvas-'+id+'" style="height: 400px; width: 860px" align="center"> </div>';
}


 
$(document).ready(function() {
   $('#example').DataTable();
   var table = $('#example').DataTable();
	
	// Add event listener for opening and closing details
    $('#example tbody').on('click', 'td.btn', function () {
       
	    var tr = $(this).closest('tr');
        var row = table.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.remove();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()[1]) ).show();
            tr.addClass('shown');
								//latitude    //longitude
			createMap(row.data()[1],row.data()[7],row.data()[8]);
        }
    } );	
} );

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
                <!-- Featured -->
                <div class="featured-title">
                    <div class="ribbon"><span>Logs</span></div>
                </div>

                <div id="page-wrap">
                    <form action="" class="sky-form" method="POST">
                        <fieldset>	
		
							<table id="example" class="display" cellspacing="0" width="100%">
								<thead>
									<tr>
									    <th></th>
										<th>ID</th>
										<th>Cuidando</th>
										<th>Pedido</th>
										<th>Data</th>
										<th>Status</th>
										<th>Atendido por</th>
										<th hidden>Latitude</th>
										<th hidden>Longitude</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th>ID</th>
										<th>Cuidando</th>
										<th>Pedido</th>
										<th>Data</th>
										<th>Status</th>
										<th>Atendido por</th>
										<th hidden>Latitude</th>
										<th hidden>Longitude</th>
									</tr>
								</tfoot>
								<tbody>
									  <?php $pos->createTable();?>
								
								</tbody>
							</table>
							
							
                        </fieldset>
						
						
					<!-- Featured -->					
					
	
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
