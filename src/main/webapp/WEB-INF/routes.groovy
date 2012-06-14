get "/favicon.ico",            redirect: "/images/gaelyk-small-favicon.png"

get "/mobile",                 redirect : "/mobile/wydLogin.groovy"
get "/mobile/",                redirect : "/mobile/wydLogin.groovy"

get "/mobile/logout.groovy",   forward : "/mobile/wydLogout.groovy"

get "/echo",		   	       forward : "wydEcho.jsp"
get "/echo.jsp",	   	       forward : "wydEcho.jsp"
get "/echo.groovy",		       forward : "wydEcho.jsp"

get "/about.groovy",           forward : "wydAbout.groovy"
get "/login.groovy",           forward : "wydLogin.groovy"
get "/profile.groovy",         forward : "wydProfile.groovy"
get "/logout.groovy",          forward : "wydLogout.groovy"

get "/",                       forward: "wydIndex.groovy"
get "/index.groovy",           forward: "wydIndex.groovy"



