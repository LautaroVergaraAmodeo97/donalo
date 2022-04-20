//package com.DONALO.proyecto.Seguridad;
//
//import com.DONALO.proyecto.entidades.Roles;
//import com.DONALO.proyecto.entidades.Usuario;
//import com.DONALO.proyecto.repositorios.UsuarioRepositorio;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
//    @Autowired
//    private UsuarioRepositorio userRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) {
//        Usuario user = userRepository.findByUsername(username);
//        if (user == null) throw new UsernameNotFoundException(username);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Roles role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getClave(), grantedAuthorities);
//    }
//}