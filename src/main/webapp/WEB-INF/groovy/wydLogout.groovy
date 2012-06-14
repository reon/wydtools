import wyd.dto.WebMessage

def user = session.user
if(user != null) {
	session.removeAttribute('user')
}
session.invalidate()

request.webMessage = WebMessage.createInformationMessage("'$user.id' successfully logged out...")

forward 'login.groovy'
