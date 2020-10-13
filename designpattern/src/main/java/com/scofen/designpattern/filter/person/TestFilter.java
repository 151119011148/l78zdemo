package com.scofen.designpattern.filter.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by  GF  in  10:26 2019/3/14
 * Description:
 * Modified  By:
 */
public class TestFilter {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Robert","Male", "Single"));
        persons.add(new Person("John","Male", "Married"));
        persons.add(new Person("Laura","Female", "Married"));
        persons.add(new Person("Diana","Female", "Single"));
        persons.add(new Person("Mike","Male", "Single"));
        persons.add(new Person("Bobby","Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);
        Criteria singleAndFemale = new AndCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));

        System.out.println("\nSingle And Females: ");
        printPersons(singleAndFemale.meetCriteria(persons));

        //过滤模式的实现在java8里面有典型的应用方法就是分组操作，可以根据指定的指标进行分组筛选。
        System.out.println();
        Map<String, List<Person >> groupMap = persons.stream().collect(Collectors.groupingBy(Person :: getGender));
        groupMap.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });
    }

     private static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    +", Gender : " + person.getGender()
                    +", Marital Status : " + person.getMaritalStatus()
                    +" ]");
        }



    }
}
