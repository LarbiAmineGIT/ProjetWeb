package SQLPackage;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import BeanPackage.UserBean;

public class SQLConnector {
	
		public SQLConnector() { }

		
		public HashMap<Integer, ArrayList<Object>> getUsers(String nom,String prenom) {
			Connection con = connect();
			
			
			HashMap<Integer, ArrayList<Object>> mapuser = new HashMap<Integer, ArrayList<Object>>();
			
			
			try {
				Statement stmt;
				stmt = con.createStatement();
				//ResultSet rs = stmt.executeQuery("SELECT id FROM `utilisateur` WHERE nom = '"+nom+"' OR  = '"+prenom+"';");
				String rqString = "Select id,nom,prenom,login FROM utilisateur where prenom = '"+prenom+"' OR nom = '"+nom+"';";
				ResultSet rs = doRequest(rqString);
				
				
				while (rs.next()) {
					//System.out.println("je rajoute le user dont le nom est : ' "+rs.getString("nom")+" ' et le prenom est '"+rs.getString("prenom")+"' et le id est : '"+rs.getString("id")+"'");
					//listeuser.add(Integer.valueOf(rs.getString("id"))); 
					
					ArrayList<Object> listeobject = new ArrayList<>();
					listeobject.add(rs.getString("nom"));
					listeobject.add(rs.getString("prenom"));
					//System.out.println("voici l'objet 0 nom :"+listeobject.get(0));
					//System.out.println("voici l'objet 0 prenom:"+listeobject.get(1));
					mapuser.put(Integer.valueOf(rs.getString("id")), listeobject);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return mapuser;

		}
		public UserBean getUser(String login, String password) {
			
			   UserBean user = null;
			   
			   String rqString = "Select * from utilisateur where login='"+login+"' and password='"+password+"';";
			   ResultSet res = doRequest(rqString);
			   int i = 0;
			   try {
				   while(res.next()) {
					   if(i==0) {
						   user = new UserBean();
						   user.setId(Integer.valueOf(res.getString("id")));
						   user.setLogin(res.getString("login"));
						   user.setPassword(res.getString("password"));
						   user.setNom(res.getString("nom"));
						   user.setPrenom(res.getString("prenom"));
						   user.setRang(res.getString("role"));
						   user.setDate(LocalDate.parse(res.getString("datenaissance")));
						   
					   }
					   else {
						   i++;
						   arret("Plus d'un utilisateur ayant le meme login ???");
					   }

				   }
				} 
			   catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   return user;
		   }
		   
		public void createUser(String login, String password, String nom, String prenom, LocalDate date) {
			
			   Connection con = connect();
			   
			    try {
			    	Statement stat = con.createStatement();
			    	
			    	ResultSet rs= stat.executeQuery("SELECT Count(login) As logincount FROM utilisateur WHERE login = '"+ login +"'");
			    	
			    	if (rs.next()) {
			    	    int count= rs.getInt("logincount");
			    	    
			    	    if(count == 0){
				    		Statement stmt = con.createStatement();
					    	String rqString = "INSERT INTO Utilisateur (role,login,password,nom,prenom,datenaissance) VALUES ('basic_user','"+
									   login+"','"+password+"','"+nom+"','"+prenom+"','"+date+"')";
							stmt.executeUpdate(rqString);
				    		
				    	}else {
				    		System.out.println("Adresse mail déja utilisée");
				    	}
			    	}
			    	
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}	
		}
		
		public int createUserPossible(String login) {
			
			   Connection con = connect();
			   int resultat = 2;
			    try {
			    	
			    	Statement stat = con.createStatement();
			    	
			    	ResultSet rs= stat.executeQuery("SELECT Count(login) As logincount FROM utilisateur WHERE login = '"+ login +"'");
			    	
			    	if (rs.next()) {
			    	    int count= rs.getInt("logincount");
			    	    
			    	    if(count == 0){
			    	    	resultat = 1;
				    		
				    	}else {
				    		//Cas ou adresse mail déja existante
				    		resultat = 0;
				    	}
			    	}
			    	
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
			    
			    return resultat;
		   }
		   
		   
		   public  ResultSet doRequest(String sql_string) {
			   ResultSet results = null;
			   Connection con = connect();
			   try {
				   Statement stmt = con.createStatement();
				   results = stmt.executeQuery(sql_string);
				} catch (SQLException e) {
				   e.printStackTrace();
				}
			   
			   return results;
		   }
		   
		 
		   public Connection connect() {
			   
			   Connection con = null;
			   
			   try {
				   Class.forName("com.mysql.cj.jdbc.Driver");
			   }
			   catch (ClassNotFoundException e) {
				   System.out.println("Driver non reussi");
			         arret("Impossible de charger le pilote jdbc");
			   }

			  // affiche("connexion a la base de donnÃ©es");
			   
			   try {
			         String DBurl = "jdbc:mysql://localhost:3306/database";
			         con = DriverManager.getConnection(DBurl,"root","");
			         affiche("connexion rÃ©ussie");
			   } 
			   catch (SQLException e) {
				   
			         arret("Connection Ã  la base de donnÃ©es impossible");
			   }
			   
			   return con;
		   }
		   
		   
		   
		   private static void affiche(String message) {
			      System.out.println(message);
		   }

		   
		   
		   private static void arret(String message) {
			      System.err.println(message);
			      System.exit(99);
		   }
		   
		   
		   public void addUser(String idsender, String idreceiver) {
				
			   Connection con = connect();
			   
			    try {
			    	Statement stat = con.createStatement();
			    	
			    	ResultSet rs= stat.executeQuery("SELECT Count(notificationID) As notifcount FROM notification WHERE user_sender_id = '"+ idsender +"' AND user_receiver_id = '"+ idreceiver +"' AND type = 'add_user' ");
			    	
			    	if (rs.next()) {
			    	    int count= rs.getInt("notifcount");
			    	    
			    	    if(count == 0){
				    		Statement stmt = con.createStatement();
					    	String rqString = "INSERT INTO notification (type,user_sender_id,user_receiver_id) VALUES ('add_user','"+
									   idsender +"','"+idreceiver+"')";
							stmt.executeUpdate(rqString);
				    		
				    	}else {
				    		System.out.println("Notification déja envoyée");
				    	}
			    	}
			    	
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}	
		}
		   
		   public HashMap<Integer, ArrayList<Object>> CountNotificationReceived(String id) {
			   	
			   	Connection con = connect();
			   	HashMap<Integer, ArrayList<Object>> mapuser = new HashMap<Integer, ArrayList<Object>>();
			    
			   	try {
			    	Statement stat = con.createStatement();
			    	
					String rqString = "SELECT * FROM notification WHERE user_receiver_id = '"+id+"' AND type = 'add_user'";
					ResultSet rs = doRequest(rqString);
					
					
					while (rs.next()) {
						
						System.out.println("Resultat récupéré dans la bdd notifications : Celui qui reçoit :"+ rs.getString("user_receiver_id")+" Celui qui envoi :"+ rs.getString("user_sender_id"));
						
						
						String rqString2 = "Select * from utilisateur where id='"+rs.getString("user_sender_id")+"'";
						ResultSet res2 = doRequest(rqString2);
						
						
						
						while (res2.next()) {
							
							ArrayList<Object> listeobject = new ArrayList<>();
							
							listeobject.add(res2.getString("nom"));
							listeobject.add(res2.getString("prenom"));
							listeobject.add(rs.getString("dateheure"));
							
							mapuser.put(Integer.valueOf(res2.getString("id")), listeobject);
						
							System.out.println("Une personne veut vous ajouter ! :" );
							System.out.println("le nom : "+res2.getString("nom"));
							System.out.println("le prenom :"+res2.getString("prenom"));
							
						}
						
						
					}
			    					  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
			    
			    return mapuser;
		}
		   
		   
		   public void deleteNotif(String idreceiver, String idsender) {
				
			   Connection con = connect();
			   
			   try {
			    	Statement stat = con.createStatement();
			    	
			    	int rs = stat.executeUpdate("DELETE FROM notification WHERE user_receiver_id = "+idreceiver+" AND user_sender_id = "+idsender+" AND type = 'add_user'");
			    
										  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		}
		   
		   public void acceptFriend(String idreceiver, String idsender) {
				
			   Connection con = connect();
			   
			   try {
			    	Statement stat = con.createStatement();
			    	
			    	int rs = stat.executeUpdate("INSERT INTO friend (userid,friendid) VALUES ('"+idreceiver+"','"+idsender+"')");
			    	int rs2 = stat.executeUpdate("INSERT INTO friend (userid,friendid) VALUES ('"+idsender+"','"+idreceiver+"')");
										  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		}
		 
		   
		   public void DeleteFriend(String iduser, String idelete) {
				
			   Connection con = connect();
			   
			   try {
			    	Statement stat = con.createStatement();
			    	
			    	int rs = stat.executeUpdate("DELETE FROM friend WHERE userid='"+iduser+"' AND friendid = '"+idelete+"'");
			    	int rs2 = stat.executeUpdate("DELETE FROM friend WHERE userid='"+idelete+"' AND friendid = '"+iduser+"'");
										  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		   
		   
		   public HashMap<Integer, ArrayList<Object>> getUserFriends(String iduser) {
			   
			   
				
			   	Connection con = connect();
			   	HashMap<Integer, ArrayList<Object>> mapuser = new HashMap<Integer, ArrayList<Object>>();
			    
			   	try {
			    	Statement stat = con.createStatement();
			    	
					String rqString = "SELECT userid,friendid FROM friend WHERE userid = '"+iduser+"'";
					ResultSet rs = doRequest(rqString);
					
					
					while (rs.next()) {
				
						String rqString2 = "Select nom,prenom,id FROM utilisateur where id = '"+rs.getString("friendid")+"';";
						ResultSet res2 = doRequest(rqString2);
						
						
						while (res2.next()) {
							
							ArrayList<Object> listeobject = new ArrayList<>();
							
							listeobject.add(res2.getString("nom"));
							listeobject.add(res2.getString("prenom"));
							
							mapuser.put(Integer.valueOf(res2.getString("id")), listeobject);
						
							System.out.println("Une personne veut vous ajouter ! :" );
							System.out.println("le nom : "+res2.getString("nom"));
							System.out.println("le prenom :"+res2.getString("prenom"));
							
						}
						
						
					}
			    					  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
			    
			    return mapuser;
			   
			   
				
		}
		   
		   
		   public HashMap<Integer, ArrayList<Object>> getUsers() {
			   
			   
				
			   	Connection con = connect();
			   	HashMap<Integer, ArrayList<Object>> mapuser = new HashMap<Integer, ArrayList<Object>>();
			    
			   	try {
			    	Statement stat = con.createStatement();
			    	
					String rqString = "SELECT * FROM utilisateur";
					ResultSet rs = doRequest(rqString);
					
					
					while (rs.next()) {
						ArrayList<Object> listeobject = new ArrayList<>();
							
						listeobject.add(rs.getString("nom"));
						listeobject.add(rs.getString("prenom"));
						listeobject.add(rs.getString("login"));
							
						mapuser.put(Integer.valueOf(rs.getString("id")), listeobject);
	
					}
			    					  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
			    
			    return mapuser;
			   
			   
				
		}
		   public HashMap<Integer, ArrayList<Object>> getActivites() {
			  
				
			   	Connection con = connect();
			   	HashMap<Integer, ArrayList<Object>> mapuser = new HashMap<Integer, ArrayList<Object>>();
			    
			   	try {
			    	Statement stat = con.createStatement();
			    	
					String rqString = "SELECT * FROM activite";
					ResultSet rs = doRequest(rqString);
					
					
					while (rs.next()) {
						ArrayList<Object> listeobject = new ArrayList<>();
							
						listeobject.add(rs.getString("date"));
						listeobject.add(rs.getString("lieuid"));
						listeobject.add(rs.getString("heuredeb"));
						listeobject.add(rs.getString("heurefin"));
							
						mapuser.put(Integer.valueOf(rs.getString("activiteid")), listeobject);
	
					}
			    					  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
			    
			    return mapuser;
			   
			   
				
		}
		   
		   
		   
		   
		   public void DeleteUser(String iduser) {
				
			   Connection con = connect();
			   
			   try {
			    	Statement stat = con.createStatement();
			    	
			    	int rs = stat.executeUpdate("DELETE FROM utilisateur WHERE id='"+iduser+"'");						  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		}
		   
		   
		   public void deleteActivity(String idactivity) {
				
			   Connection con = connect();
			   
			   try {
			    	Statement stat = con.createStatement();
			    	
			    	int rs = stat.executeUpdate("DELETE FROM activite WHERE activiteid ='"+idactivity+"'");						  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
		}
		   
		   public void modifyParameter(String parameter,String newparameter, String id) {

			   Connection con = connect();
			   
			   try {
			    	Statement stat = con.createStatement();
			    	
			    	int rs = stat.executeUpdate("UPDATE utilisateur SET "+parameter+" = '"+newparameter+"' WHERE id = "+id+";");						  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
	
		   }
		   
		   public void modifyDate(LocalDate newdate, String id) {

			   Connection con = connect();
			   
			   try {
			    	Statement stat = con.createStatement();
			    	
			    	int rs = stat.executeUpdate("UPDATE utilisateur SET datenaissance = '"+newdate+"' WHERE id = "+id+";");						  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
	
		   }
		   
		   
		   public void notifyFriends(String iduser) {
				
			   Connection con = connect();
			   
			    try {
			    	Statement stat = con.createStatement();
			    	
			    	String rqString = "SELECT userid,friendid FROM friend WHERE userid = '"+iduser+"'";
					ResultSet rs = doRequest(rqString);
			    	
			    	while(rs.next()) {
			    		
			    		Statement stmt = con.createStatement();
					    String rqString2 = "INSERT INTO notification (type,user_sender_id,user_receiver_id) VALUES ('alerte_covid','"+iduser +"','"+rs.getString("friendid")+"')";
						stmt.executeUpdate(rqString2);
	
				    }
			    	
			    }catch (SQLException e) {
						e.printStackTrace();
				}	
			    	
			} 
		   
		   
		   
		   public HashMap<Integer, ArrayList<Object>> getUserCovidNotification(String iduser) {
			   
			   
				
			   	Connection con = connect();
			   	HashMap<Integer, ArrayList<Object>> mapuser = new HashMap<Integer, ArrayList<Object>>();
			    
			   	try {
			    	Statement stat = con.createStatement();
			    	
					String rqString = "Select * FROM notification where user_receiver_id = '"+iduser+"' And type = 'alerte_covid '; ";
					ResultSet rs = doRequest(rqString);
					
					
					while (rs.next()) {
				
						String rqString2 = "Select nom, prenom FROM utilisateur where id = '"+rs.getString("user_sender_id")+"';";
						ResultSet res2 = doRequest(rqString2);
						
						
						while (res2.next()) {
							
							ArrayList<Object> listeobject = new ArrayList<>();
							
							listeobject.add(res2.getString("nom"));
							listeobject.add(res2.getString("prenom"));
							
							mapuser.put(Integer.valueOf(rs.getString("user_sender_id")), listeobject);
						
							
						}
						
						
					}
			    					  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
			    
			    return mapuser;
			   		
		}
		   
		   public void addActivite(LocalDate date, LocalTime heuredeb, LocalTime heurefin, String lieuid) {
				
			   Connection con = connect();
			   
			    try {
			    	Statement stat = con.createStatement();
			    	
			    	ResultSet rs= stat.executeQuery("SELECT Count(activiteid) As activitecount FROM activite WHERE date = '"+ date +"' AND heuredeb = '"+ heuredeb.toString() +"' AND heurefin ='"+ heurefin.toString() +"' AND lieuid = '"+lieuid+"'; ");
			    	
			    	if (rs.next()) {
			    	    int count= rs.getInt("activitecount");
			    	    
			    	    if(count == 0){
				    		Statement stmt = con.createStatement();
					    	String rqString = "INSERT INTO activite (date,heuredeb,heurefin,lieuid) VALUES ('"+date+"','"+ heuredeb +"','"+heurefin+"','"+lieuid+"');";
							stmt.executeUpdate(rqString);
				    		
				    	}else {
				    		System.out.println("Activité existe déja ");
				    	}
			    	}
			    	
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}	
		}
		   
		   
		   public void addparticipation(String iduser, LocalTime heuredeb, LocalTime heurefin, String idactivity) {
				
			   Connection con = connect();
			   
			   try {
			    	Statement stmt = con.createStatement();
			    	
			    	String rqString = " INSERT INTO participe (activiteid,userid,heuredeb,heurefin) VALUES ('"+Integer.valueOf(idactivity)+"','"+ iduser +"','"+heuredeb+"','"+heurefin+"');";
					stmt.executeUpdate(rqString);						  
				} 
			    catch (SQLException e) {
					e.printStackTrace();
				}
			    	
			    	
			} 
		   
		   
		   public HashMap<Integer, ArrayList<Object>> getParticipation(String iduser) {
				
			   Connection con = connect();
			   
			   HashMap<Integer, ArrayList<Object>> mapuser = new HashMap<Integer, ArrayList<Object>>();
				
				
				try {
					Statement stmt;
					stmt = con.createStatement();
					
					String rqString = "Select * from participe where userid ='"+iduser+"';";
					ResultSet rs = doRequest(rqString);
					
					
					while (rs.next()) {
						
						ArrayList<Object> listeobject = new ArrayList<>();
						listeobject.add(rs.getString("userid"));
						listeobject.add(rs.getString("heuredeb"));
						listeobject.add(rs.getString("heurefin"));
						
						mapuser.put(Integer.valueOf(rs.getString("activiteid")), listeobject);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return mapuser;

			    	
			    	
			} 
			    
}
		   

