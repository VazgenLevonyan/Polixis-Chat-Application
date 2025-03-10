package com.polixis.dto.usersgroup;

import java.util.ArrayList;
import java.util.List;

public class DetachUsersFromUsersGroup {

    private Long groupId;
    private List<Long> memberIds = new ArrayList<>();

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<Long> memberIds) {
        this.memberIds = memberIds;
    }
}
