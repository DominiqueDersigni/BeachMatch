package nl.novi.beachmatch.dtos.review;

import jakarta.validation.constraints.*;

public class ReviewRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 100, message = "Title needs to be between 3 and 100 characters")
    private String title;
    @NotBlank(message = "Text cannot be blank")
    @Size(max = 255, message = "Text cannot be longer than 255 characters")
    private String text;
    @NotNull(message = "Score cannot be empty")
    @Min(value = 1, message = "Score must be at least one")
    @Max(value = 5, message = "Score cannot be higher than 5")
    private Integer score;
    @NotNull (message = "beachId cannot be empty")
    private Long beachId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getBeachId() {
        return beachId;
    }

    public void setBeachId(Long beachId) {
        this.beachId = beachId;
    }
}
