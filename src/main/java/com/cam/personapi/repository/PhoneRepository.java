package com.cam.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cam.personapi.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
