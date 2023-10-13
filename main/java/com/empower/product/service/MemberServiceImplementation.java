package com.empower.product.service;


import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.empower.product.entity.AdminLogin;
import com.empower.product.entity.Login;
import com.empower.product.entity.Member;
import com.empower.product.entity.MemberCode;
import com.empower.product.repository.AdminLoginRepository;
import com.empower.product.repository.LoginRepository;
import com.empower.product.repository.MemberRepository;


@Service
public class MemberServiceImplementation implements MemberService {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	LoginRepository loginRepository;
	@Autowired
	AdminLoginRepository adminLoginRepository;
	
//	@Autowired
//	private EmailServiceImpl emailService;

	@Override
	public MemberCode addMember(Member member) {
		MemberCode memberCode = new MemberCode(0,"Member not Found", null);
		if(!memberRepository.existsById(member.getMemberId())) {
			memberRepository.save(member);
		   Login login = new Login(member.getUsername(), member.getPassword());
		   loginRepository.save(login);
		   
		   generateOtp(member.getEmail(),member.getUsername());
			memberCode.setStatus(1);
			memberCode.setMessage("Member Added");
			memberCode.setMember(member);
		}
		
		return memberCode;
	}

	@Override
	public MemberCode getLoginDetails(Login l) {
		MemberCode memberCode = new MemberCode(0,"Member not found", null);
		// TODO Auto-generated method stub            
		Optional<Login> x = loginRepository.findById(l.getUsername()) ;

		Login log = x.get();
		
		if(x.isPresent() && log.getPin()==1)
		{
			if(log.getPassword().equals(l.getPassword())) {
				memberCode.setMessage("Login Successfull");
				memberCode.setLogin(l);
				memberCode.setStatus(1);
				
			}
					
		}

		return memberCode;
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		List<Member> members = memberRepository.findAll();
		return members;
	}

	@Override                                        // l =username, password
	public MemberCode getAdminLoginDetails(AdminLogin l) {
		MemberCode memberCode = new MemberCode(0, null,"Admin Not found");
		// TODO Auto-generated method stub
		                                                         //admin
		Optional<AdminLogin> x = adminLoginRepository.findById(l.getUsername()) ;

		AdminLogin log = x.get();
		
		//log = database object   l =frontend object
		if(x.isPresent()  )
		{
			if(log.getPassword().equals(l.getPassword())) {
				memberCode.setMessage("Login Successfull");
				memberCode.setAdminLogin(l);
				memberCode.setStatus(1);
				
			}
					
		}

		return memberCode;
	}

	@Override
	public List<Member> getMemberForLogin(String username) {
		// TODO Auto-generated method stub
		List<Member> member = memberRepository.findByUsername(username);
		return member;
	}

	@Override
	public boolean checkverify(String username) {
		
		Login x = loginRepository.findByUsername(username);
		
		if(x == null) {
			return false;
		}
		
		System.out.println(username);
		//Login y = x.get();
		x.setPin(1);
		loginRepository.save(x);
		return true;
	}
	
	// email service
	
	public String generateOtp(String email, String username)
	{
		//generate otp
		int noOfDigits=6;
		String otp="";
		for(int i=0;i<noOfDigits;i++)
		{	
			otp+=(int)(Math.random()*10);		
		}
	//	emailService.



		sendSimpleMessage(email, "Otp",otp +"is your otp");


	return"two";

	}
	
	 @Autowired
	    private JavaMailSender emailSender;

	    public void sendSimpleMessage(
	      String to, String subject, String text) {
	        
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("librarymanagement28@gmail.com");
	        message.setTo(to); 
	        message.setSubject(subject); 
	        message.setText(text);
	        emailSender.send(message);
	        
	    }

	
	
	}


	
/*
 
  
  query part in jpa repository..
  @Query("from Employee where deptid = :x")//Employee is the entity clssname
   List<Employee> f1(@param("x") int x);



   List<Employee> findBydeptid(int y);
   //deptid  is non static variable name in theEntity class.
    
  */
	


