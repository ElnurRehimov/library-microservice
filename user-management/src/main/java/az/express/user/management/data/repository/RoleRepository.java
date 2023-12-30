package az.express.user.management.data.repository;

import az.express.user.management.data.entity.RoleEntity;
import az.express.user.management.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(Roles roleName);
}
