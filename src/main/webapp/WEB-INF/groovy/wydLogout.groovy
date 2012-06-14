
def user = session.user
if(user != null) {
	session.removeAttribute('user')
}
session.invalidate()

request.infoMessage = "'$user.id' successfully logged out..."
forward 'login.groovy'

//redirect "login.groovy?infoMessage=Successfully logged out...";
