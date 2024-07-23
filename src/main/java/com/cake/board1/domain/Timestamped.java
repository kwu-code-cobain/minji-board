package com.cake.board1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
    JPA를 사용하여 데이터베이스의 엔티티 생성 및 수정 타임스태프를
    자동으로 관리하는 Timesstamped 추상 클래스를 정의한다
    JPA의 Auditing 기능을 사용하여 엔티티가 생성되거나 수정될때 자동으로 기록한다
 */

@Getter
@MappedSuperclass  // 이 클래스가 다른 엔티티 클래스의 부모가 될 수 있음을 나타냄
@EntityListeners(AuditingEntityListener.class)  // Auditing 기능을 추가함

public abstract class Timestamped {
    @CreatedDate  // 엔티티 생성될때의 시간을 자동으로 기록
    @Column(updatable = false)  // 이 필드가 데이터베이스의 not null 컬럼임을 나타냄
    @Temporal(TemporalType.TIMESTAMP) // 이 필드가 타임스태프임을 나타냄
    private LocalDateTime createdAt;

    @LastModifiedDate  // 엔티티 수정될때의 시간을 자동으로 기록
    @Column  // 이 필드가 데이터베이스의 컬럼임을 나타냄
    @Temporal(TemporalType.TIMESTAMP)  // 이 필드가 타임스태프임을 나타냄
    private LocalDateTime modifiedAt;
}

// 굳이 안 나눠도 된다
