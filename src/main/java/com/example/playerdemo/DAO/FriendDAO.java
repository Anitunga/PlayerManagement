package com.example.playerdemo.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Repository.FriendRepository;

@Repository
public class FriendDAO implements IDAO<Friend> {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public Friend save(Friend t) {
        return friendRepository.save(t);
    }

    @Override
    public Optional<Friend> findById(Long id) {
        return friendRepository.findById(id);
    }

    @Override
    public List<Friend> findAll() {
        return friendRepository.findAll();
    }

    @Override
    public void delete(Friend friend) {
        friendRepository.delete(friend);
    }

    public List<Friend> findByPlayer(Player player) {
        return friendRepository.findByPlayer(player);
    }

    public Optional<Friend> findByPlayerAndFriendPlayer(Player player, Player friendPlayer) {
        return friendRepository.findByPlayerAndFriendPlayer(player, friendPlayer);
    }

}
