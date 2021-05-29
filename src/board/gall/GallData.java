package board.gall;

import java.util.List;

public class GallData {
	private GallDTO gallDto;
	private List<GallImgDTO> gallImgDto;
	
	public GallData() {}

	public GallData(GallDTO gallDto, List<GallImgDTO> gallImgDto) {
		this.gallDto = gallDto;
		this.gallImgDto = gallImgDto;
	}

	public GallDTO getGallDto() {
		return gallDto;
	}

	public void setGallDto(GallDTO gallDto) {
		this.gallDto = gallDto;
	}

	public List<GallImgDTO> getGallImgDto() {
		return gallImgDto;
	}

	public void setGallImgDto(List<GallImgDTO> gallImgDto) {
		this.gallImgDto = gallImgDto;
	}
	
	
	
	
}
