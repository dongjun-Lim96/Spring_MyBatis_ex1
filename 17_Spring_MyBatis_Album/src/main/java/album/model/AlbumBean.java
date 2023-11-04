package album.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AlbumBean {
private int num;
	
	@NotBlank(message = "제목 누락")
	private String title;
	
	@NotBlank(message = "가수 누락")
	@Length(max = 7, min = 3, message = "가수명은 3~7글자 입력") // 개수
	private String singer;
	
	@NotBlank(message = "가격 누락")
	@Min(value = 1000, message = "최소 1000원 이상") // 값
	private String price; // 유효성검사를 할건데, 유효성검사할때는 반드시 String 이어야한다!!
	
	private String day;

	public AlbumBean() {
		super();
	}

	public AlbumBean(int num, String title, String singer, String price, String day) {
		super();
		this.num = num;
		this.title = title;
		this.singer = singer;
		this.price = price;
		this.day = day;
	}

	public int getNum() {
		System.out.println("getNum");
		return num;
	}

	public void setNum(int num) {
		System.out.println("setNum");
		this.num = num;
	}

	public String getTitle() {
		System.out.println("getTitle");
		return title;
	}

	public void setTitle(String title) {
		System.out.println("setTitle");
		this.title = title;
	}

	public String getSinger() {
		System.out.println("getSinger");
		return singer;
	}

	public void setSinger(String singer) {
		System.out.println("setSinger");
		this.singer = singer;
	}

	public String getPrice() {
		System.out.println("getPrice");
		return price;
	}

	public void setPrice(String price) {
		System.out.println("setPrice");
		this.price = price;
	}

	public String getDay() {
		System.out.println("getDay");
		return day;
	}

	public void setDay(String day) {
		System.out.println("setDay");
		this.day = day;
	}
}
