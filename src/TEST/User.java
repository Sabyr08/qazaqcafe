package TEST;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String sname;

    public User() {
    }

    public User(String name, String sname) {
        this.name = name;
        this.sname = sname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }
}
