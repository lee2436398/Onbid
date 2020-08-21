package com.wind_world.back.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.DomainLoadStoreParameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.wind_world.back.dao.MemberDao;
import com.wind_world.back.dto.ItemDTO;
import com.wind_world.back.dto.MemberDTO;
import com.wind_world.back.dto.PositionDTO;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/apiRest")




public class ApiController {
	@Autowired
	SqlSession session;
	
	@RequestMapping("/selec_catg")
	public List<Map<String, String>> selec_catg(){
		
		List<Map<String, String>> a1=session.selectList("test.member.selec_catg");
		
		return a1;
		
	}
	
	@RequestMapping("/selec_sido")
	   public List<Map<String, String>> selec_sido() {
	      
		
		  List<Map<String, String>> a1=session.selectList("test.member.selec_sido");
	      
	      System.out.println(a1);
	      
	      return a1;
	   }
	
	
	   @RequestMapping("/selec_gugun")
	   public List<Map<String, String>> selec_gugun(HttpServletRequest request){
		  String sc=request.getParameter("sido").substring(0,2);
		  System.out.println(sc);
	      List<Map<String, String>> a1=session.selectList("test.member.selec_gugun",sc);
	      
	      System.out.println(a1);
	      
	      return a1;
	   }
	   
	   
	
	
	@RequestMapping("/dong_list")
	public List<String> donglist(HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException {
		
		
		String gugun=request.getParameter("gugun");
		String ss=gugun.substring(0,5);
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B553077/api/open/sdsc/baroApi"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=MOVf7R3OoIzx8dZvko%2FxBKmaboWkrMGnstMKdRmbn91axzWJ5ZgL7oV1LOE9nLjCEIGkvQL0ZU4nEYCfBSWqAw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("resId","UTF-8") + "=dong"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("catId","UTF-8") + "=admi"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("signguCd","UTF-8") + "="+ss); /*Service Key*/
        
        System.out.println(urlBuilder);
	       String url = new String(urlBuilder.toString());
	       
	       DocumentBuilderFactory dbFactoty=DocumentBuilderFactory.newInstance();
	       DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	       Document doc=dBuilder.parse(url);
	       
	       doc.getDocumentElement().normalize();
	       System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());
	       
	       NodeList nList = doc.getElementsByTagName("item");
	       System.out.println("파싱할 리스투 수: "+ nList.getLength());
	       
	       List<String> donglist =new ArrayList<String>();
	       for(int temp=0;temp<nList.getLength();temp++) {
	    	   Node nNode=nList.item(temp);
	    	   if(nNode.getNodeType()==Node.ELEMENT_NODE) {
	    		   Element eElement = (Element) nNode;
	    		   donglist.add(getTagValue("adongNm",eElement));
	    	   }
	       }
        
        
        return donglist;
	}
	
	@RequestMapping("/serch")  //http://localhost:8090/kamco/apiRest/test
	public List<ItemDTO> ser(HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException {
		
		
		
		String sido=request.getParameter("sido");
		
		List<String> sidon=session.selectList("test.member.selec_siname",sido);
		
		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=Dvcgx3pgVWjTPf88ltePPibU4gTyuEmmX07p6uSqnjxn5K1pUBjdtesjv041WYuVxgxl1%2Bdeh8l9kEHbl8N4QQ%3D%3D"); /*공공데이터포털에서 발급받은 인증키*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
	    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 데이터 개수*/
	    urlBuilder.append("&" + URLEncoder.encode("DPSL_MTD_CD","UTF-8") + "=" + URLEncoder.encode("0001", "UTF-8")); /*0001 매각 0002 임대*/
	    urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID","UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("CTGR_HIRK_ID_MID","UTF-8") + "=" + URLEncoder.encode("10300", "UTF-8"));
	    urlBuilder.append("&" + URLEncoder.encode("SIDO","UTF-8") + "=" + sidon.get(0));
	    
	    
	    
	    System.out.println(urlBuilder);
	       String url = new String(urlBuilder.toString());
	       
	       DocumentBuilderFactory dbFactoty=DocumentBuilderFactory.newInstance();
	       DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	       Document doc=dBuilder.parse(url);
	       
	       doc.getDocumentElement().normalize();
	       System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());
	       
	       NodeList nList = doc.getElementsByTagName("item");
	       
	       List<ItemDTO> li=new ArrayList<ItemDTO>();
	       
	       for(int temp=0;temp<nList.getLength();temp++) {
	    	   Node nNode=nList.item(temp);
	    	   if(nNode.getNodeType()==Node.ELEMENT_NODE) {
	    		   Element eElement = (Element) nNode;
	    		   ItemDTO dt=new ItemDTO();
	    		   dt.setwatername(getTagValue("CLTR_NM",eElement));
	    		   dt.setverylow(getTagValue("MIN_BID_PRC",eElement));
	    		   dt.setletsgogj(getTagValue("PBCT_BEGN_DTM",eElement));
	    		   dt.settheen(getTagValue("PBCT_CLS_DTM",eElement));
	    		   dt.setwaterstate(getTagValue("PBCT_CLTR_STAT_NM",eElement));
	    		   dt.setwaterzoom(getTagValue("GOODS_NM",eElement));
	    		   dt.setjibun(getTagValue("LDNM_ADRS",eElement));
	    		   li.add(dt);
	    	   }
	       } 
		
		return li;
	}
	
	
	@RequestMapping("/sang")
	public List<PositionDTO> sa(HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException{
		
		String lat=request.getParameter("lat");   //위도
		String lon=request.getParameter("lon");  //경도
		String cat=request.getParameter("cat");
		
		System.out.println("hi");
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=MOVf7R3OoIzx8dZvko%2FxBKmaboWkrMGnstMKdRmbn91axzWJ5ZgL7oV1LOE9nLjCEIGkvQL0ZU4nEYCfBSWqAw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=300"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("cx","UTF-8") + "="+lon); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("cy","UTF-8") + "="+lat); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("indsLclsCd","UTF-8") + "="+cat);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=1000");
        
        System.out.println(urlBuilder);
	       String url = new String(urlBuilder.toString());
	       
	       DocumentBuilderFactory dbFactoty=DocumentBuilderFactory.newInstance();
	       DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	       Document doc=dBuilder.parse(url);
	       
	       doc.getDocumentElement().normalize();
	       System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());
	       
	       NodeList nList = doc.getElementsByTagName("item");
	       
	       List<PositionDTO> li=new ArrayList<PositionDTO>();
	       
	       System.out.println(nList.getLength());
	       
	       for(int temp=0;temp<nList.getLength();temp++) {
	    	   Node nNode=nList.item(temp);
	    	   if(nNode.getNodeType()==Node.ELEMENT_NODE) {
	    		   Element eElement = (Element) nNode;
	    		   PositionDTO dt=new PositionDTO();
	    		   dt.setlon(getTagValue("lon",eElement));
	    		   dt.setlat(getTagValue("lat",eElement));
	    		   li.add(dt);
	    	   }
	       }
		
		return li;
	}
	
	private static String getTagValue(String tag,Element eElement) {
		   NodeList nlList=eElement.getElementsByTagName(tag).item(0).getChildNodes();
		   Node nValue = (Node)nlList.item(0);
		   if(nValue==null)
			   return null;
		   return nValue.getNodeValue();
	   }

}
