package com.example.playerdemo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.playerdemo.Entity.Friend;
import com.example.playerdemo.Service.FriendService;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/{playerId}/{friendId}")
    public ResponseEntity<Friend> addFriend(@PathVariable Long playerId, @PathVariable Long friendId) {
        return ResponseEntity.ok(friendService.addFriend(playerId, friendId));
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<List<Friend>> getPlayerFriends(@PathVariable Long playerId) {
        return ResponseEntity.ok(friendService.getPlayerFriends(playerId));
    }
}