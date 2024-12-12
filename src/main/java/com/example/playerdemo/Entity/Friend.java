package com.example.playerdemo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "friend_player_id", nullable = false)
    private Player friendPlayer;

    // Constructor
    public Friend() {
    }

    public Friend(Player player, Player friendPlayer) {
        this.player = player;
        this.friendPlayer = friendPlayer;
    }
}
