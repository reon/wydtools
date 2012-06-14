import wyd.dto.WebMessage

println '''
<html>
	<head>
		<title>Test</title>
	</head>
<body>
<pre>
'''
def webMessage = WebMessage.createInformationMessage('Information Message...')
println webMessage.toJSONString()
println WebMessage.TWITTER_TO_NOTY.'alert-info'
webMessage = WebMessage.createSuccessMessage('Success Message...')
println webMessage.toJSONString()
webMessage = WebMessage.createErrorMessage('Error Message...')
println webMessage.toJSONString()
webMessage = WebMessage.createWarningMessage('Alert Message...')
println webMessage.toJSONString()
println '''
</pre>
</bodY>
</html>
'''