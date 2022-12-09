package domain.base;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @Description: 仓储接口规范
 * @Author gaofeng
 * @Date 3/22/22 5:38 PM
 **/
public interface Repository<E extends AggregateRoot, ID extends Identity> {

    Optional<E> findById(ID id);

    List<E> findAll();

//    List<E> findAllMatching(Criteria criteria);

    boolean exists(ID id);

    void save(E entity);

    void saveAll(Collection<? extends E> entities);

    void remove(ID id);

    void removeAll(Collection<? extends E> entities);

//    void removeAllMatching(Criteria criteria);

}
