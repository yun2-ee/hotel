package reserve.service;

import reserve.dto.BeforeReserveDTO;


public class ReserveChangeRequest {
	private  BeforeReserveDTO reservationdto;

	public ReserveChangeRequest(){}
	public ReserveChangeRequest(BeforeReserveDTO reservationdto) {
		this.reservationdto = reservationdto;
	}

	public BeforeReserveDTO getReservationdto() {
		return reservationdto;
	}

	public void setReservationdto(BeforeReserveDTO reservationdto) {
		this.reservationdto = reservationdto;
	}


	@Override
	public String toString() {
		return "ReserveChangeRequest [reservationdto=" + reservationdto + "]";
	}
	
	
	
	
}
