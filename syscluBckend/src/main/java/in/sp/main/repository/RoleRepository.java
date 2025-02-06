package in.sp.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findByName(String name);
}
