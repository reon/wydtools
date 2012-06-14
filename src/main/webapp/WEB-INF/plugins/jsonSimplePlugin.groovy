import groovyx.gaelyk.logging.GroovyLogger
import wyd.json.JsonSimpleCategory

def log = new GroovyLogger("jsonSimplePlugin")
log.info "Registering JSON-Simple plugin..."

binding {
	// Register JSON builder factory
	//jsonLibBuilderFactory = JsonLibBuilderFactory.instance

	// Plugin library variables
	plugins = [
				json : [
					version: "0.1",
					lib: [
						name: "JSON-Simple",
						version: "1.1.1"
					]
				]
			]
}

// Add JSON category
categories JsonSimpleCategory
