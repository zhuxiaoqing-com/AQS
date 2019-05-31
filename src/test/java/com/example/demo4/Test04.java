package com.example.demo4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test04 {

    @Test
    public void test01() {
       /* int[] sample = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 1, 0,
                -2, 0, 0};*/

        int[] sample = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2, 1, 0,
                -2, 0, 0};
        List<Student> list = new ArrayList<>();
        for (int i : sample) {
            Student s = new Student();
            s.setId(i);
            list.add(s);
        }

        Collections.sort(list, new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                int id1 = o1.getId();
                int id2 = o2.getId();
                return id1 > id2 ? 1 : -1;// throw java.lang.IllegalArgumentException
               // return id1==id2?0:id1 > id2 ? 1 : -1;
            }
        });
        System.out.println();
    }

    private class Student {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }
}




