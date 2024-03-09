package com.java.low.level.design.Group;

import com.java.low.level.design.User.User;

import java.util.ArrayList;
import java.util.List;

public class GroupController {
    List<Group> groupList;

    public GroupController() {
        this.groupList = new ArrayList<>();
    }

    public void createNewGroup(String groupId, String groupName, User createdByUser) {
        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.addMember(createdByUser);
        groupList.add(group);
    }

    public Group getGroup(String groupId) throws Exception {
        for(Group group: groupList) {
            if(group.getGroupId().equals(groupId)){
                return group;
            }
        }
        throw new Exception("Group not found");
    }
}
