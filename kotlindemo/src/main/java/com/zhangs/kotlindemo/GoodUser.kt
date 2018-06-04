package com.zhangs.kotlindemo



data class GoodUser(
    var username: String,
    var headurl: String,
    var url_token: String,
    var is_org: Boolean,
    var profile: String,
    var badge: List<Any>
)