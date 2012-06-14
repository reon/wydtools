import wyd.dto.User
import wyd.dto.WebMessage

def rval = 'wydLogin.groovy?' + WebMessage.createErrorMessage('Invalid login! Either User Id or Password may be wrong...').toQueryString()

User user = User.get(params.userId)
if (user != null && user.password == params.password) {
	session.user = user
	
	if(user.timeZone == null) {
		user.timeZone = 'Chennai'
	}
	def s = application.globalTimeZones[user.timeZone]
	session.userTimeZoneHour = s.substring(5, 7)
	session.userTimeZoneMinute = s.substring(8, 10)
	
	rval = 'wydIndex.groovy?' + WebMessage.createErrorMessage("<b>'$user.id'</b> successfully logged in...").toQueryString()
}

redirect rval

