import wyd.domain.dto.User
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

/*
def entities = datastore.execute{
	from AppConfigE
	where id == lastTranscationShowingLimit
	limit 1
}

Entity appconfig = null
if(entities.size() == 0 ) {
	appconfig = new Entity('AppConfigE')
	appconfig.id = 'lastTranscationShowingLimit'
	appconfig.value = 10
	appconfig.createdTime = now
	appconfig.updatedTime = now
	appconfig.save()
}
else {
	appconfig = entities[0]
}
println 'AppConfig - ' + appconfig.id
*/
println '------------------------------------------------------------------------------'
println """
</pre>
</body>
</html>
"""
