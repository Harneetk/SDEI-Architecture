package android.sdei.com.basicapp.model;

import java.util.List;

/**
 *
 * @Base LoginResponse class :  This class contain the all the fields having the  response of login api..
 *
 *
 */
public class LoginResponse {
    /**
     * status : success
     * messageId : 200
     * message : User logged in successfully
     * data : {"_id":"5a61935bdffbec1b5cffc2c4","last_name":"","email":"vik@yopmail.com","location":"","first_name":"Vik","device_token":"simulator123456statatic12345device123456token123456","gender":"Male","user_type":"application","facebook_id":"","zipCode":"160071","device_type":"ios","latLong":[-74.006,40.7128],"dob":"2000-01-19T00:00:00.000Z","__v":0,"socketid":"","profession":"","education":"","college_name":"","about_me":"","lastMessage":[],"unReadCount":0,"badge_count":0,"created":"2018-01-19T06:02:05.989Z","subScribeNewsLetter":true,"isDeleted":false,"_public":true,"interests":["5b28a35f8e80c15aca7679cb","5b28a2a08e80c15aca7679c5","5b28a2868e80c15aca7679c4","5b28a32f8e80c15aca7679c9","5b28a33c8e80c15aca7679ca","5b28a24b8e80c15aca7679c2"],"userImages":[{"_id":"5a65d68e8ccf3c6746454792","name":"1516623502235.jpg","creationDate":"2018-01-22T11:13:13.472Z"},{"_id":"5a8aa6ca88e86917ac260538","name":"1519036105501.jpg","creationDate":"2018-01-30T13:07:07.730Z"},{"_id":"5a8aa6ca88e86917ac260537","name":"1519036105506.jpg","creationDate":"2018-01-30T13:07:07.730Z"}],"isSeen":true,"is_like":false}
     * access_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzaWQiOnsiX2lkIjoiNWE2MTkzNWJkZmZiZWMxYjVjZmZjMmM0IiwibGFzdF9uYW1lIjoiIiwiZW1haWwiOiJ2aWtAeW9wbWFpbC5jb20iLCJsb2NhdGlvbiI6IiIsImZpcnN0X25hbWUiOiJWaWsiLCJwYXNzd29yZCI6ImY1YmIwYzhkZTE0NmM2N2I0NGJhYmJmNGU2NTg0Y2MwIiwiZGV2aWNlX3Rva2VuIjoic2ltdWxhdG9yMTIzNDU2c3RhdGF0aWMxMjM0NWRldmljZTEyMzQ1NnRva2VuMTIzNDU2IiwiZ2VuZGVyIjoiTWFsZSIsInVzZXJfdHlwZSI6ImFwcGxpY2F0aW9uIiwiZmFjZWJvb2tfaWQiOiIiLCJ6aXBDb2RlIjoiMTYwMDcxIiwiZGV2aWNlX3R5cGUiOiJpb3MiLCJsYXRMb25nIjpbLTc0LjAwNiw0MC43MTI4XSwiZG9iIjoiMjAwMC0wMS0xOVQwMDowMDowMC4wMDBaIiwiX192IjowLCJzb2NrZXRpZCI6IiIsInByb2Zlc3Npb24iOiIiLCJlZHVjYXRpb24iOiIiLCJjb2xsZWdlX25hbWUiOiIiLCJhYm91dF9tZSI6IiIsImxhc3RNZXNzYWdlIjpbXSwidW5SZWFkQ291bnQiOjAsImJhZGdlX2NvdW50IjowLCJjcmVhdGVkIjoiMjAxOC0wMS0xOVQwNjowMjowNS45ODlaIiwic3ViU2NyaWJlTmV3c0xldHRlciI6dHJ1ZSwiaXNEZWxldGVkIjpmYWxzZSwiX3B1YmxpYyI6dHJ1ZSwiaW50ZXJlc3RzIjpbIjViMjhhMzVmOGU4MGMxNWFjYTc2NzljYiIsIjViMjhhMmEwOGU4MGMxNWFjYTc2NzljNSIsIjViMjhhMjg2OGU4MGMxNWFjYTc2NzljNCIsIjViMjhhMzJmOGU4MGMxNWFjYTc2NzljOSIsIjViMjhhMzNjOGU4MGMxNWFjYTc2NzljYSIsIjViMjhhMjRiOGU4MGMxNWFjYTc2NzljMiJdLCJ1c2VySW1hZ2VzIjpbeyJfaWQiOiI1YTY1ZDY4ZThjY2YzYzY3NDY0NTQ3OTIiLCJuYW1lIjoiMTUxNjYyMzUwMjIzNS5qcGciLCJjcmVhdGlvbkRhdGUiOiIyMDE4LTAxLTIyVDExOjEzOjEzLjQ3MloifSx7Il9pZCI6IjVhOGFhNmNhODhlODY5MTdhYzI2MDUzOCIsIm5hbWUiOiIxNTE5MDM2MTA1NTAxLmpwZyIsImNyZWF0aW9uRGF0ZSI6IjIwMTgtMDEtMzBUMTM6MDc6MDcuNzMwWiJ9LHsiX2lkIjoiNWE4YWE2Y2E4OGU4NjkxN2FjMjYwNTM3IiwibmFtZSI6IjE1MTkwMzYxMDU1MDYuanBnIiwiY3JlYXRpb25EYXRlIjoiMjAxOC0wMS0zMFQxMzowNzowNy43MzBaIn1dLCJpc1NlZW4iOnRydWUsImlzX2xpa2UiOmZhbHNlfSwiaWF0IjoxNTI5NTY1ODc1fQ.DcIsmAHNPNhRXrd9IYvxXx4fwgoeSVGKL_m1lz_ceS4
     */

