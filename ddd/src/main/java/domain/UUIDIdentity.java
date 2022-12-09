package domain;

import java.util.UUID;

/**
 * @Description: TODO
 * @Author gaoying
 * @Date 3/20/22 5:38 PM
 **/
class UUIDIdentity<T> implements RandomIdentity{
    public static String nextId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    @Override
    public Object next() {
        return nextId();
    }

    @Override
    public Object value() {
        return nextId();
    }
}
