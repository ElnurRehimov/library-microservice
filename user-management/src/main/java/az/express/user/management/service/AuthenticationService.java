package az.express.user.management.service;

import az.express.user.management.data.entity.RoleEntity;
import az.express.user.management.data.entity.RolePermissionEntity;
import az.express.user.management.data.entity.UserEntity;
import az.express.user.management.data.repository.RolePermissionRepository;
import az.express.user.management.data.repository.RoleRepository;
import az.express.user.management.data.repository.UserRepository;
import az.express.user.management.exception.RoleNotFoundException;
import az.express.user.management.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;


    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = UserEntity.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        RoleEntity roleEntity = roleRepository.findByRoleName(Roles.USER)
                .orElseThrow(() -> new RoleNotFoundException("ROLE_NOT_FOUND", "role was not found"));
        user.setRole(roleEntity);
        userRepository.save(user);

        var jwt = jwtService.generateToken(getClaims(user));
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(getClaims(user));
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private UserClaims getClaims(UserEntity user) {
        UserClaims userClaims = new UserClaims();
        userClaims.setUsername(user.getUsername());
        List<String> permissions = rolePermissionRepository
                .findAllByRole_Id(user.getRole().getId()).stream()
                .map(RolePermissionEntity::getPermissionName)
                .collect(Collectors.toList());
        Map<String, Object> permissionMap = new HashMap<>();
        permissionMap.put("permissions", permissions);
        userClaims.setPermissions(permissionMap);
        return userClaims;
    }
}
