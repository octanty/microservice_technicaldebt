package TDMeasurement.MeasurementService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class GenericStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genericStatisticID;
    private int fileAmount;
    private int LOCNumber;
    private int commentNumber;

    public Long getGenericStatisticID() {
        return genericStatisticID;
    }

    public void setGenericStatisticID(Long genericStatisticID) {
        this.genericStatisticID = genericStatisticID;
    }

    public int getfileAmount() {
        return fileAmount;
    }

    public void setFileAmount(int fileAmount) {
        this.fileAmount= fileAmount;
    }

    public int getLOCNumber() {
        return LOCNumber;
    }

    public void setLOCNumber(int LOCNumber) {
        this.LOCNumber= LOCNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber= commentNumber;
    }
}
