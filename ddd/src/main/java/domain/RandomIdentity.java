package domain;

/**
 * @Description: TODO
 * @Author gaoying
 * @Date 3/20/22 5:37 PM
 **/
interface RandomIdentity<T> extends Identity{
    T next();
}
