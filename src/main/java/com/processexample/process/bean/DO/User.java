package com.processexample.process.bean.DO;


import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Map;

/**
 * Create by  GF  in  15:56 2018/6/20
 * Description:
 * Modified  By:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="demo_user")
public class User {

    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "姓名必传")
    @Pattern(regexp = "^[\u4E00-\u9FA5]{0,}$",message = "姓名格式不正确")
    private String name;
    @Pattern(regexp = "^[0-9]{17}([0-9]|X)|([0-9]{15})\n$",message = "身份证格式不正确")
    private String cardNo;
    @Length(min = 1,max =1,message = "性别格式不正确")
    private String sex;
    @Email(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*.[a-zA-Z0-9]{2,6}$",message = "邮箱格式不正确")
    private String email;
    private String job;
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|14[5-9]|15[012356789]|166|17[0-8]|18[0-9]|19[89])[0-9]{8}$",message = "手机号格式不正确")
    @Column(name = "phone_no")
    private String phoneNumber;
    @Min(value = 18,message = "未成年")
    @Max(value = 45,message = "too old")
    private Integer age;
    @Pattern(regexp = "^(?:(?!0000)[0-9]{4}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)0229)((0[0-9])|(1[0-9])|(2[0-3]))[0-5][0-9][0-5][0-9]{1}$",message = "手机号格式不正确")
    private String date;


    public static void main(String[] args){
        java.util.regex.Pattern patternEmail = java.util.regex.Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*.[a-zA-Z0-9]{2,6}$");
        boolean whetherValid = patternEmail.matcher("352415401@qq.com").matches();
        System.out.println(whetherValid);

        User user = User.builder()
                .phoneNumber("15111901148")
                .name("高峰")
                .build();
        Map map = new BeanMap(user);
        System.out.println(JSON.toJSON(map));

        User newUser = new User();
        try {
            BeanUtils.populate(newUser, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSON(newUser));



    }





}


