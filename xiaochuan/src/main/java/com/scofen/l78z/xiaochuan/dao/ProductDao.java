package com.scofen.l78z.xiaochuan.dao;

import com.scofen.l78z.xiaochuan.dao.dataObject.CategoryDO;
import com.scofen.l78z.xiaochuan.dao.dataObject.ProductDO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 2023/9/3 8:34 PM
 **/
public interface ProductDao extends JpaRepository<ProductDO, Long> {



    @Override
    default <S extends ProductDO> Optional<S> findOne(Example<S> example) {
        List<S> result = this.findAll(example);
        if (CollectionUtils.isEmpty(result)){
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }




}
