package com.example.megamart;

public class UserModel {
    private  String usrName;
    private  String userName;
   private  String usrEmail;
    private  String usrPassword;
   private  String usrPhNumber;
  private String usrBio;
    private  String usrProfile;
  private  String usrCover;


    public void setUsrProfile(String usrProfile) {
        this.usrProfile = usrProfile;
    }
    public void usrCover(String usrCover) {
        this.usrCover = usrCover;
    }

    public void setusrBio(String usrBio) {
        this.usrBio = usrBio;
    }

    public void setusrPhNumber(String usrPhNumber) {
        this.usrPhNumber = usrPhNumber;
    }


    public void setusrPassword(String usrPassword) {
        this.usrPassword =usrPassword;
    }

    public void setusrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public void setusrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPhNumber() {
        return usrPhNumber;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUsrBio() {
        return usrBio;
    }

    public String getUsrCover() {
        return usrCover;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public String getUsrName() {
        return usrName;
    }

public UserModel()
{

}
    public UserModel(String usrName, String userName, String usrEmail, String usrPassword, String usrPhNumber, String usrBio, String usrProfile, String usrCover) {
        this.usrName = usrName;
        this.userName = userName;
        this.usrEmail = usrEmail;
        this.usrPassword = usrPassword;
        this.usrPhNumber = usrPhNumber;
        this.usrBio = usrBio;
        this.usrProfile = usrProfile;
        this.usrCover = usrCover;
    }
}

