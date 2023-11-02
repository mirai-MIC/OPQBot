package com.love.entity.event.eventmessage;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: NTQQ
 * @BelongsPackage: com.ntqq.message.data
 * @Author: mi
 * @CreateTime: 2023/10/23 11:20
 * @Description:
 * @Version: 1.0
 */


@NoArgsConstructor
@Data
public class GroupInfoVO {
    @SerializedName("GroupCard")
    private String groupCard;
    @SerializedName("GroupCode")
    private Integer groupCode;
    @SerializedName("GroupInfoSeq")
    private Integer groupInfoSeq;
    @SerializedName("GroupLevel")
    private Integer groupLevel;
    @SerializedName("GroupRank")
    private Integer groupRank;
    @SerializedName("GroupType")
    private Integer groupType;
    @SerializedName("GroupName")
    private String groupName;
}
