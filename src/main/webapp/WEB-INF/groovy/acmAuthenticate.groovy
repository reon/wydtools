import wyd.domain.dto.User

def rval = 'wydLogin.groovy?errorMessage=Invalid login! Either User Id or Password may be wrong...'

User user = User.get(params.userId)
if (user != null && user.password == params.password) {
	session.user = user
	
	if(user.timeZone == null) {
		user.timeZone = 'Chennai'
	}
	def s = application.globalTimeZones[user.timeZone]
	session.userTimeZoneHour = s.substring(5, 7)
	session.userTimeZoneMinute = s.substring(8, 10)
	
	rval = "wydIndex.groovy?successMessage=Welcome <b>$params.userId</b>!"
}

redirect rval

