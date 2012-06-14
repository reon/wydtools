import javax.servlet.http.HttpServletResponse

def entityName = params.entityName
if(entityName == null || entityName == '') {
	response.sendError(HttpServletResponse.SC_NOT_FOUND)
	return
}
response.contentType = 'text/csv'
response.setHeader('Content-Disposition', 'attachment;filename=' + entityName +'.csv')

def entities = datastore.execute {	from entityName	}
//System.out.println 'Total Rows of ' + entityName + ' = ' + entities.size()
if(entities.size() == 0) {	return	}

def entityProps = entities[0].properties.keySet().sort()
println entityProps.join(',')
entities.each { entity ->
	entityProps.each { propName ->
		print entity[propName]
		print ','
	}
	println ''
}