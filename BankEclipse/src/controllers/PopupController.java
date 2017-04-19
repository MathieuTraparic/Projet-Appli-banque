package controllers;

public abstract class PopupController<T> {

	public PopupController() {
		// TODO Auto-generated constructor stub
	}
	public void initData(T data) {
		this.data = data;
	}
	protected abstract void initializePopupFields(T data);
	
	public T getValidatedData() {
		return this.validated ? this.data : null;
	}
	
	protected T getData() { return this.data; }
	
	protected void setAsValidated() {
		this.validated = true;
	}
	private T data;
	private boolean validated = false;
}
