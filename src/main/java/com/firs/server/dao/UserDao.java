package com.firs.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firs.server.Entity.Tuser;

@Repository
public interface UserDao extends JpaRepository<Tuser, String>{
	Tuser findOneBySname(String sname);
}
