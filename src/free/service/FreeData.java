package free.service;

import free.model.Free;

public class FreeData {
	private Free free;

	public FreeData() {}
	public FreeData(Free free) {
		this.free = free;
	}
	public Free getFree() {
		return free;
	}
	public void setFree(Free free) {
		this.free = free;
	}
	@Override
	public String toString() {
		return "FreeData [free=" + free + "]";
	}

	
	
	
	
	

}
