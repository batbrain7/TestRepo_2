package tech.mohitkumar.recylcerexoplayer.Models;

/**
 * Created by mohitkumar on 11/06/17.
 */

public class CardDataProvider {

    String name;
    String link;
    String comments;
    String likes;

    public CardDataProvider(String name, String link, String comments, String likes) {
        setName(name);
        setComments(comments);
        setLink(link);
        setLikes(likes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
