package com.cibertec.cliente.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cibertec.cliente.entity.Usuario;

@Service
public class UserService implements UserDetailsService  {

	
	private String URL = "http://localhost:8091";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		RestTemplate rt=new RestTemplate();
		Usuario bean = rt.getForObject(URL+"/usuario/lista/"+username, Usuario.class);
		
		UserDetails userDet=null;
		
		Set<GrantedAuthority> rol=new HashSet<GrantedAuthority>();
		rol.add(new SimpleGrantedAuthority(bean.getTipo().getNombre()));
		
		userDet=new User(username, bean.getPassword(),rol);
		
		return userDet;
	}

}
