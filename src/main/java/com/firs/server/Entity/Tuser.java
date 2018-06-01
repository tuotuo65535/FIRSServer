package com.firs.server.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Tuser {
	@Id
	private int sid;
	private String sname;
	private int sage;
}
