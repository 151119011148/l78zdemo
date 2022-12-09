package domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @Description: TODO
 * @Author gaofeng
 * @Date 3/22/22 3:26 PM
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerValueObject implements ValueObject<OwnerValueObject> {

    /**
     * 归属类型
     * @see
     */
    String ownerType;

    /**
     * 归属id
     */
    String ownerId;

    /**
     * userId
     */
    String userId;

    /**
     * 组织id
     */
    String groupId;

    /**
     * tenantId
     */
    String tenantId;


    @Override
    public boolean sameValueAs(OwnerValueObject other) {
        return Objects.equals(this.getOwnerId(), other.getOwnerId())
                && Objects.equals(this.getGroupId(), other.getGroupId());
    }

    @Override
    public OwnerValueObject copy() {
        return null;
    }
}
