package airplane.domain;

import airplane.AdminApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Admin_table")
@Data
//<<< DDD / Aggregate Root
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long airPlaneId;

    private Integer seatQty;
    
    @PostPersist
    public void onPostPersist() {
    	AirplaneCreated airplaneCreated = new AirplaneCreated(
            this
        );
    	airplaneCreated.publishAfterCommit();
    }

    public static AdminRepository repository() {
        AdminRepository adminRepository = AdminApplication.applicationContext.getBean(
            AdminRepository.class
        );
        return adminRepository;
    }
}
//>>> DDD / Aggregate Root
