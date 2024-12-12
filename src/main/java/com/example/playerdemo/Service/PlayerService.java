package com.example.playerdemo.Service;

import com.example.playerdemo.DTO.PlayerDTO;
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
}
