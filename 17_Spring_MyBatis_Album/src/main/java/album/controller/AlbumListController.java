package album.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import album.model.AlbumBean;
import album.model.AlbumDao;


@Controller
public class AlbumListController {
	
	private final String command = "/list.ab";
	private final String getPage = "albumList";
	
	// 앨범다오 객체가 필요하다.
	// 아까 다오클래스에서 component로 자동 객체 생성을 했기 때문에 Autowired 어노테이션을 사용하여 객체만듬
	@Autowired
	//@Qualifier("myAlbumDao") -> ㅎ나라서 굳이,,,안써도,,,ㅇㅇ
	AlbumDao albumDao;
	
	@RequestMapping(value=command)
	public ModelAndView doAction(Model model, HttpServletRequest request) {
		
		// getAllbumList() 메서드 호출해야함
		List<AlbumBean> albumLists = albumDao.getAlbumList();
		// 여기서 호출하면 
		
		// getPage 로 albumLists를 챙겨가고 싶을 때 여러가지 방법
		// model.addAttribute("albumLists", albumLists);
		// request.setAttribute("albumLists", albumLists);
		ModelAndView mav = new ModelAndView();
		mav.addObject("albumLists", albumLists);
		mav.setViewName(getPage);
		
		return mav; // /WEB-INF/album/albumList.jsp -> 폴더랑 파일둘다 생서앻쥬ㅜ기
	}
}
