package com.codeenginestudio.elearning.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class QuestionOfAssessmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long questionid;

	@Column
	private int numericalorder;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "questiontypeid", referencedColumnName = "questiontypeid")
	private QuestionTypeEntity questiontype;

	@Lob
	@Column( length = 100000)
	private String content;

	@Lob
	@Column( length = 100000)
	private String options;

	@Column
	private String correctanswer;

	@Column
	private float score;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "assessmentid", referencedColumnName = "assessmentid")
	private AssessmentEntity assessment;

	public QuestionOfAssessmentEntity(Long questionid, int numericalorder, QuestionTypeEntity questiontype,
			String content, String options, String correctanswer, float score, AssessmentEntity assessment) {
		super();
		this.questionid = questionid;
		this.numericalorder = numericalorder;
		this.questiontype = questiontype;
		this.content = content;
		this.options = options;
		this.correctanswer = correctanswer;
		this.score = score;
		this.assessment = assessment;
	}

	public QuestionOfAssessmentEntity() {
		super();
	}

	public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	public int getNumericalorder() {
		return numericalorder;
	}

	public void setNumericalorder(int numericalorder) {
		this.numericalorder = numericalorder;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getCorrectanswer() {
		return correctanswer;
	}

	public void setCorrectanswer(String correctanswer) {
		this.correctanswer = correctanswer;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public QuestionTypeEntity getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(QuestionTypeEntity questiontype) {
		this.questiontype = questiontype;
	}

	public AssessmentEntity getAssessment() {
		return assessment;
	}

	public void setAssessment(AssessmentEntity assessment) {
		this.assessment = assessment;
	}

}
