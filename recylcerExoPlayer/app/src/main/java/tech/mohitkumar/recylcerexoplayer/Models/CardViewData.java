package tech.mohitkumar.recylcerexoplayer.Models;

/**
 * Created by mohitkumar on 14/06/17.
 */

public class CardViewData {

    String links,name,tag1,tag2,nolikes,noh4u,nocomments,timeelapsed,noviews;

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getNolikes() {
        return nolikes;
    }

    public void setNolikes(String nolikes) {
        this.nolikes = nolikes;
    }

    public String getNoh4u() {
        return noh4u;
    }

    public void setNoh4u(String noh4u) {
        this.noh4u = noh4u;
    }

    public String getNocomments() {
        return nocomments;
    }

    public void setNocomments(String nocomments) {
        this.nocomments = nocomments;
    }

    public String getTimeelapsed() {
        return timeelapsed;
    }

    public void setTimeelapsed(String timeelapsed) {
        this.timeelapsed = timeelapsed;
    }

    public String getNoviews() {
        return noviews;
    }

    public void setNoviews(String noviews) {
        this.noviews = noviews;
    }

    public CardViewData(String links, String name, String tag1, String tag2, String nolikes, String noh4u, String nocomments, String timeelapsed, String noviews) {
        setLinks(links);
        setName(name);
        setTag2(tag2);
        setNocomments(nocomments);
        setTag1(tag1);
        setNoh4u(noh4u);
        setNoviews(noviews);
        setNolikes(nolikes);
        setTimeelapsed(timeelapsed);
    }
}
