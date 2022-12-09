package domain.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @Description: 操作日期值对象
 * @Author gaofeng
 * @Date 3/27/22 3:43 PM
 **/
@Getter
@Slf4j
@NoArgsConstructor
public class GmtOperationValueObject implements ValueObject<GmtOperationValueObject> {


    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 过期时间
     */
    private Date expiredTime;



    @Override
    public boolean sameValueAs(GmtOperationValueObject other) {
        return false;
    }

    @Override
    public GmtOperationValueObject copy() {
        return null;
    }

    public GmtOperationValueObject(@Nullable Date gmtCreate, @Nullable Date gmtModified, @Nullable Date expiredTime){
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.expiredTime = expiredTime;
    }

    void modified(){
        this.gmtModified = new Date();
    }


}
