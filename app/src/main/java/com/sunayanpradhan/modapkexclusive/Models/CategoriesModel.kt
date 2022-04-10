package com.sunayanpradhan.modapkexclusive.Models

data class CategoriesModel(var categoryId: String,var categoryLogo:String,var categoryName:String,var categoryType:String)
{
    constructor():this(
        "",
        "",
        "",
        ""
    )
}
