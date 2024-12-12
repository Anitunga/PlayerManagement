package com.example.playerdemo.Service;

import com.example.playerdemo.DTO.PlayerDTO;
import com.example.playerdemo.DTO.PlayerStatsDTO;
import com.example.playerdemo.Entity.LevelType;
import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.DAO.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    // Create a new player
    @Override
    public Player createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        return playerDAO.save(player);
    }

    // Get a player by ID
    @Override
    public Player getPlayerById(Long id) {
        return playerDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    // Get all players
    @Override
    public List<Player> getAllPlayers() {
        return playerDAO.findAll();
    }

    // Update player stats
    public void updatePlayerStats(Long playerId, PlayerStatsDTO statsDTO) {
        Player player = playerDAO.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // Update total points
        player.setTotalPoints(player.getTotalPoints() + statsDTO.getScore());

        // Update wins if victory
        if (statsDTO.isVictory()) {
            player.setTotalWins(player.getTotalWins() + 1);
        }

        // Update level based on total points
        player.setLevel(LevelType.getLevelByPoints(player.getTotalPoints()));

        playerDAO.save(player);
    }
}
