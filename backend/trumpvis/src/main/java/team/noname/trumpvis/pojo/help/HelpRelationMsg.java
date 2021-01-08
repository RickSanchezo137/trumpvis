package team.noname.trumpvis.pojo.help;

import java.util.List;

public class HelpRelationMsg {
    private List<HelpRelationPoint> points;
    private List<HelpRelationLink> links;
    private List<HelpRelationCategory> categories;

    public List<HelpRelationPoint> getPoints() {
        return points;
    }

    public void setPoints(List<HelpRelationPoint> points) {
        this.points = points;
    }

    public List<HelpRelationLink> getLinks() {
        return links;
    }

    public void setLinks(List<HelpRelationLink> links) {
        this.links = links;
    }

    public List<HelpRelationCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<HelpRelationCategory> categories) {
        this.categories = categories;
    }
}
