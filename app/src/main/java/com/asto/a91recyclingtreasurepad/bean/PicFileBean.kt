package com.asto.a91recyclingtreasurepad.bean

import java.io.File

/**
 * 作者 ：nxk
 * 日期 ：2019/12/19
 * 备注 ：图片信息类
 * @param file: 图片文件
 * @param fileName: 图片文件名
 * @param fileNameString: 图片路径 + 文件名
 */
data class PicFileBean(val file: File, val fileName : String, val fileNamePath : String,val collect_id : String)