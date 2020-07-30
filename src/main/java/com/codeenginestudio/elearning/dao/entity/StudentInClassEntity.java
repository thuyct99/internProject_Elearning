package com.codeenginestudio.elearning.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "class_students")
public class StudentInClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idrow;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "studentid", referencedColumnName = "userid")
	private UserEntity student;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "classid", referencedColumnName = "classid")
	private ClassEntity classForeign;

	public StudentInClassEntity() {
		super();
	}

	public UserEntity getStudent() {
		return student;
	}

	public void setStudent(UserEntity student) {
		this.student = student;
	}

	public ClassEntity getClassForeign() {
		return classForeign;
	}

	public void setClassForeign(ClassEntity classForeign) {
		this.classForeign = classForeign;
	}

	public void setIdrow(Long idrow) {
		this.idrow = idrow;
	}

	public Long getIdrow() {
		return idrow;
	}

}
