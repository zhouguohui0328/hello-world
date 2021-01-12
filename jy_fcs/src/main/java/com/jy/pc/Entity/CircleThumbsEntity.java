package com.jy.pc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

//帖子点赞表
@Entity
@Table(name = "sas_circle_thumbs")
public class CircleThumbsEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(strategy = "uuid", name = "uuid")
	@Column(length = 36)
	private String id;// 主键id
	// 外键id - 帖子信息
	@ManyToOne(optional = false)
	@JoinColumn(name = "circleId", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private PostInfoEntity postInfoEntity;

	@Column(columnDefinition = "varchar(36) default '' comment '点赞用户id'")
	private String thumbsUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PostInfoEntity getPostInfoEntity() {
		return postInfoEntity;
	}

	public void setPostInfoEntity(PostInfoEntity postInfoEntity) {
		this.postInfoEntity = postInfoEntity;
	}

	public String getThumbsUserId() {
		return thumbsUserId;
	}

	public void setThumbsUserId(String thumbsUserId) {
		this.thumbsUserId = thumbsUserId;
	}

}