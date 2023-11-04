package album.controller;

import javax.crypto.Mac;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import album.model.AlbumBean;
import album.model.AlbumDao;


@Controller
public class AlbumInsertController {
	
	private final String command = "insert.ab";
	private final String getPage = "albumInsertForm";
	private final String gotoPage = "redirect:/list.ab";
	
	@Autowired
	AlbumDao albumDao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String insert() {
		
		return getPage;
	}
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("album") @Valid AlbumBean album, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}else {
			int cnt = albumDao.insertAlbum(album); // int cnt 값을 받아도 되고~
			
			mav.setViewName(gotoPage);
			return mav;
		}
	}
	
}
