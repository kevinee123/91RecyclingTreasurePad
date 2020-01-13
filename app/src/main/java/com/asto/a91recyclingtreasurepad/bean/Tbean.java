package com.asto.a91recyclingtreasurepad.bean;

public class Tbean {

    /**
     * data : {"enter":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * enter : 1
         */

        private int enter;

        public int getEnter() {
            return enter;
        }

        public void setEnter(int enter) {
            this.enter = enter;
        }
    }
}
