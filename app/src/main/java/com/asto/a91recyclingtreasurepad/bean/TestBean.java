package com.asto.a91recyclingtreasurepad.bean;

import java.util.List;

/**
 * Created by zj on 2018/10/18.
 * is use for:
 */
public class TestBean {
        private int id;
        private Object user_id;
        private int company_id;
        private String name;
        private String code;
        private int socket;
        private String equipment_ip;
        private int status;
        private String created_at;
        private String updated_at;
        private String company_name;
        private String address;
        private int group_id;
        private List<DisplayBean> display;
        private List<DataCameraBean> data_camera;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getSocket() {
            return socket;
        }

        public void setSocket(int socket) {
            this.socket = socket;
        }

        public String getEquipment_ip() {
            return equipment_ip;
        }

        public void setEquipment_ip(String equipment_ip) {
            this.equipment_ip = equipment_ip;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public List<DisplayBean> getDisplay() {
            return display;
        }

        public void setDisplay(List<DisplayBean> display) {
            this.display = display;
        }

        public List<DataCameraBean> getData_camera() {
            return data_camera;
        }

        public void setData_camera(List<DataCameraBean> data_camera) {
            this.data_camera = data_camera;
        }

        public static class DisplayBean {
            /**
             * id : 5
             * equipment_id : 3
             * name : 地磅1号
             * eq_com :
             * baud_rate :
             * data_bits :
             * check_digit :
             * start_char : =
             * start_type : String
             * length : 7
             * coordinates : 0
             * rank : 1
             * decimal_point_type : 0
             * created_at : 2018-10-18 15:04:12
             * updated_at : 2018-10-18 15:04:12
             */

            private int id;
            private int equipment_id;
            private String name;
            private String eq_com;
            private String baud_rate;
            private String data_bits;
            private String check_digit;
            private String start_char;
            private String start_type;
            private String length;
            private int coordinates;
            private int rank;
            private int decimal_point_type;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getEquipment_id() {
                return equipment_id;
            }

            public void setEquipment_id(int equipment_id) {
                this.equipment_id = equipment_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEq_com() {
                return eq_com;
            }

            public void setEq_com(String eq_com) {
                this.eq_com = eq_com;
            }

            public String getBaud_rate() {
                return baud_rate;
            }

            public void setBaud_rate(String baud_rate) {
                this.baud_rate = baud_rate;
            }

            public String getData_bits() {
                return data_bits;
            }

            public void setData_bits(String data_bits) {
                this.data_bits = data_bits;
            }

            public String getCheck_digit() {
                return check_digit;
            }

            public void setCheck_digit(String check_digit) {
                this.check_digit = check_digit;
            }

            public String getStart_char() {
                return start_char;
            }

            public void setStart_char(String start_char) {
                this.start_char = start_char;
            }

            public String getStart_type() {
                return start_type;
            }

            public void setStart_type(String start_type) {
                this.start_type = start_type;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public int getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(int coordinates) {
                this.coordinates = coordinates;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public int getDecimal_point_type() {
                return decimal_point_type;
            }

            public void setDecimal_point_type(int decimal_point_type) {
                this.decimal_point_type = decimal_point_type;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }

        public static class DataCameraBean {
            /**
             * id : 6
             * equipment_id : 3
             * vendor : 海康
             * port : 8000
             * IP : 192.168.10.11
             * user : admin
             * password : zj88friend#
             * created_at : 2018-10-18 15:05:21
             * updated_at : 2018-10-18 15:05:21
             */

            private int id;
            private int equipment_id;
            private String vendor;
            private int port;
            private String IP;
            private String user;
            private String password;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getEquipment_id() {
                return equipment_id;
            }

            public void setEquipment_id(int equipment_id) {
                this.equipment_id = equipment_id;
            }

            public String getVendor() {
                return vendor;
            }

            public void setVendor(String vendor) {
                this.vendor = vendor;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public String getIP() {
                return IP;
            }

            public void setIP(String IP) {
                this.IP = IP;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }
}
