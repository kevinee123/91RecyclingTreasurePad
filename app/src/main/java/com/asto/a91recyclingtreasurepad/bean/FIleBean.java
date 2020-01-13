package com.asto.a91recyclingtreasurepad.bean;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.Serializable;

/**
 * Created by zj on 2018/10/22.
 * is use for:
 */
public class FIleBean implements Serializable{
    private File file;

    public FIleBean(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
