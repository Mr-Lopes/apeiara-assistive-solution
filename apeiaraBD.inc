<?php
class apeiaraBD{
	protected $_conn;
	
	function __construct () {
		$this->_conn = mysql_connect("localhost", "root", "") or die ("Falha conexão: " . mysql_error());
		mysql_select_db("apeiaraservice", $this->_conn) or die ("Falha selecionar Banco de Dados");
		mysql_query("SET NAMES 'utf8'");
		mysql_query('SET character_set_connection=utf8');
		mysql_query('SET character_set_client=utf8');
		mysql_query('SET character_set_results=utf8');
	}
			
	public function BuscarUsu($usuario,$senha ) {
		$comando = "SELECT * FROM USER WHERE email = '$usuario' AND pass = '$senha'";
		$retorno = mysql_query($comando); # retorna registros (SELECT)
		$qtde = mysql_num_rows($retorno); # qtde linhas retornadas
		
		if ( $qtde > 0 )
		{
			return  mysql_fetch_array($retorno);
		}
		else
		{
			return "naoexiste";
		}	
	}
	
	public function CadastrarUsu($name, $email, $password, $phone, $category) {	
        $command = "INSERT INTO user (name, email, pass, phone, category) VALUES ('$name', '$email', '$password', '$phone', '$category')";
		mysql_query($command); # executes (INSERT)
		$qtd = mysql_affected_rows($this->_conn); # return lines (INSERT)
		return mysql_insert_id($this->_conn);
	} 
	
	public function AtualizarUsu($id, $name, $email, $password, $phone, $category) {
		$command = "UPDATE user SET name = '$name', email = '$email', pass = '$password', phone = '$phone', category = '$category' WHERE id = '$id'";
		mysql_query($command); # executes (UPDATE)
		$qtd = mysql_affected_rows($this->_conn); #returns the quantity of lines (UPDATE)
		return $qtd;		
	}
	
	
	
	public function CadastrarCuidando($cuidando, $cuidador){
		$command = "INSERT INTO user_user (User_ID, USERS_ID) VALUES ('$cuidando', '$cuidador')";
		
		mysql_query($command); # executes (INSERT)
		$qtd = mysql_affected_rows($this->_conn); # return lines (INSERT)
		return $qtd;
	}
	
		
	public function RemoveCuidando($cuidador){
		$command = "DELETE from user_user where USERS_ID='$cuidador'";
		
		mysql_query($command); # executes (INSERT)
		$qtd = mysql_affected_rows($this->_conn); # return lines (INSERT)
		return $qtd;
	}
	

	
	public function DeletarUsu($id) {
		$command = "DELETE FROM user 
					WHERE id = '$id' ";
		mysql_query($command); # executes (DELETE)
		$qtd = mysql_affected_rows($this->_conn); #returns the quantity of lines (DELETE)
		return $qtd;		
	}
	
	
	public function BuscarUsers() {
		$comando = "SELECT * FROM USER";
		$retorno = mysql_query($comando); 
		$qtde    = mysql_num_rows($retorno); 
		#$row     = mysql_fetch_array($retorno);
		
		if ( $qtde > 0 ){
				return $retorno;

			}else{
				return null;
			}
		}
	public function getCuidandos($cuidador){
		$comando = "SELECT u1.ID as ID, u1.NAME as NAME FROM USER as u1 INNER JOIN USER_USER as u2 on u2.USERS_ID='$cuidador'and u2.User_ID=u1.ID";
		$retorno = mysql_query($comando); 
		$qtde    = mysql_num_rows($retorno); 
		
		
			$result="";
			while($row = mysql_fetch_array($retorno)){
				$result=$result.$row["ID"]."-".$row["NAME"].";";
			}
		
		
		if ( $qtde > 0 ){
				return $result;

			}else{
				return null;
			}
	
	}	
		
	
	public function listUsers(){
	
	$type="CUIDANDO";
	$comando = "SELECT * FROM USER WHERE CATEGORY='$type'";
		$retorno = mysql_query($comando); 
		$qtde    = mysql_num_rows($retorno); 
	
	
	$result[$qtde][2];
	$i=0;	
		
		while($row = mysql_fetch_array($retorno)){
			$result[$i][0]=$row['ID'];
			$result[$i][1]=$row['NAME'];
			
			$i++;
			}
			
		/*return $result;*/
        
		return [[1,'Pedro Henrique Lopes'],[2,'Jaiminho']];
		
	}
	
	public function listCuidandos(){
	$comando = "SELECT * FROM USER WHERE CATEGORY='CUIDANDO'";
		$retorno = mysql_query($comando); 
		$qtde    = mysql_num_rows($retorno); 
	
		
		while($row = mysql_fetch_array($retorno)){
			echo "<option value='$row[ID]'>$row[NAME]</option>";
			}
	}
	
	public function listCuidadores(){
	$comando = "SELECT * FROM USER WHERE CATEGORY='CUIDADOR'";
		$retorno = mysql_query($comando); 
		$qtde    = mysql_num_rows($retorno); 
	
		
		while($row = mysql_fetch_array($retorno)){
			echo "<option value='$row[ID]'>$row[NAME]</option>";
			}
	}
	
	public function createTable(){
	$comando = "SELECT r.ID as ID, u2.NAME as CUIDANDO, r.type as TYPE, r.date as DATE, r.status as STATUS, u1.NAME as CUIDADOR, r.LATITUDE as LATITUDE, r.LONGITUDE as LONGITUDE 
	             FROM request as r 
			     inner join `user` as u1 on r.CUIDADOR_ID=u1.ID 
			     inner join `user` as u2 on r.CUIDANDO_ID=u2.ID";
		$retorno = mysql_query($comando); 
		$qtde    = mysql_num_rows($retorno); 
	
		
		while($row = mysql_fetch_array($retorno)){
			
			echo "<tr>
			       <td class='btn'></td> 
			       <td id='id'>$row[ID]</td> 
				   <td>$row[CUIDANDO]</td> 
				   <td>$row[TYPE]</td> 
				   <td>$row[DATE]</td> 
				   <td>$row[STATUS]</td> 
				   <td>$row[CUIDADOR]</td> 
				   <td hidden>$row[LATITUDE]</td> 
				   <td hidden>$row[LONGITUDE]</td> 
    			 </tr>";
			}
	}
	
	
	

	
//	public function BuscarPos($Val) {
//		$comando = "SELECT * FROM TB_POS ORDER BY POS_ID DESC LIMIT 1";
//		$retorno = mysql_query($comando); # retorna registros (SELECT)
//		$qtde    = mysql_num_rows($retorno); # qtde linhas retornadas
//		$row     = mysql_fetch_array($retorno);
//		
//		if ( $qtde > 0 ){
//			//-- Latitude
//			if($Val == 1){
//				return $row["POS_LAT"];
//			//-- Longitude
//			}else if($Val == 2){
//				return $row["POS_LONG"];
//			//-- Tipo
//			}else{
//				return $row["POS_TIPO"];
//			}
//		}
//	}
	
	function __destruct () {
		@mysql_close($this->_conn);
	}	
}
?>