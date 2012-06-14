package wyd.json

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.json.simple.JSONArray
import org.json.simple.JSONObject

public class JsonSimpleCategory {

	static Object parseJson(HttpServletRequest request, String parameterName) {
		String json = request.getParameter(parameterName)
		convertJsonToDynaObject(json)
	}

	static Object parseJson(String json) {
		convertJsonToDynaObject(json)
	}

	private static convertJsonToDynaObject(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json)
		JSONObject.toBean(jsonObject)
	}

	static void renderJson(HttpServletResponse response, JSONObject jsonObject, String encoding = 'UTF-8') {
		renderJson response, jsonObject.toString(), encoding
	}

	static void renderJson(HttpServletResponse response, JSONArray jsonArray, String encoding = 'UTF-8') {
		renderJson response, jsonArray.toString(), encoding
	}

	static void renderJson(HttpServletResponse response, Object object, String encoding = 'UTF-8') {
		JSONObject jsonObject = JSONObject.fromObject(object)
		renderJson response, jsonObject.toString(), encoding
	}

	static void renderJson(HttpServletResponse response, String jsonString, String encoding = 'UTF-8') {
		respondWithJson response, new String(jsonString.getBytes(), encoding)
	}

	private static respondWithJson(HttpServletResponse response, String encodedJsonString) {
		response.contentType = 'application/json'
		response.getWriter().write encodedJsonString
	}
}