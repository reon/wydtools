import wyd.dto.WebMessage

//response.contentType = 'application/json'

def user = session.user 
if(params.currentPassword == null || params.currentPassword == '') {
	//println 'Current Password should not be empty...'
	println WebMessage.createErrorMessage('Name should not be empty...').toJSONString()
	return
}
if(params.currentPassword != user.password) {
	//println 'Your current passord is wrong...'
	println WebMessage.createErrorMessage('Your current passord is wrong...').toJSONString()
	return
}
if(params.newPassword == null || params.newPassword == '') {
	//println 'New Password should not be empty...'
	println WebMessage.createErrorMessage('New Password should not be empty...').toJSONString()
	return
}
if(params.retypeNewPassword == null || params.retypeNewPassword == '') {
	//println 'Retype New Password should not be empty...'
	println WebMessage.createErrorMessage('Retype New Password should not be empty...').toJSONString()
	return
}
if(params.newPassword != params.retypeNewPassword) {
	//println 'New password and Retype New Password should same...'
	println WebMessage.createErrorMessage('New password and Retype New Password should same...').toJSONString()
	return
}
if(params.currentPassword == params.newPassword) {
	//println 'Current password and New Password should not be same...'
	println WebMessage.createErrorMessage('Current password and New Password should not be same...').toJSONString()
	return
}

Date now = new Date()
user.password = params.newPassword
user.updatedTime = now
user.save()

session.user = user

//println 'Password changed successfully...'
println WebMessage.createSuccessMessage('Password changed successfully...').toJSONString()

