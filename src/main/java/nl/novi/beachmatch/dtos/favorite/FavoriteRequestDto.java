package nl.novi.beachmatch.dtos.favorite;

import jakarta.validation.constraints.NotNull;

public class FavoriteRequestDto {
    @NotNull(message = "beachId cannot be empty")
    private Long beachId;

    public Long getBeachId() {
        return beachId;
    }

    public void setBeachId(Long beachId) {
        this.beachId = beachId;
    }
}
