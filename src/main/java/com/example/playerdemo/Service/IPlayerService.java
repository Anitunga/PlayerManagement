package com.example.playerdemo.Service;

import com.example.playerdemo.DTO.PlayerDTO;
import com.example.playerdemo.Entity.Player;
import java.util.List;

public interface IPlayerService {
    Player createPlayer(PlayerDTO playerDTO);
    Player getPlayerById(Long id);
    List<Player> getAllPlayers();
}
