package com.example.megamart;

public class PostModel {
    private  String postID,userID,postText,postImage,postLikes,postComments;
    private long postingTime;

    public PostModel(String postID, String userID, String postText, String postImage, String postLikes, String postComments, long postingTime) {
        this.postID = postID;
        this.userID = userID;
        this.postText = postText;
        this.postImage = postImage;
        this.postLikes = postLikes;
        this.postComments = postComments;
        this.postingTime = postingTime;
    }

    public PostModel(){

    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setPostLikes(String postLikes) {
        this.postLikes = postLikes;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public void setPostComments(String postComments) {
        this.postComments = postComments;
    }

    public void setPostingTime(long postingTime) {
        this.postingTime = postingTime;
    }
}
