package com.asto.a91recyclingtreasurepad.bean;

import java.util.List;

public class PrintContentBean {

    private List<PrintListBean> print_list;

    public List<PrintListBean> getPrint_list() {
        return print_list;
    }

    public void setPrint_list(List<PrintListBean> print_list) {
        this.print_list = print_list;
    }

    public static class PrintListBean {
        /**
         * content :
         * fontSize : 10
         * alignment : 1
         * addNewLine : true
         */

        private String content;
        private int fontSize;
        private int alignment;
        private boolean addNewLine;
        private boolean isBold;

        public boolean isBold() {
            return isBold;
        }

        public void setBold(boolean bold) {
            isBold = bold;
        }


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public int getAlignment() {
            return alignment;
        }

        public void setAlignment(int alignment) {
            this.alignment = alignment;
        }

        public boolean isAddNewLine() {
            return addNewLine;
        }

        public void setAddNewLine(boolean addNewLine) {
            this.addNewLine = addNewLine;
        }
    }
}
