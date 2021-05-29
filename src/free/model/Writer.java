package free.model;

//게시글 작성자 정보를 담는 클래스
public class Writer {
	private String id;
	//private String name;
	
	public Writer(String id) {
		this.id = id;
		//this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Writer [id=" + id + "]";
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	
	

}
