package album.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import album.model.AlbumDao;


@Controller
public class AlbumDeleteController {

	private final String command = "delete.ab";
	private final String gotoPage = "redirect:/list.ab";
	
	@Autowired
	AlbumDao albumDao;
	
	@RequestMapping(value=command)
	public String doAction(@RequestParam("num") String num) {
		// HttpServletRequest request, 
		//String num = request.getParameter("num");
		System.out.println("delete num : " + num);
		
		int cnt = albumDao.deleteAlbum(num);
		
		return gotoPage;
	}
	
}
