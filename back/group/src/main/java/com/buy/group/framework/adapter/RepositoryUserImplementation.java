package com.buy.group.framework.adapter;

import java.util.ArrayList;
import java.util.List;

import com.buy.group.domain.model.User;
import com.buy.group.domain.port.RepositoryUser;
import com.buy.group.framework.adapter.mapper.MapperUser;
import com.buy.group.framework.db_repository.UserDBRepository;
import com.buy.group.framework.entity.EntityRole;
import com.buy.group.framework.entity.EntityUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryUserImplementation implements RepositoryUser, UserDetailsService {

    private UserDBRepository userDBRepository;
    private PasswordEncoder bcryptEncoder;    

    public RepositoryUserImplementation(UserDBRepository userDBRepository) {
        this.userDBRepository = userDBRepository;
    }

    @Override
    public void save(User user) {
        EntityUser entityUser = MapperUser.modelToEntity(user);
        entityUser.encryptPassword(bcryptEncoder);
        this.userDBRepository.save(entityUser);
    }    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityUser user = this.userDBRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<EntityRole> roles = user.getRoles();
        for(EntityRole role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    
}