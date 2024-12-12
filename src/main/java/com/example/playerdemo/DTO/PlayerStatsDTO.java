package com.example.playerdemo.DTO;

import lombok.Data;

@Data
public class PlayerStatsDTO {
    private int score;
    private boolean victory;

    // Constructors
    public PlayerStatsDTO() {
    }

    public PlayerStatsDTO(int score, boolean victory) {
        this.score = score;
        this.victory = victory;
    }
}