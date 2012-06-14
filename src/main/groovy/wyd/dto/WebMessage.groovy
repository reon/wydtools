package wyd.dto;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;


public class WebMessage implements Serializable, JSONAware {

	public static String WARNING = "";

	public static String INFOMATION = "alert-info";

	public static String SUCCESS = "alert-success";

	public static String ERROR = "alert-error";

	public static Map<String, String> TWITTER_TO_NOTY = ["" : "alert", "alert-info" : "information", "alert-success" : "success", "alert-error" : "error"]

	public static WebMessage createWarningMessage(String msg) {
		WebMessage webMessage = new WebMessage();
		webMessage.setType(WebMessage.WARNING);
		webMessage.setValue(msg);
		return webMessage;
	}

	public static WebMessage createInformationMessage(String msg) {
		WebMessage webMessage = new WebMessage();
		webMessage.setType(WebMessage.INFOMATION);
		webMessage.setValue(msg);
		return webMessage;
	}

	public static WebMessage createSuccessMessage(String msg) {
		WebMessage webMessage = new WebMessage();
		webMessage.setType(WebMessage.SUCCESS);
		webMessage.setValue(msg);
		return webMessage;
	}

	public static WebMessage createErrorMessage(String msg) {
		WebMessage webMessage = new WebMessage();
		webMessage.setType(WebMessage.ERROR);
		webMessage.setValue(msg);
		return webMessage;
	}

	public static WebMessage createMessage(String tpe, String msg) {
		WebMessage webMessage = new WebMessage();
		if(tpe == null) {
			tpe = WebMessage.WARNING;
		}
		webMessage.setType(tpe);
		webMessage.setValue(msg);
		return webMessage;
	}

	public static WebMessage createMessage(HttpServletRequest req) {
		WebMessage webMessage = null;
		String webMessageValue = req.getParameter("webMessageValue");
		if (webMessageValue != null) {
			String webMessageType = req.getParameter("webMessageType");
			webMessage = WebMessage.createMessage(webMessageType,
					webMessageValue);
		}
		return webMessage;
	}

	private String type = WebMessage.WARNING;

	private String value = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toQueryString() {
		StringBuilder qs = new StringBuilder("webMessageValue=");
		qs.append(this.value);
		qs.append("&webMessageType=");
		qs.append(this.type);

		return qs.toString();
	}

	@Override
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"");
		sb.append(JSONObject.escape("type"));
		sb.append("\":");
		sb.append("\"" + JSONObject.escape(this.type) + "\"");
		sb.append(",\"");
		sb.append(JSONObject.escape("notyType"));
		sb.append("\":");
		sb.append("\"" + JSONObject.escape(TWITTER_TO_NOTY.get(this.type)) + "\"");
		sb.append(",\"");
		sb.append(JSONObject.escape("value"));
		sb.append("\":");
		sb.append("\"" + JSONObject.escape(this.value) + "\"");
		sb.append("}");
		return sb.toString();
	}
}
