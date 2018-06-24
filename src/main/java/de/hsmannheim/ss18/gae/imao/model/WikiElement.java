package de.hsmannheim.ss18.gae.imao.model;

import de.hsmannheim.ss18.gae.imao.model.enums.EWikiKathegorie;

public class WikiElement {

    public EWikiKathegorie getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getTags() {
        return tags;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        this.count++;
        return content;
    }

    public void setCategory(EWikiKathegorie category) {
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void addTag(String tag){
        String[] newTagsArray = new String[this.tags.length+1];
        for (int i=0;i<this.tags.length;i++){
            newTagsArray[i]=this.tags[i];
        }
        newTagsArray[this.tags.length]=tag;
        this.tags = newTagsArray;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void resetCount(){
        this.count=0;
    }

    private EWikiKathegorie category;
    private String question;
    private String[] tags;
    private int count=0;
    private int id;
    private String content;

    private static int idCounter=0;

    /**
     * Constructor
     * @param question
     * @param content
     * @param category
     * @param tags
     * @param id
     * @param count
     */
    public WikiElement(String question, String content, EWikiKathegorie category,  String[] tags, int id, int count) {
        this.category = category;
        this.question = question;
        this.tags = tags;
        this.content = content;
        this.count=count;
        this.id = id;
    }
    /**
     *Constructor
     * @param question
     * @param content
     * @param category
     * @param tags
     */
    public WikiElement(String question, String content, EWikiKathegorie category,  String[] tags) {
        this.category = category;
        this.question = question;
        this.tags = tags;
        this.content = content;
        this.id = idCounter++;
    }

    /**
     *Constructor
     * @param question
     * @param content
     */
    public WikiElement(String question, String content){
        this(question, content, EWikiKathegorie.SONSTIGES, new String[0]);

    }
}
