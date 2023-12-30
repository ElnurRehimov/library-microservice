package az.express.user.management.service;

import az.express.user.management.data.entity.RolePermissionEntity;
import az.express.user.management.data.entity.UserEntity;
import az.express.user.management.data.repository.RolePermissionRepository;
import az.express.user.management.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity userEntity = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            List<GrantedAuthority> authorities = new ArrayList<>();
            List<RolePermissionEntity> permissions = rolePermissionRepository
                    .findAllByRole_Id(userEntity.getRole().getId());
            permissions.forEach(rolePermissionEntity -> {
                authorities.add(new SimpleGrantedAuthority(rolePermissionEntity.getPermissionName()));
            });

            return new User(userEntity.getUsername(),
                    userEntity.getPassword(),
                    authorities);
        };
    }
}
