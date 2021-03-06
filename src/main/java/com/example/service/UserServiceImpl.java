package com.example.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.web.dto.UserRegistrationDto;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstname(), registrationDto.getLastname(), registrationDto.getEmail(),registrationDto.getLogin(), passwordEncoder.encode(registrationDto.getPassword()),registrationDto.getTel(),registrationDto.getVille(), Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		List<User> users = userRepository.findAll();
		for (User use : users) {
			
			if(use.getLogin().equals(username))
			{
				
				user=use;
				break;
			}
		}
		if(user==null)
		{
			throw new UsernameNotFoundException("Inavalid Username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles())); //roles
	} 

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}


	@Override
	public User findbymail(String username) {
		User user = userRepository.findByEmail(username);
		return user;
	}


	@Override
	public User updateUser(User user) {
	 return userRepository.save(user);
	}
	
}
