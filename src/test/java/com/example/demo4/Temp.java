package com.example.demo4;

public class Temp extends Father {
    private int id;
    private String name;

    public Temp(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("有参构造");
    }

  /*  public Temp() {
        System.out.println("无参构造");
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Temp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
