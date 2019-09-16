package com.thamouda.gestion_magasin.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thamouda.gestion_magasin.models.User;

@Repository
public interface UserRepository extends  CrudRepository<User, Integer> {
	

}
