package com.allstock.app.services;

import com.allstock.app.controllers.dto.AuthCreatorUserRequest;
import com.allstock.app.controllers.dto.AuthLoginRequest;
import com.allstock.app.controllers.dto.AuthResponse;
import com.allstock.app.persistence.entities.RolesEntity;
import com.allstock.app.persistence.entities.UserEntity;
import com.allstock.app.repositories.RoleRepository;
import com.allstock.app.repositories.UserRepository;
import com.allstock.app.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final UserRepository repository;

    private final JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(RoleRepository roleRepository, UserRepository repository, JwtUtils jwtUtils) {
        this.roleRepository = roleRepository;
        this.repository = repository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findUserEntitiesByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("The user %s does not exist", username)));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority(
                        "ROLE_".concat(role.getRolesEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList
                        .add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnable(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User loged successfuly", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, password,
                userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreatorUserRequest authCreatorUserRequest){
        String username = authCreatorUserRequest.username();
        String password = authCreatorUserRequest.password();
        List<String> roleRequest = authCreatorUserRequest.roleRequest().roleListName();

        Set<RolesEntity> rolesEntitySet = roleRepository.findRolesEntitiesByRolesEnumIn(roleRequest).stream().collect(Collectors.toSet());

        if (rolesEntitySet.isEmpty()){
            throw new IllegalArgumentException("The roles specified does not exist");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(rolesEntitySet)
                .isEnable(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreate = userRepository.save(userEntity);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreate.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRolesEnum().name()))));

        userCreate.getRoles().stream().flatMap(role -> role.getPermissions().stream()).forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContext securityContextHolder = SecurityContextHolder.getContext();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreate, null, authorityList);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(userCreate.getUsername(), "User created successfully", accessToken,true);
        return authResponse;
    }
}
