//package com.DONALO.proyecto.Seguridad;
//
//import com.DONALO.proyecto.entidades.Usuario;
//import com.DONALO.proyecto.repositorios.RolesRepositorio;
//import com.DONALO.proyecto.repositorios.UsuarioRepositorio;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UsuarioRepositorio userRepository;
//    @Autowired
//    private RolesRepositorio roleRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public void save(Usuario user) {
//        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
//        userRepository.save(user);
//    }
//
//    @Override
//    public Usuario findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//}