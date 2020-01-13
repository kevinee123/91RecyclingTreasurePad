package com.asto.a91recyclingtreasurepad.bean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/3
 * 备注 ：设备登录返回数据
 */
data class LoginBean(
    val camera: List<Any>,
    val company: Company,
    val displays: List<Any>,
    val equipment: Equipment,
    val group: Group
)
//公司
data class Company(
    val address: Any,
    val created_at: String,
    val group_id: Int,
    val id: Int,
    val name: String,
    val status: Int,
    val types: Any,
    val updated_at: String
)
//设备
data class Equipment(
    val code: String,
    val company_id: Int,//分站id
    val created_at: String,
    val equipment_ip: Any,
    val id: Int,
    val name: String,
    val second: Int,
    val socket: Any,
    val status: Int,
    val updated_at: String,
    val user_id: Any
)
//集团
data class Group(
    val address: String,
    val code: String,
    val created_at: String,
    val id: Int,//集团id
    val name: String,
    val phone: String,
    val status: Int,
    val updated_at: String
)