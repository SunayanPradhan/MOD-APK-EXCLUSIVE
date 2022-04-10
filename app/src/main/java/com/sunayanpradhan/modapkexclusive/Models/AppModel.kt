package com.sunayanpradhan.modapkexclusive.Models

data class AppModel(var appId:String,var logo:String,var title:String,var applink:String,var obblink:String,var description:String,var version:String,var category:String,var publisher:String,var appsize:String,var appType:String,var editorsChoice:Boolean,var premium:Boolean,var trending:Boolean,var release:Boolean,var recommended:Boolean)
{
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        false,
        false,
        false,
        false,
        false
    )
}
