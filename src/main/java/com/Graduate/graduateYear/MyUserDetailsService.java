package com.Graduate.graduateYear;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Graduate.graduateYear.tables.user;
import com.Graduate.graduateYear.tables.userRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	userRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<user> u=userrepository.findByEmail(username);
		if(u.isPresent()==false) throw new UsernameNotFoundException("no such email");
		return  u.map(MyUserDetail::new).get();
	}

}
