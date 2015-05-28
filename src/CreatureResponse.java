public class CreatureResponse {
	private String response;
	private boolean validAction;
	
	public CreatureResponse (String response, boolean validAction) {
		setResponse(response);
		setValidAction(validAction);
	}

	public boolean getValidAction() {
		return validAction;
	}

	public void setValidAction(boolean validAction) {
		this.validAction = validAction;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
