# Spring_MyBatis_ex1

## Spring_MyBatis_Album

Spring은 Framework이며 틀이 짜여져 있다.

- Framework : 틀이 갖추어져 있는 상태에서 작업할 수 있는 하나의 기술.
    
    ex ) form에서 입력한 항목이 10가지가 있음 → 커맨드 객체로 간단하게 작성할 수 있었음.
    
    ex ) 커맨드 객체를 보면 체크박스가 있을 때 사이사이에 쉼표가 자동으로 찍힌다. 
    즉, 스프링에서 내부적으로 구현이 되어있음.
    
    따라서 어떤식으로 틀이 갖추어져 있는지를 알면 jsp보다 훨씬 간단하고 빠르게 작업을 할 수 있다.

## **MyBatis DB**

- **MyBatis**는 데이타베이스 관련된 프레임워크 이며 훨씬 더 간단하다.

## 세팅방법~

1.pom.xml(실행)

2.web.xml(실행)

3.root-context.xml

4.SqlMapConfig.xml(doctype ~ <configuration>)

5.album.xml(mapper 파일 DOCTYPE~ <mapper>) (실행)

6.album-servlet.xml(실행)


### 1. pom.xml 입력(db,유효성검사,mybatis,dbcp)
		<!-- mybatis 관련 -->
		 <dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.1.0</version>
		</dependency> 
		
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.1.0</version>
		</dependency> 
		 
		 <!-- dbcp 관련 -->
		 <dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>

### 2. web.xml → root-context.xml
![Untitled](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/18b4397f-635b-41f3-9c77-fa093308750d)

root-context.xml 프로그램 전반에 걸쳐 사용될 객체를 만든다.

### 3. root-context.xml

- 여기서 각 name은 정해져있다.
- 에러가 뜨면 pom.xml의 디펜던시를 잘못 썼을 경우가 크다.

아래 코드 작성하기

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">
	
	<!-- Root Context: defines shared resources visible to all other web components -->
	
	<!-- 자바로 쓴다면?
	org.apache.commons.dbcp.BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
	dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	dataSource.setUsername("jspid");
	dataSource.setPassword("jsppw");
	
	 -->
	
	
	<!-- 변수이름이 정해져있다. -->
	
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
		<property name="url" value="jdbc:oracle:thin:@localhost:1521:orcl"/>
		<property name="username" value="jspid"/>
		<property name="password" value="jsppw"/>	
	</bean>
	
	
	<!-- 
	org.mybatis.spring.SqlSessionFactoryBean sqlSessionFactoryBean = new org.mybatis.spring.sqlSessionFactoryBean();
	sqlSessionFactoryBean.setDataSource(dataSource);
	sqlSessionFactoryBean.setConfigLocation("classpath:/album/mybatis/SqlMapConfig.xml");
	sqlSessionFactoryBean.setMapperLocations("classpath:/album/mybatis/album.xml");
	ref : 객체를 넣을 때 사용하는,,
	value는 속성으로도 넣을 수 있지만, 자식태그로도 넣을 값을 설정 할 수 있다.
	
	org.mybatis.spring.SqlSessionFactoryBean -> dependency 설정했던 mybatis관련 설정에 있다. 
	 -->
	 
	<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"/>
		<property name="configLocation" value="classpath:/album/mybatis/SqlMapConfig.xml"/>
		<property name="mapperLocations">
			<value>classpath:/album/mybatis/album.xml</value>
		</property>
	</bean>
	
	<!-- 
	org.mybatis.spring.SqlSessionTemplate sqlSessionTemplate 
	= new org.mybatis.spring.SqlSessionTemplate(sqlSessionFactoryBean);
	 -->
	
	<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="sqlSessionFactoryBean"/>
	</bean>	
	

	-> 즉, 첫번째 객체는 두번째 객체를 만들기 위해 사용되고,
	두번째 객체는 세번째 객체를 만들기 위해서 사용이 되고 있다.
	실제 필요한 것은 세번째 객체다.
</beans> 


![4](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/6c15f71a-26b8-4ba5-9679-8a7307a5724b)
→ 패키지와 파일을 만들어주어야 한다.

![5](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/008a56db-6c9d-41ad-9644-6ea9d1522e76)  ![Untitled (1)](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/95b96827-e1b1-4e94-8b9c-732c3a5203dc)


그리고 아래 링크에 들어가서

https://mybatis.org/mybatis-3/ko/getting-started.html

### 4. SqlMapConfig.xml (doctype 설정~ <configuration> 설정)
![6](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/b9cac457-af7d-4992-aaf8-b0574670a6ee)


SqlMapConfig.xml 파일에 복사

?xml version="1.0" encoding="UTF-8"?

!-- SqlMapConfig.xml --

!DOCTYPE configuration

  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  
  "https://mybatis.org/dtd/mybatis-3-config.dtd"
  
  
  
configuration

configuration

### 5. album.xml (mapper화일 doctype ~ <mapper>)
![8](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/eca97d13-af32-4366-9ee7-bfedb531bc3a)

album.xml 파일에 복사


### web.xml 파일에 아래 작성
<!-- 여러가지 ab 요청 -->
	<servlet>
		<servlet-name>album</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/album-servlet.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>album</servlet-name>
		<url-pattern>*.ab</url-pattern>
	</servlet-mapping>


### 6. album-servlet.xml
![9](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/0c42c94f-76f2-4da9-a06f-2cb84d5e3d98)

