package br.com.inatel.quotationmanagement.controller.form;

/**
 * Data loaded from the Notification call
 * @author francisco.carvalho
 * @since 1.0
 */

public class NotificationForm {

	private String host;
	private String port;
	
	public NotificationForm(String host, String port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
