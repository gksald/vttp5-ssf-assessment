package vttp.batch5.ssf.noticeboard.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Notice {
    
    @NotEmpty(message= "Notice Title is mandatory")
    @Size(min = 3, max = 128, message = "Notice Title must be between 3 to 128 characters")
    private String title;

    @Email(message="Invalid email format")
    @NotBlank(message="Email is required")
    private String poster;

    @NotEmpty(message= "Publication Date is required")
    @Future(message= "Invalid Publication Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postDate;

    @Size(min = 1, max = 5, message = "At least 1 category is required")
    private List<CategoriesList> categories;
    
    @NotEmpty(message= "Notice Content is required")
    private String text;


    public Notice() {
    }


    public Notice(String title, String poster, Date postDate, List<CategoriesList> categories, String text) {
        this.title = title;
        this.poster = poster;
        this.postDate = postDate;
        this.categories = categories;
        this.text = text;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public List<CategoriesList> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesList> categories) {
        this.categories = categories;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "Notice [title=" + title + ", poster=" + poster + ", postDate=" + postDate + ", categories=" + categories
                + ", text=" + text + "]";
    }
    
}
