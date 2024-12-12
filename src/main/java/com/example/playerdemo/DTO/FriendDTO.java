package com.example.playerdemo.DTO;

import lombok.Data;

@Data
public class FriendDTO {
    private Long friendId; // The ID of the friend to be added
    private Long playerId; // The ID of the player to be added
    private String playerName; // The name of the player to be added
    private String friendName; // The name of the friend to be added
}