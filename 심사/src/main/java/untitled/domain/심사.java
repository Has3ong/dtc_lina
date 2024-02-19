package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.domain.심사승인됨;
import untitled.domain.심사취소됨;
import untitled.domain.청약상태변경됨;
import untitled.심사Application;

@Entity
@Table(name = "심사_table")
@Data
//<<< DDD / Aggregate Root
public class 심사 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long prdId;

    private Long usrId;

    private String prdStatus;

    private String prdNm;

    @PostPersist
    public void onPostPersist() {
        심사승인됨 심사승인됨 = new 심사승인됨(this);
        심사승인됨.publishAfterCommit();

        심사취소됨 심사취소됨 = new 심사취소됨(this);
        심사취소됨.publishAfterCommit();

        청약상태변경됨 청약상태변경됨 = new 청약상태변경됨(this);
        청약상태변경됨.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static 심사Repository repository() {
        심사Repository 심사Repository = 심사Application.applicationContext.getBean(
            심사Repository.class
        );
        return 심사Repository;
    }
}
//>>> DDD / Aggregate Root
