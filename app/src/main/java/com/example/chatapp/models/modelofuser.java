package com.example.chatapp.models;

public class modelofuser {
    String name, lastmessgae, userid ;
    int profilepic;



    public modelofuser(String name, String lastmessgae, String userid, int profilepic) {
        this.name = name;
        this.lastmessgae = lastmessgae;
        this.userid = userid;
        this.profilepic = profilepic;

    }
    public  modelofuser(){


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastmessgae() {
        return lastmessgae;
    }

    public void setLastmessgae(String lastmessgae) {
        this.lastmessgae = lastmessgae;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(int profilepic) {
        this.profilepic = profilepic;
    }
}




