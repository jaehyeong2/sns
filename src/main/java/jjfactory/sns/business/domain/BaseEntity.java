package jjfactory.sns.business.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor
@Getter
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    private LocalDateTime deleteDate;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus = DeleteStatus.NON;

    public void delete() {
        this.deleteStatus = DeleteStatus.DELETED;
        this.deleteDate = LocalDateTime.now();
    }
}
