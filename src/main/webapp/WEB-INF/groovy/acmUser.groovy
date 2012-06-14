import wyd.domain.dto.User

request.users = User.findAll { sort desc by createdTime }

request.title = 'Users'
request.view = 'acmUser.gsp'

forward 'wydLayout.gsp'