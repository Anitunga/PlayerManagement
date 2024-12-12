package com.example.playerdemo.Service;

import com.example.playerdemo.Entity.Player;
import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.DAO.PlayerDAO;
import com.example.playerdemo.DAO.FriendDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService implements IFriendService {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private FriendDAO friendDAO;

    // Add a friend to a player
    @Override
    public Friend addFriend(Long playerId, Long friendId) {
        Player player = playerDAO.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Player friendPlayer = playerDAO.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Friend player not found"));

        // Check if friendship already exists
        if (friendDAO.findByPlayerAndFriendPlayer(player, friendPlayer).isPresent()) {
            throw new RuntimeException("Friendship already exists");
        }

        Friend friend = new Friend(player, friendPlayer);
        return friendDAO.save(friend);
    }

    // Get all friends of a player
    @Override
    public List<Friend> getPlayerFriends(Long playerId) {
        Player player = playerDAO.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        return friendDAO.findByPlayer(player);
    }
}