    private String status;
    private int messageId;
    private String message;
    private DataBean data;
    private String access_token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public static class DataBean {
        /**
         * _id : 5a61935bdffbec1b5cffc2c4
         * last_name :
         * email : vik@yopmail.com
         * location :
         * first_name : Vik
         * device_token : simulator123456statatic12345device123456token123456
         * gender : Male
         * user_type : application
         * facebook_id :
         * zipCode : 160071
         * device_type : ios
         * latLong : [-74.006,40.7128]
         * dob : 2000-01-19T00:00:00.000Z
         * __v : 0
         * socketid :
         * profession :
         * education :
         * college_name :
         * about_me :
         * lastMessage : []
         * unReadCount : 0
         * badge_count : 0
         * created : 2018-01-19T06:02:05.989Z
         * subScribeNewsLetter : true
         * isDeleted : false
         * _public : true
         * interests : ["5b28a35f8e80c15aca7679cb","5b28a2a08e80c15aca7679c5","5b28a2868e80c15aca7679c4","5b28a32f8e80c15aca7679c9","5b28a33c8e80c15aca7679ca","5b28a24b8e80c15aca7679c2"]
         * userImages : [{"_id":"5a65d68e8ccf3c6746454792","name":"1516623502235.jpg","creationDate":"2018-01-22T11:13:13.472Z"},{"_id":"5a8aa6ca88e86917ac260538","name":"1519036105501.jpg","creationDate":"2018-01-30T13:07:07.730Z"},{"_id":"5a8aa6ca88e86917ac260537","name":"1519036105506.jpg","creationDate":"2018-01-30T13:07:07.730Z"}]
         * isSeen : true
         * is_like : false
         */

        private String _id;
        private String last_name;
        private String email;
        private String location;
        private String first_name;
        private String device_token;
        private String gender;
        private String user_type;
        private String facebook_id;
        private String zipCode;
        private String device_type;
        private String dob;
        private int __v;
        private String socketid;
        private String profession;
        private String education;
        private String college_name;
        private String about_me;
        private int unReadCount;
        private int badge_count;
        private String created;
        private boolean subScribeNewsLetter;
        private boolean isDeleted;
        private boolean _public;
        private boolean isSeen;
        private boolean is_like;
        private List<Double> latLong;
        private List<?> lastMessage;
        private List<String> interests;
        private List<UserImagesBean> userImages;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(String facebook_id) {
            this.facebook_id = facebook_id;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getSocketid() {
            return socketid;
        }

        public void setSocketid(String socketid) {
            this.socketid = socketid;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getCollege_name() {
            return college_name;
        }

        public void setCollege_name(String college_name) {
            this.college_name = college_name;
        }

        public String getAbout_me() {
            return about_me;
        }

        public void setAbout_me(String about_me) {
            this.about_me = about_me;
        }

        public int getUnReadCount() {
            return unReadCount;
        }

        public void setUnReadCount(int unReadCount) {
            this.unReadCount = unReadCount;
        }

        public int getBadge_count() {
            return badge_count;
        }

        public void setBadge_count(int badge_count) {
            this.badge_count = badge_count;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public boolean isSubScribeNewsLetter() {
            return subScribeNewsLetter;
        }

        public void setSubScribeNewsLetter(boolean subScribeNewsLetter) {
            this.subScribeNewsLetter = subScribeNewsLetter;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }

        public boolean is_public() {
            return _public;
        }

        public void set_public(boolean _public) {
            this._public = _public;
        }

        public boolean isIsSeen() {
            return isSeen;
        }

        public void setIsSeen(boolean isSeen) {
            this.isSeen = isSeen;
        }

        public boolean isIs_like() {
            return is_like;
        }

        public void setIs_like(boolean is_like) {
            this.is_like = is_like;
        }

        public List<Double> getLatLong() {
            return latLong;
        }

        public void setLatLong(List<Double> latLong) {
            this.latLong = latLong;
        }

        public List<?> getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(List<?> lastMessage) {
            this.lastMessage = lastMessage;
        }

        public List<String> getInterests() {
            return interests;
        }

        public void setInterests(List<String> interests) {
            this.interests = interests;
        }

        public List<UserImagesBean> getUserImages() {
            return userImages;
        }

        public void setUserImages(List<UserImagesBean> userImages) {
            this.userImages = userImages;
        }

        public static class UserImagesBean {
            /**
             * _id : 5a65d68e8ccf3c6746454792
             * name : 1516623502235.jpg
             * creationDate : 2018-01-22T11:13:13.472Z
             */

            private String _id;
            private String name;
            private String creationDate;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCreationDate() {
                return creationDate;
            }

            public void setCreationDate(String creationDate) {
                this.creationDate = creationDate;
            }
        }
    }
}
