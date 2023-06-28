package com.tismart.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tismart.hospital.dao.IUserRepository;
@Service
public class IUserService implements UserDetailsService{
	@Autowired
	private IUserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user= userRepo.findByNombreUsuario(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

}
