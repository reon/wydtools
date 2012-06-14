import wyd.dto.WebMessage

def user = session.user
if(user != null) {
	session.removeAttribute('user')
}
session.invalidate()

request.webMessage = WebMessage.createInformationMessage("<b>'$user.id'</b> successfully logged out...")

forward 'login.groovy'
