package com.moroTechLibrary.library.dao.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonDAO, Long> {
}
