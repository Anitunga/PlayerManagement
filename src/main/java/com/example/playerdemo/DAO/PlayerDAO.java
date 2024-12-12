package com.example.playerdemo.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Repository.PlayerRepository;

@Repository
public class PlayerDAO implements IDAO<Player> {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player save(Player t) {
        return playerRepository.save(t);
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public void delete(Player t) {
        playerRepository.delete(t);
    }

}
