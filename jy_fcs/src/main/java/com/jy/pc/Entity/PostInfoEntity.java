package com.jy.pc.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jy.pc.POJO.PostCommentInfoPO;

//帖子信息表
@Entity
@Table(name = "sas_post_info")
public class PostInfoEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	@Column(length = 36)
	private String name;// 标题名称
	@Column(length = 36)
	private String code;// 内容
	@Column(length = 36)
	private String parentCode;// 分类Id（分类表编码）
	@Column(length = 1)
	private String auditStatus;// 审核状态（0未审核1审核通过2审核驳回）
	@Column(length = 36)
	private String auditOptinion;// 审核意见
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 发布时间
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;// 修改时间
	@Column(length = 36)
	private String visibility;// 可见程度（0自己可见1全部）
	@Column(length = 36)
	private String author;// 作者
	@Column(length = 36)
	private String createUser;// 发布人
	@Column(length = 36)
	private String createUserId;// 发布人ID
	@Column(length = 36)
	private String auditUser;// 审核人
	@Column
	private String status; // 显示状态
	// 拒绝原因
	@Column
	private String reason;
	// 贴子下的评论
	@Transient
	private List<PostCommentInfoPO> commentList;// 文章列表
	@Transient
	private int commentSize;
	@Transient
	private String time;

	public int getCommentSize() {
		return commentSize;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setCommentSize(int commentSize) {
		this.commentSize = commentSize;
	}

	public String getReason() {
		return reason;
	}

	public List<PostCommentInfoPO> getCommentList() {
		return commentList;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public void setCommentList(List<PostCommentInfoPO> commentList) {
		this.commentList = commentList;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditOptinion() {
		return auditOptinion;
	}

	public void setAuditOptinion(String auditOptinion) {
		this.auditOptinion = auditOptinion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
}