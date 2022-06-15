package com.kdt.ggyustagram.tag.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
	@Id
	private Long id;
	private String name;
	private Long tagCount;
}
