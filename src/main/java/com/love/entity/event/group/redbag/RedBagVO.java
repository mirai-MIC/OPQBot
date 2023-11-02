package com.love.entity.event.group.redbag;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @Author: mi
 * @CreateTime: 2023/10/27 16:35
 * @Description:
 */

/**
 * <p>"RedBag": {
 * <p>"Wishing": "大吉大利",
 * <p>"Des": "赶紧点击拆开吧",
 * <p>"RedType": 6,
 * <p>"Listid": "10000320012310273400107531923400",
 * <p>"Authkey": "5f95203898f1a25c8576529186cda248pa",
 * <p>"Channel": 1,
 * <p>"StingIndex": "ZjM1MDBmYjI4NDQxNThiYmRkZjk1N2Q2MDM0ZmI3NjU=",
 * <p>"TransferMsg": "",
 * <p>"Token_17_2": "3DxfLL57/O+tsjxa49ilbEqt5l7tzOY9a45ZJHacuoo=",
 * <p>"Token_17_3": "MDk3ZDhkNmJlOWQ4ODdiYjQ1YmJkZTNjNDZlZjNiMzQ=",
 * <p>"FromUin": 939136273,
 * <p>"FromType": 1
 * <p>}
 */
@Data
public class RedBagVO {
    @SerializedName("Wishing")
    private String Wishing;
    @SerializedName("Des")
    private String Des;
    @SerializedName("RedType")
    private Integer RedType;
    @SerializedName("Listid")
    private String Listid;
    @SerializedName("Authkey")
    private String Authkey;
    @SerializedName("Channel")
    private Integer Channel;
    @SerializedName("StingIndex")
    private String StingIndex;
    @SerializedName("TransferMsg")
    private String TransferMsg;
    @SerializedName("Token_17_2")
    private String Token_17_2;
    @SerializedName("Token_17_3")
    private String Token_17_3;
    @SerializedName("FromUin")
    private Integer FromUin;
    @SerializedName("FromType")
    private Integer FromType;
}
