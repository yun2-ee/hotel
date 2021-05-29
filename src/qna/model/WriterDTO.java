package qna.model;

public class WriterDTO {

	private String id;
	
	public WriterDTO() {}

	public WriterDTO(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "WriterDTO [id=" + id + "]";
	}
	
}
