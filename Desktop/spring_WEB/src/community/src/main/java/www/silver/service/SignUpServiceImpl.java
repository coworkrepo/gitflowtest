package www.silver.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.silver.dao.IF_signUpDAO;
import www.silver.vo.MemberVO;

@Service
public class SignUpServiceImpl implements IF_signUpService{
	
	@Inject
	IF_signUpDAO signupdao;

	@Override
	@Transactional
	public void insert(MemberVO membervo) {
		signupdao.insertAccount(membervo);
		
	}
	
	
	

}
