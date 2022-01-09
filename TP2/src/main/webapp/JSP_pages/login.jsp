<!DOCTYPE html>
<html>
<head>

  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
 

    <title>Jumbotron Template for Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/jumbotron/">
	 <!-- Custom fonts for this template-->
    <link href="../bootstrap/sb2/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
	
    <!-- Bootstrap core CSS -->
   
   	<link href="../bootstrap/firstscreen/css/styles.css" rel="stylesheet">
    <link href="../bootstrap/sb2/css/sb-admin-2.min.css" rel="stylesheet">
	
	
    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">

</head>
<body class="bg-secondary bg-opacity-50" >
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="background-color:#28242c!important;">
            <div class="container px-lg-5">
                <a class="navbar-brand" >DefeatCovid</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" aria-current="page" href="../index.jsp">Home</a></li>
                        <li class="nav-item"><a class="nav-link active">Connexion</a></li>
                        <li class="nav-item"><a class="nav-link" href="register.jsp">Inscription</a></li>
                         <li class="nav-item"><a class="nav-link" href="help.jsp">FAQ <span class="sr-only">(current)</span></a></li>
                        
                    </ul>
                </div>
            </div>
        </nav>

    <main role="main">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div>
        <div class="container">
          <div class="justify-content-center ">
          
	          <div class="card o-hidden border-0 shadow-lg my-5 ">
	          	<div class="card-body p-0">
	          		<div class="row">
	          			<div class="col-lg-6 d-none d-lg-block" style="background: url('../bootstrap/sb2/img/loginimage2.jpg'); background-position: center; background-size: cover;"></div>
	          			<div class="col-lg-6 border-left">
	          				<div class="p-5">
		          				<div class="text-center">
		          					<h1 class="h4 text-gray-900 mb-4">
		          						 Connexion 
		          					</h1>
		          				</div>
								
								<hr>
								          				
			          			<section>
			          				<form method="post" class="user" action="../login">
			          					
			          					
			          					<div class="form-group row">
			          						<div class="col-sm-12 mb-3 mb-sm-0">
			          							<input class="form-control form-control-user" name="login" placeholder="Adresse mail" type="email" required>
			          						</div>
			          					</div>
			          					
			          					<div class="form-group row">
			          						<div class="col-sm-12 mb-3 mb-sm-0">
			          							<input class="form-control form-control-user" name="password" placeholder="Mot de passe" type="password" required>
			          						</div>
			          					</div>
			          					
			          						
			          					<div class="form-group row">
			          						<div class="col-sm-12 mb-3 mb-sm-0">
			          							<button type="submit" class="btn btn-primary btn-user btn-block"> Se connecter </button>
			          						</div>
			          					</div>
			          					
			          					
			          						
			          				</form>
			          				
			          				<hr>
                                    <div class="text-center">
                                        <a class="small" href="forgotpassword.jsp">Mot de passe oublié?</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="register.jsp">Pas de compte? Crééz en un!</a>
                                    </div>
			          			</section>
	          				</div>
	          		
	          			</div>
	          			
	          		</div>
	          	
	          	</div>
	          
	          </div>
	          
	        </div>
        </div>
      </div>

     

    </main>

	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="bootstrap/sb2/js/sb-admin-2.min.js"></script>
  </body>
</html>