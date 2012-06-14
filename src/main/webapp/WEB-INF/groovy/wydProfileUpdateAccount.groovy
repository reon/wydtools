import wyd.dto.WebMessage

//response.contentType = 'application/json'

def user = session.user
if(params.name == null || params.name == '') {
	//println 'Name should not be empty...'
	println WebMessage.createErrorMessage('Name should not be empty...').toJSONString()
	return
}
if(params.timeZone == null || params.timeZone == '') {
	//println 'Time Zone should not be empty...'
	println WebMessage.createErrorMessage('Time Zone should not be empty...').toJSONString()
	return
}
Date now = new Date()
user.name = params.name
user.timeZone = params.timeZone
user.updatedTime = now
user.save()

session.user = user

def s = application.globalTimeZones[user.timeZone]
session.userTimeZoneHour = s.substring(5, 7)
session.userTimeZoneMinute = s.substring(8, 10)

//int h = session.userTimeZoneHour as int
//int m = session.userTimeZoneMinute as int
//System.out.println("$h : $m")

//println 'Account information updated successfully...'
println WebMessage.createSuccessMessage('Account information updated successfully...').toJSONString()
