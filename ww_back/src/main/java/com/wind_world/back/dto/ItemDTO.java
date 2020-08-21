package com.wind_world.back.dto;
 
public class ItemDTO {
	String watername;
	String waterzoom;
	String waterstate;
	String verylow;
	String letsgogj;
	String theen;
	String jibun;
	
	public ItemDTO() {
		super();
	
	}
	
	public ItemDTO(String watername, String waterzoom, String waterstate, String verylow ,String letsgogj,String theen,String jibun) {
	      super();
	      this.watername=watername;
	      this.waterzoom=waterzoom;
	      this.waterstate=waterstate;
	      this.verylow=verylow;
	      this.letsgogj=letsgogj;
	      this.theen=theen;
	      this.jibun=jibun;
	   }
	
	public void setwatername(String watername) {
		this.watername=watername;
	}
	public void setwaterzoom(String waterzoom) {
		this.waterzoom=waterzoom;
	}
	public void setwaterstate(String waterstate) {
		this.waterstate=waterstate;
	}
	public void setverylow(String verylow) {
		this.verylow=verylow;
	}
	public void setletsgogj(String letsgogj) {
		this.letsgogj=letsgogj;
	}
	public void settheen(String theen) {
		this.theen=theen;
	}
	public void setjibun(String jibun) {
		this.jibun=jibun;
	}
	
	public String getwatername() {
		return this.watername;
	}
	public String getwaterzoom() {
		return this.waterzoom;
	}
	public String getwaterstate() {
		return this.waterstate;
	}
	public String getverylow() {
		return this.verylow;
	}
	public String getletsgogj() {
		return this.letsgogj;
	}
	public String gettheen() {
		return this.theen;
	}
	public String getjibun() {
		return this.jibun;
	}
}
