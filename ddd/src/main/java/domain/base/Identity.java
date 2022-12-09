package domain.base;

import java.io.Serializable;

/**
 * @Description: Identity
 * @Author gaofeng
 * @Date 3/22/22 10:50 AM
 **/
interface Identity<T> extends Serializable {
    /**
     * 获取当前ID
     * @return
     */
    T identity();
}
