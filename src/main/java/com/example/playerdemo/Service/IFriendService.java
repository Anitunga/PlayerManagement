package com.example.playerdemo.Service;

import com.example.playerdemo.Entity.Friend;
import java.util.List;

public interface IFriendService {
    Friend addFriend(Long playerId, Long friendId);
    List<Friend> getPlayerFriends(Long playerId);
}
