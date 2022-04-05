package com.sunayanpradhan.modapkexclusive.Models

data class AppModel(var appId:String,var logo:String,var title:String,var applink:String,var obblink:String,var description:String,var version:String,var category:String,var publisher:String,var appsize:String)
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
        ""
    )
}
