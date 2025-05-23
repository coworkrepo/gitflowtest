package www.silver.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import www.silver.vo.MemberVO;

@Repository
public class SignUpDAOImpl implements IF_signUpDAO{
	@Inject
	private SqlSession sqlSession;

	@Override
	public void insertAccount(MemberVO membervo) {
		System.out.println("회원가입 DAO insertAccount 정상 호출함 ");
		sqlSession.insert("www.silver.dao.IF_signUpDAO.insertone", membervo);
		
	}
}
