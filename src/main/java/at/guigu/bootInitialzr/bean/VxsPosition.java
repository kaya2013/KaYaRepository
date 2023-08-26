package at.guigu.bootInitialzr.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VxsPosition {
    private Long vxsID;
    private String desc;
    private Double Price;
    private Date date;
}
