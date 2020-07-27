package com.renzo.dawii.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Authority implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String authority;
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"id=" + id +
				", authority='" + authority + '\'' +
				'}';
	}
}
