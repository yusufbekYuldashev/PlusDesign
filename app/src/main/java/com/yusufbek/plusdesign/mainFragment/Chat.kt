package com.yusufbek.plusdesign.mainFragment

data class Chat(
    var imgSrc: Int,
    var name: String,
    var lastMsg: String,
    var date: String,
    var isPin: Boolean = false,
    var lastMsgStatus: LastMessageStatus = LastMessageStatus.NOT_SENT
)
