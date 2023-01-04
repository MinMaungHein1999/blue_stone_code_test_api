package com.example.demo.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="phone_no",nullable=false)
	private String phoneNo;
	
	@Column(name="email",nullable=false)
	private String email;

	@Column(name = "mail_status")
	private boolean mailStatus;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_date", nullable = false, length = 10)
	private Date createdDate;

	
	
	

}
