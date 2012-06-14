<%
import wyd.dto.WebMessage

def sesUser = session?.user
%>
<!doctype html>
<html ng-app>

<head>
  	<title>WydTools - - ${request.title}</title>

  	<link rel="shortcut icon" href="images/gaelyk-small-favicon.png" type="image/png">
  	<link rel="icon" href="images/gaelyk-small-favicon.png" type="image/png">

  	<script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>

	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
  	<link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css" />
	<script type="text/javascript" src="js/bootstrap.min.js" ></script>
	
	<link rel="stylesheet" type="text/css" href="css/jquery.noty.css"/>
	<link rel="stylesheet" type="text/css" href="css/noty_theme_twitter.css"/>
	<script type="text/javascript" src="js/jquery.noty.js"></script>
  	
  	<style type="text/css">
    	body {
          padding-top: 60px;
      	}
      
      	.center {
        	text-align: center;
      	}
  	</style>
</head>
<body>
   
<div class="navbar navbar-fixed-top">
<div class="navbar-inner">
	<div class="container">
		<ul class="nav">
			<li class="">
				<a class="brand" href="/"><strong>WydTools</strong></a>
			</li>
			<li class="${request.view == 'wydIndex.gsp' ? 'active' : ''}">
				<a href="wydIndex.groovy"><i class="icon-home icon-white"></i></a>
			</li>
			<li class="divider-vertical"></li>
			<li class="${request.view == 'webConsole.gsp' ? 'active' : ''}">
				<a href="webConsole.groovy">WebConsole</a>
			</li>
			<li class="${request.view == 'regexpConsole.gsp' ? 'active' : ''}">
				<a href="regexpConsole.groovy">RegexpConsole</a>
			</li>
			<li class="${request.view == 'networkConsole.gsp' ? 'active' : ''}">
				<a href="networkConsole.groovy">NetowrkConsole</a>
			</li>
			<li class="${request.view == 'commandLineFu.gsp' ? 'active' : ''}">
				<a href="commandLineFu.groovy">CommandLineFu</a>
			</li>
			<li class="${request.view == 'instaCalc.gsp' ? 'active' : ''}">
				<a href="instaCalc.groovy">InstaCalc</a>
			</li>
		</ul>
		<% if(sesUser != null) { %>
		<form class="navbar-search">
			<input type="text" class="search-query" placeholder="Search">
			<a href="#"><i class="icon-search icon-white"></i></a>
		</form>
		<ul class="nav">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					System&nbsp;<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="acmUser.groovy">Users</a></li>
					<li><a href="wydAppConfig.groovy">App Config</a></li>
					<li><a href="wydEcho.groovy">App Echo</a></li>
		    	</ul>
			</li>
		</ul>	
		<% } %>
		<ul class="nav pull-right">
		<% if(sesUser != null) { %>
  			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<i class="icon-user icon-white"></i>&nbsp;<b>$sesUser.id</b>&nbsp;<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="wydProfile.groovy">Profile</a></li>
					<li class="divider"></li>
					<li><a href="wydLogout.groovy">Sign Out</a></li>
			    </ul>
			</li>
		<% } else { %>
			<li class="">
				<a href="wydLogin.groovy">Sign In</a>
			</li>
		<% } %>
			<!-- 
			<li class="divider-vertical"></li>
			-->
			<li class="${request.view == 'wydAbout.gsp' ? 'active' : ''}">
				<a href="wydAbout.groovy">About</a>
			</li>
		</ul>	
	</div>
</div>
</div>

<% 
//include 'wydBreadcrumb.gsp'

WebMessage webMessage = (WebMessage) request.webMessage
if(webMessage != null && webMessage.type == WebMessage.WARNING) {
	webMessage.value = "<storng>Warning : </storng> $webMessage.value"
}
if (webMessage != null) {
%>
<div class="container">
<div class="alert ${webMessage.type}">
  <button class="close" data-dismiss="alert">×</button>
  $webMessage.value
</div>
</div>
<%
	}
%>

<% include request.view %>

</body>
</html>