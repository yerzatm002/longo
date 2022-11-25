package kz.meirambekuly.backendlongo.repositories;

import kz.meirambekuly.backendlongo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}