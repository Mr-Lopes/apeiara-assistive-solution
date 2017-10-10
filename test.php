<?php

require_once("apeiaraBD.inc");

$pos = new apeiaraBD;

$options = "";
#$vys = mysqli_query($db, $goods);
$vys = $pos->BuscarUsers();
while ($arr = mysqli_fetch_assoc($vys)) {
   $options .= "<option value='".$arr['id_good']."'>".$arr['name']."</option>";               
 }

echo <<< _html
<form method="post">            
      <div id="dynamicInput">
            <div>
                <select name=idp[]>
                    $options
                </select> <br />
                Entry 1 <input type="text" name=myInputs[]><br />
            </div>
       </div>
       <input type="button" value="Add another text input" onClick="addInput('dynamicInput');"><br />
       <input type="submit" name="order">
</form>

_html;


									

																											
					

?>

<script type="text/javascript">
var counter = 1;
function addInput(divName){
          var newdiv = document.createElement('div');
          newdiv.innerHTML = "<select name=idp[]><?php echo $options; ?></select> Entry " + (counter + 1) + " <input type='text' name='myInputs[]'>";
          document.getElementById(divName).appendChild(newdiv);
          counter++;
}
</script>