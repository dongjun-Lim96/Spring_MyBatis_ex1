package album.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import album.model.AlbumBean;
import album.model.AlbumDao;

@Controller
public class AlbumDetailController {

	// albumDetailView.jsp로 이동
	
		private final String command = "detail";
		private String gotoPage = "albumDetailView";
		
		@Autowired
		AlbumDao albumDao;
		
		@RequestMapping(command)
		public String doAction(@RequestParam("num") int num, Model model) {
			
			AlbumBean album = albumDao.getAlbumByNum(num);
			model.addAttribute("album", album);
			
			return gotoPage;
		}
	
}
