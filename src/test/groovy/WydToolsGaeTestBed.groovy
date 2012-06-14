import wyd.dto.WebMessage

def webMessage = WebMessage.createInformationMessage('Information Message...')
println webMessage.toJSONString()
println WebMessage.TWITTER_TO_NOTY['alert-info']

