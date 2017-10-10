using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using MySql.Data.MySqlClient;
using System.Net;
using System.Net.Mail;

namespace Apeiara
{
    
    /// <summary>
    /// Summary description for Service1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {
        private MySqlConnection bdConn; //MySQL
        private MySqlDataAdapter bdAdapter;
        private DataSet bdDataSet; //MySQL

        [WebMethod]
        public string GetPosicao(String usu, String Lat, String Long, String Tipo )
        {
           
            //TRATAMENTO DAS INFORMAÇOES
            //GRAVACAO NO BANCO DE DADOS
            //GERACAO DE PONTOS NO MAPA
            //ENVIO DE EMAIL

            try
            {

                return Gravar(Lat, Long, Tipo, usu);
            }
            catch (Exception e)
            {
                throw e;
            }


            //RETORNO PARA DISPOSITIVO
            
        }

        public string Gravar(string lat, string lon, string tipo, string usu)
        {

            string sqlQuery = "INSERT INTO TB_POS(POS_USER,POS_LAT, POS_LONG, POS_TIPO, POS_DATA) VALUES(" + usu + ",'" + lat + "','" + lon + "','" + tipo + "'," + DateTime.Now.ToShortDateString() + ")";
            
            String _conexaoMySQL = "";
            MySqlConnection con = null;
            
           _conexaoMySQL = "server=mysql01.portalapeiara.hospedagemdesites.ws;user id=portalapeiara;password=infante09;database=portalapeiara";
           
            try
            {
                
	            con = new MySqlConnection(_conexaoMySQL);
	            MySqlCommand cmd = new MySqlCommand(sqlQuery,con);	            
	            con.Open();
	            cmd.ExecuteNonQuery();
                Email();
                return "OK";
                
	        }
	        catch (Exception ex)
	        {
		        throw ex;
	        }
	        finally
            {
		        con.Close();
	        }
                
               

            }



        public void Email()
        {

            //Instancia o Objeto Email como MailMessage 
            
            SmtpClient smtpClient = new SmtpClient();
           // NetworkCredential basicCredential = new NetworkCredential("apeiara@portalapeiara.hospedagemdesites.ws", "apeiara2014");
            NetworkCredential basicCredential = new NetworkCredential("apeiaraapoio@gmail.com", "apeiara2014");
            MailMessage message = new MailMessage();
            MailAddress fromAddress = new MailAddress("apeiaraapoio@gmail.com");

            smtpClient.Host = "smtp.gmail.com";
            smtpClient.UseDefaultCredentials = false;
            smtpClient.EnableSsl = true;
            smtpClient.Credentials = basicCredential;

            message.From = fromAddress;
            message.Subject = "ATENCAO - SOLICITAÇÃO DE APOIO AO DEFICIENTE VISUAL";
           
            message.IsBodyHtml = true;
            message.Body = "<h1><a href='www.portalapeiara.com.br/home.php'>Clique aqui para Visualizar a Posição Atual do Deficiente</a></h1>";
            message.To.Add("kggrodrigues@gmail.com");

            try
            {
                smtpClient.Send(message);
            }
            catch (Exception ex)
            {
                //Error, could not send the message
               //esponse.Write(ex.Message);
            }
            
            
        } 
            
        }
    }
