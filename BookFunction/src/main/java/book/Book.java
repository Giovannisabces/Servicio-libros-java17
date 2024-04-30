package book;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName = "book")
public class Book {
    @DynamoDBHashKey
    private int id;
    @DynamoDBAttribute
    private String title;
    @DynamoDBAttribute
    private String autor;
    @DynamoDBIndexRangeKey
    private String serie;
    @DynamoDBAttribute
    private String content;
    @DynamoDBAttribute
    private String img;
    
    public Book() {
    }

    public Book(int id, String title, String autor, String serie, String content, String img) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.serie = serie;
        this.content = content;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "{ title=" + title + ", autor=" + autor + ", serie=" + serie + ", content=" + content
                + ", img=" + img + "}";
    }

    

}