<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="BeanPackage.UserBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="SQLPackage.SQLConnector" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>User Activities</title>

    <!-- Custom fonts for this template -->
    <link href="../bootstrap/sb2/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../bootstrap/sb2/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="../bootstrap/sb2/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center">
                <div class="sidebar-brand-icon ">
                	<i class="fas fa-shield-virus"></i>
                </div>
                <div class="sidebar-brand-text mx-3">DefeatCovid </div>
            </a>
            
            
			
			<!-- Divider -->
            <hr class="sidebar-divider my-0">
			
           
			
            <!-- Divider -->
            <hr class="sidebar-divider my-0">
			
            <!-- Nav Item - Dashboard -->
            <li class="nav-item ">
                <a class="nav-link" href="logged.jsp">
                    <i class="fas fa-home"></i>
                    <span>Home</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">
            
            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="userprofile.jsp">
                    <i class="far fa-address-card"></i>
                    <span>Profile</span></a>
            </li>
            
            <!-- Divider -->
            <hr class="sidebar-divider my-0">
            
            <!-- Nav Item - Dashboard -->
            <li class="nav-item ">
                <a class="nav-link" href="userfriends.jsp">
                    <i class="fas fa-user-friends"></i>
                    <span>Amis</span></a>
            </li>
            
            <!-- Divider -->
            <hr class="sidebar-divider my-0">
            
            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link">
                    <i class="fas fa-walking"></i>
                    <span>Activités</span></a>
            </li>

           

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->
		
		 					<% UserBean current_user = (UserBean) session.getAttribute("current_user") ;%>
							<% SQLConnector sc = new SQLConnector(); %>
				     	  	<% String str = Integer.toString(current_user.getId()); %>
				     	  	<%  HashMap<Integer, ArrayList<Object>> ajout = new HashMap<Integer, ArrayList<Object>>();  %>
				     	  	<% ajout = sc.CountNotificationReceived(str); %>
				     	  	<% int taille = ajout.size(); %>
				     	  	
				     	  	<%  HashMap<Integer, ArrayList<Object>> mapamis = new HashMap<Integer, ArrayList<Object>>();  %>
					     	<% mapamis = sc.getUsers(); %>	
					     	
					     	<%  HashMap<Integer, ArrayList<Object>> mapparticipation = new HashMap<Integer, ArrayList<Object>>();  %>
					     	<% mapparticipation = sc.getParticipation(str); %>	
					     	
					     	<%  HashMap<Integer, ArrayList<Object>> mapnotif = new HashMap<Integer, ArrayList<Object>>();  %>
					     	  	<% mapnotif = sc.getUserCovidNotification(String.valueOf(current_user.getId())); %>
					     	
					     	<% int taille2 = mapnotif.size(); %>
				     	  	
				     	  	
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter"><% out.println(taille2); %></span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerte COVID
                                </h6>
                                
                                
                                
                                <% for(HashMap.Entry<Integer, ArrayList<Object>> entry : mapnotif.entrySet()) {
				     	  		
				     	  		out.println("<a class=\"dropdown-item d-flex align-items-center\" href=\"#\">");
				     	  		out.println("<div class=\"mr-3\">");
				     	  		out.println("<div class=\"icon-circle bg-danger\">");
				     	  		out.println("<i class=\"fas fa-file-alt text-white\"></i>");
				     	  		out.println(" </div>");
				     	  		out.println(" </div>");
				     	  		out.println("<div>");
				     	  		out.println("<span class=\"font-weight-bold\">"+entry.getValue().get(0)+" "+entry.getValue().get(1)+" est atteint du covid</span>");
				     	  		out.println("</div>");
				     	  		out.println("</a>");
				     	  		
				     	  		
				     	  		
				     	  		}%>
                                
                                
                               
                                <a class="dropdown-item text-center small " href="logged.jsp">Afficher toutes les notifications</a>
                            </div>
                        </li>

                       
				     	  	
						
                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter"><% out.println(taille); %></span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                
                                <h6 class="dropdown-header">
                                   Demandes d'ajout à la liste d'ami
                                </h6>
                               	
                               	<% for(HashMap.Entry<Integer, ArrayList<Object>> entry : ajout.entrySet()) {
				     	  		
				     	  		out.println("<a class=\"dropdown-item d-flex align-items-center\" href=\"#\">");
				     	  		out.println("<div class=\"dropdown-list-image mr-3\">");
				     	  		out.println("<img class=\"rounded-circle\" src=\"https://source.unsplash.com/Mv9hjnEUHR4/60x60\" alt=\"...\">");
				     	  		out.println("<div class=\"status-indicator bg-success\"></div>");
				     	  		out.println("</div>");
				     	  		out.println("<div>");
				     	  		out.println("<div class=\"text-truncate\">"+entry.getValue().get(0)+" "+entry.getValue().get(1)+" souhaite vous ajouter à ses amis</div>");
				     	  		out.println("<div class=\"small text-gray-500\">"+entry.getValue().get(2)+"</div>");
				     	  		out.println("</div>");
				     	  		out.println("</a>");
				     	  		
				     	  		
				     	  		
				     	  		}%>
                                
                                    
                                <a class="dropdown-item text-center small " href="userfriends.jsp">Voir toutes les demandes d'ajout</a>    
                                        
                                    
                                    
                            </div>   
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><% out.print(current_user.getPrenom()); %> <% out.print(current_user.getPrenom()); %></span>
                                <img class="img-profile rounded-circle"
                                    src="../bootstrap/sb2/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                
                                <div class="dropdown-divider"></div>
                                 <form method="post" class="user" action="../logoutservlet">
                                	<a class="dropdown-item" href="#" >
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                   
                                  	<button class="btn btn-user btn-block btn-success" type="submit"> Logout</button>
                                  	 </a>
                                  </form>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
				   	
				   	
		 			<div class="card shadow mb-4 ">
								<div class="card-header py-3">
                            	<h6 class="m-0 font-weight-bold text-primary">Outil de création d'activités </h6>
                       		 	</div>
                       		 	
                       		 	<div class="card-body">
										<form method="post" class="user" action="../addactivityservlet">
					          					<div class="form-group row">
					          						<div class="col-sm-6 mb-3 mb-sm-0">
					          						<h4>Date de l'activité:</h4>
					          							<input class="form-control form-control-user" name="date" type="date"  required>
					          						</div>
					          						
					          						<div class="col-sm-6 mb-3 mb-sm-0">
					          						<h4>Lieu de l'activité:</h4>
					          							<input class="form-control form-control-user" list="browsers" name="lieu" placeholder="Prenom de la personne que vous cherchez" type="text" required>
															 <datalist id="browsers">
															    <option>Google</option>
															    <option>IE9</option>
															 </datalist>
					          						</div>
					          					</div>
					          					
					          					<div class="form-group row">
					          						<div class="col-sm-6 mb-3 mb-sm-0">
					          							<h4>Heure de début:</h4>
					          							<input class="form-control form-control-user" name="heuredeb" type="time" required>
					          						</div>
					          						
					          						<div class="col-sm-6 mb-3 mb-sm-0">
					          							<h4>Heure de fin:</h4>
					          							<input class="form-control form-control-user" name="heurefin" type="time" required>
					          						</div>
					          					</div>
					          					
					          						
					          					<div class="form-group row">
					          						<div class="col-sm-4 mb-3 mb-sm-0"></div>
					          						<div class="col-sm-4 mb-3 mb-sm-0">
					          							<button type="submit" class="btn btn-primary btn-user btn-block"> Appuyez pour confirmer la création de l'activité </button>
					          						</div>
					          						<div class="col-sm-4 mb-3 mb-sm-0"></div>
					          					</div>
					          					
					          					
					          						
					          				</form>
								</div>
								
							</div>
							
							
							<div class="card shadow mb-4">
		                        <div class="card-header py-3">
		                            <h6 class="m-0 font-weight-bold text-primary">Liste des activités menées par l'utilisateur</h6>
		                        </div>
		                        <div class="card-body">
		                            <div class="table-responsive">
		                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                                    <thead>
		                                        <tr>
		                                            <th>ActiviteID</th>
		                                            <th>Heure du début</th>
		                                            <th>Heure de fin</th>
		                                            
		                                            
		                                            
		                                        </tr>
		                                    </thead>
		                                    
		                                 
		                                   <%
		                                   
											
											if(mapparticipation.size() == 0){
												System.out.println("pas d'amis");
											}else{
												
												out.println("<tbody>");
												
												int i = 0;
												
												for (HashMap.Entry<Integer, ArrayList<Object>> entry : mapparticipation.entrySet()) {
												    
													out.println("<tr>");
													
													out.println("<td>");
												    out.println(entry.getKey()); 
												    out.println("</td>");
												   
												    out.println("<td>");
												    out.println(entry.getValue().get(1)); 
												    out.println("</td>");
												    
												    
												    out.println("<td>");
												    out.println(entry.getValue().get(2)); 
												    out.println("</td>");
												    
												   
												    /*
												    out.println("<td style=\"width:15%\">");
													   
												    out.println("<form method=\"post\" id=deleteactivity"+i+" class=\"user\" action=\"../deleteactivityservlet\">");
												    out.println("<div class=\" row\">");
												    out.println("<div class=\"col-sm-12 mb-3 mb-sm-0\">");
												    out.println("<input type=\"hidden\" name=\"idactivitydelete[]\" value='"+entry.getKey()+"'\">");
												    out.println("<button \"  type=\"submit\" class=\"btn btn-danger btn-user btn-block\" id=\"btn-show-activity"+i+"\"> Supprimer la participation à cette activité </button>");
												    out.println("<div id=\"toast"+i+"\"></div>");
												    out.println("</div>");
												    out.println("</div>");
												    out.println("</form>");
												    
												    out.println("</td>");
												    */
												    i = i + 1;
												
												}
									
												out.println("</tbody>");
												
											}
											
										%>
		                                        
		                                        
		                                  
		                                </table>
		                            </div>
		                        </div>
		                    </div>
							
							

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="../bootstrap/sb2/vendor/jquery/jquery.min.js"></script>
    <script src="../bootstrap/sb2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../bootstrap/sb2/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../bootstrap/sb2/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="../bootstrap/sb2/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="../bootstrap/sb2/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../bootstrap/sb2/js/demo/datatables-demo.js"></script>

</body>

</html>