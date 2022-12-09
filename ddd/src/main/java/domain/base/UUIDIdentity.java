package domain.base;

import java.util.UUID;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/22/22 10:52 AM
 **/
public class UUIDIdentity<T> implements RandomIdentity<T>{

    private static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    @Override
    public T next() {
        return (T) generate();
    }

    @Override
    public T identity() {
        return (T) generate();
    }
}
