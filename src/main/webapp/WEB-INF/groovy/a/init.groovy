import wyd.dto.User
import com.google.appengine.api.datastore.Entity

println """
<html>
	<head>
		<title>Init App</title>
	</head>
	<body>
	<pre>
"""
println '------------------------------------------------------------------------------'
println 'Init App'
println '------------------------------------------------------------------------------'
Date now = new Date()
println 'Total Users = ' + User.count()

User user = User.get('vteial')
if(user == null ) {
	user = new User()
	user.id = 'vteial'
	user.password = 'wyd123'
	user.name = 'Eialarasu'
	user.emailId = 'vteial@wybis.com'
	user.timeZone = 'Chennai'
	user.createdTime = now
	user.updatedTime = now
	user.save()
}
println user
user = User.get('hari')
if(user == null ) {
	user = new User()
	user.id = 'hari'
	user.password = 'wyd123'
	user.name = 'Hariharasubramanian'
	user.emailId = 'hari@wybis.com'
	user.timeZone = 'Chennai'
	user.createdTime = now
	user.updatedTime = now
	user.save()
}
println user

println '------------------------------------------------------------------------------'
println """
</pre>
</body>
</html>
"""
