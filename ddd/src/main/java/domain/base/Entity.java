package domain.base;

/**
 * @Description:
 * 实体是由唯一标识和一系列属性构造的类，实体的核心是用唯一标识来定义的，而不是通过属性来定义的。即使属性完全相同也可能是两个不同的对象。
 * 实体本身是有状态的，有生命周期，实体本身会提箱相关的业务行为，业务行为会使实体属性或状态发生改变和影响
 * @Author gaofeng
 * @Date 3/20/22 5:41 PM
 **/
public interface Entity<T> extends Identity<T>{

    boolean sameIdentityAs(T other);

}
