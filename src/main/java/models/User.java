package models;

import java.util.Objects;

public class User {

    /**
     * Note: deliberations about static variable for id incrementing for each user.
     * Advantage: automatic id
     * But disadvantage in unit tests and less flexible than "manual" id
     */
    private final long id;
    private String name;


    public User(long id,String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
