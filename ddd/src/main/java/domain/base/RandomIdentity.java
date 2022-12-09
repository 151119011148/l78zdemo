package domain.base;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/22/22 10:51 AM
 **/
interface RandomIdentity<T> extends Identity<T> {
    /**
     * 获取下一个ID
     * @return
     */
    T next();
}
