package album.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//AlbumDao myAlbumDao = new AlbumDao();
@Component("myAlbumDao")
public class AlbumDao {

	// mapper화일, 즉 album.xml; 에 있는 namespace 속성의 값 album.AlbumBean 똑같이 작성
	private String namespace = "album.AlbumBean";
	
	// root-context.xml의 맨 마지막에 보면 SqlSessionTemplate 객체를 생성했었다.
	// 따라서 여기서 새로 생성하는 것이 아닌, 아까 만든 객체를 여기에 주입해야한다. - @Autowired 어노테이션 사용함
	@Autowired
	SqlSessionTemplate sqlSessionTemplate; // 어떤 계정에 접속해야하는지에 대한 정보
	
	public AlbumDao() {
		System.out.println("AlbumDao()");
	}
	
	
	// 
	public List<AlbumBean> getAlbumList(){
		
		List<AlbumBean> lists = new ArrayList<AlbumBean>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetAlbumList"); // select 에서 ArrayList 로 가져오는 메서드  
		// album.AlbumBean.GetAlbumList -> namespace + id 마지막에 있는거만 아이디. 즉 아이디는 GetAlbumList
		// 네임스페이스가 album.AlbumBean 인 매퍼 화일을 찾아간다. (album.xml)
		
		
		System.out.println("lists.size() : " + lists.size());
		return lists;
	}
	
	public int insertAlbum(AlbumBean album) {
		int cnt = -1;
		// 입력한 값을 넘겨야 하기 때문에 album
		cnt = sqlSessionTemplate.insert(namespace + ".InsertAlbum", album); // namespace : album.AlbumBean
		return cnt;
		// 컨트롤러로 넘어감.
	}
	
	public int deleteAlbum(String num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace + ".DeleteAlbum", num);
		return cnt;
	}
	
	public AlbumBean getAlbumByNum(int num) {
		// 하나만 가져온다!
		AlbumBean album = sqlSessionTemplate.selectOne(namespace + ".GetAlbumByNum", num);
		return album;
	}
	
}