네임스페이스에서 위에꺼 클릭 후 아래 코드 작성함

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.1.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.1.xsd">

	<!-- 유효성검사할때 필요하당 -->
	<mvc:annotation-driven/> 
	
	<!-- view의 위치 -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/album/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
	
	<!-- album 패키지를 보면 @Controller가 있을것임~ -->
	<context:component-scan base-package="album"/>
	
</beans>


<!-- album-servlet.xml -->


### common 파일생성함
![10](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/71bbcca5-26c0-4f1c-a291-f76331bd0beb)

앨범패키지로 위에서 설정했으므로 album.controller 패키지에 컨트롤러를 넣는다

→ 위 패키지도 앨범패키지가된다.


## 컨트롤러 생성

album.controller 패키지

→ 아까 컨트롤러가 album에 있다고 작성해놨었음.

![Untitled](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/f4c9c03e-cc9e-4875-9c12-584798f8cd94)

AlbumListController.java

@Controller

public class AlbumListController {
	
	private String command = "/list.ab";
	private String getPage = "albumList";
	
	@RequestMapping(value=command)
	public String doAction() {
		
		
		return getPage; // /WEB-INF/album/albumList.jsp -> 폴더랑 파일둘다 생성
	}
}

→ The value for annotation attribute RequestMapping.value must be a constant expression

에러가 뜸. RequestMapping.value는 **반드시 상수**여야 하므로  아래처럼 수정

그리고

앨범 파일과 jsp 파일 생성해쥼

![Untitled (1)](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/af4b106d-330c-49b2-8be2-7608fa2a3841)

### model 생성

![Untitled (2)](https://github.com/dongjun-Lim96/Spring_MyBatis_ex1/assets/121374440/abb94cd8-648c-4150-8773-1fa254e9561f)

AlbumBean.java


public class AlbumBean {

	private int num;
 
	private String title;
 
	private String singer;
 
	private String price; // 유효성검사를 할건데, 유효성검사할때는 반드시 String 이어야한다!!
 
	private String day;
	
	setter, getter, 생성자 생략함
}

- 유효성검사를 할건데, 유효성검사할때는 반드시 String 이어야한다!!
- 그동안은 bean의 이름과 컬럼이름이 달라도 되었지만 mybatis를 사용하기 위해서는 반드시 똑같아야한다.


AlbumDao.java

// AlbumDao myAlbumDao = new AlbumDao();

@Component("myAlbumDao")

public class AlbumDao {
	
	// mapper화일, 즉 album.xml; 에 있는 namespace 속성의 값 album.AlbumBean 똑같이 작성
	private String namespace = "album.AlbumBean";
	
	// root-context.xml의 맨 마지막에 보면 SqlSessionTemplate 객체를 생성했었다.
	// 따라서 여기서 새로 생성하는 것이 아닌, 아까 만든 객체를 여기에 주입해야한다. - @Autowired 어노테이션 사용함
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public AlbumDao() {
		System.out.println("AlbumDao()");
	}
	
	
	// 
	public List<AlbumBean> getAlbumList(){
		
		List<AlbumBean> lists = new ArrayList<AlbumBean>();
		lists = sqlSessionTemplate.selectList(namespace + ".GetAlbumList"); // select 에서 ArrayList 로 가져오는 메서드
		System.out.println("lists.size() : " + lists.size());
		return lists;
	}
}

- 객체생성하는 @Component 어노테이션 작성함
- album.xml; 에 있는 namespace 속성의 값 album.AlbumBean 똑같이 작성
- root-context.xml의 맨 마지막에 보면 SqlSessionTemplate 객체를 생성했었는데 해당 객체를 주입한다



### 다시 mapper 화일로 , ,
!
	아까 다오에서 작성했던 GetAlbumList id에 복붙 ㄱㄱ 
	만들객체는 resultType 에
	
	select 태그안에서 select 작업을한다.
	주의할점!!! ; 세미콜론 절대 작성 ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ

<!-- mapper의 공간 이름은 album.AlbumBean 이며, -->
<mapper namespace="album.AlbumBean">

	<!-- id가 GetAlbumList 이다. -->
	<!-- album.model 의 AlbumBean 클래스. 이건 아무거나쓰면 안되고 파일대로 맞춰주어야 한다! -->
	<select id="GetAlbumList" resultType="album.model.AlbumBean">
		select * from albums
		order by num desc
	
	</select>
</mapper>

<!-- 
상관 ㄴㄴ a.b.c 도 가넝
AlbumBean 객체로 만들고
-> resultType="album.model.AlbumBean"

알아서 ArrayList로 넣는다.
 -->


### ~ 흐름정리~
목록보기 (추가하기 클릭 ) 

→ insert.ab GET방식 요청 

→ AlbumInsertController.java 

→ albumInsertForm.jsp 

→ 항목입력 후 추가하기 클릭 

→ insert.ab POST방식 요청 

→ AlbumInsertController.java 

→ 객체의 유효성검사에 따른 (hasError) 결과 처리

→ 올바르게 객체가 생성되면 해당 객체를 가지고 다오클래스의 insert 메서드를 호출한다

→ AlbumDao  커맨드 객체가 넘어온다.

→ insertAlbum 메서드안에 album 객체가 들어옴

→ sqlSessionTemplate.insert 의 네임스페이스와 id

→ album.xml 파일에 따라 sql 처리 후 돌아감

→ 다오 메서드 return cnt;

→ 컨트롤러 cnt로 옴





