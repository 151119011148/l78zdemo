package domain.base;

import java.io.Serializable;

/**
 * @Description:
 * 值对象本身是无状态的，不可变，没有唯一标识，从这个层面上来讲，值对象可以理解为实际的Entity对象的一个属性的结合，该值对象附属在一个实际的实体对象上面。
 * 值对象本省不存在一个独立的生命周期，也一般不会产生独立的行为
 * @Author gaofeng
 * @Date 3/20/22 5:41 PM
 **/
public interface ValueObject<T> extends Serializable {

  /**
   * Value objects compare by the values of their attributes, they don't have an identity.
   *
   * @param other The other value object.
   * @return <code>true</code> if the given value object's and this value object's attributes are the same.
   */
  boolean sameValueAs(T other);

  /**
   * Value objects can be freely copied.
   *
   * @return A safe, deep copy of this value object.
   */
  T copy();

}