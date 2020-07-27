package com.renzo.dawii.service;

import com.renzo.dawii.bean.Authority;
import com.renzo.dawii.bean.Usuario;
import com.renzo.dawii.repository.AuthorityRepository;
import com.renzo.dawii.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthorityRepository authorityRepository;

    @Autowired
    public UsuarioService(BCryptPasswordEncoder bCryptPasswordEncoder, AuthorityRepository authorityRepository){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    public Usuario findbyName(String nombre) {
        return repository.findByNombre(nombre).get();
    }


    @Override
    public Usuario getUsuario(int id) {
        return repository.findById(id).get();
    }

    @Override
    public String adminUsuario(Usuario u) {
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        Authority userRole = authorityRepository.findByAuthority("ADMIN");
        u.setAuthority(new HashSet<Authority>(Arrays.asList(userRole)));
        return repository.save(u).toString();
    }

    @Override
    public Usuario saveUser(Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        Authority userRole = authorityRepository.findByAuthority("USER");
        usuario.setAuthority(new HashSet<Authority>(Arrays.asList(userRole)));
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario user =  repository.findByNombre(nombre).orElseThrow( () -> new UsernameNotFoundException(nombre));
        List<GrantedAuthority> authorities = getUserAuthority(user.getAuthority());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Authority> authorities) {
        Set<GrantedAuthority> authys = new HashSet<>();
        for (Authority a : authorities) {
            authys.add(new SimpleGrantedAuthority(a.getAuthority()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Usuario user, List<GrantedAuthority> authorities) {
        return new User(user.getNombre(), user.getPassword(),
                true, true, true, true, authorities);
    }

}
