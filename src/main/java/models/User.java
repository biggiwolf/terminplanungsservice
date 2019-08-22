package models;

import java.util.Objects;

public class User {

    /**
     * Note: deliberations about static variable for id incrementing for each user.
     * Advantage: automatic id
     * But disadvantage in unit tests and less flexible than "manual" id
     */
    private long id;
    private String name;
    private String mail;


    public User(){
        super();
    }

    public User(long id,String name){
        this.id = id;
        this.name = name;
        this.mail = null;
    }

    public User(long id, String name, String mail){
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public void setId(long id){
        this.id = id;
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

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, mail);
    }

    @Override
    public String toString(){
        String result =  "id: " + id + " , name: " + name;
        if(mail != null)
            result += " , mail: " + mail;
        return result;
    }
}
