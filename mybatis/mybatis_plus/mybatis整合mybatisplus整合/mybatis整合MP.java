//1.第一步，将UserMapper继承BaseMapper，将拥有了BaseMapper中的所有方法：

public interface UserMapper extends BaseMapper<User> {
	List<User> findAll();
}


//2.第二步，使用MP中的MybatisSqlSessionFactoryBuilder进程构建：
public class TestMybatisPlus {
	@Test
	public void testUserList() throws Exception{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//这里使用的是MP中的MybatisSqlSessionFactoryBuilder
		SqlSessionFactory sqlSessionFactory = new
		MybatisSqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 可以调用BaseMapper中定义的方法
		List<User> list = userMapper.selectList(null);
		for (User user : list) {
			System.out.println(user);
		}
	}
}