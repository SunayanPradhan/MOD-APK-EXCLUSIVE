package com.sunayanpradhan.modapkexclusive.Models

data class ScreenshotModel(var appId:String,var screenshotId:String , var screenshot:String)
{
    constructor():this(
        "",
        "",
        ""
    )
}