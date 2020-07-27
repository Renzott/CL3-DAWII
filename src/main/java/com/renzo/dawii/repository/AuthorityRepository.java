package com.renzo.dawii.repository;

import com.renzo.dawii.bean.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority , Long> {
    Authority findByAuthority(String authy);
}
