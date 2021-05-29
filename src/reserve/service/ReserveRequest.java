package reserve.service;

import reserve.dto.BeforeReserveDTO;


public class ReserveRequest {
	private  BeforeReserveDTO reservationdto;
	private String paymethod;
	
	public ReserveRequest( BeforeReserveDTO reservationdto, String paymethod) {
		this.reservationdto = reservationdto;
		this.paymethod = paymethod;
	}

	public  BeforeReserveDTO getReservationdto() {
		return reservationdto;
	}

	public void setReservationdto( BeforeReserveDTO reservationdto) {
		this.reservationdto = reservationdto;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	@Override
	public String toString() {
		return "ReserveRequest [reservationdto=" + reservationdto + ", paymethod=" + paymethod + "]";
	}
	
	
	
	
}
