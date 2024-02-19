package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.domain.보험료결제됨;
import untitled.domain.청약상태변경됨;
import untitled.회원Application;

@Entity
@Table(name = "회원_table")
@Data
//<<< DDD / Aggregate Root
public class 회원 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long usrId;

    private String usrNm;

    private Long prdId;

    private String prdStatus;

    private String prdNm;

    @PostPersist
    public void onPostPersist() {
        보험료결제됨 보험료결제됨 = new 보험료결제됨(this);
        보험료결제됨.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        청약상태변경됨 청약상태변경됨 = new 청약상태변경됨(this);
        청약상태변경됨.publishAfterCommit();
    }

    public static 회원Repository repository() {
        회원Repository 회원Repository = 회원Application.applicationContext.getBean(
            회원Repository.class
        );
        return 회원Repository;
    }
}
//>>> DDD / Aggregate Root
