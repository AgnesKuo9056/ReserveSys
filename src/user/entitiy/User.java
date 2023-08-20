package user.entitiy;

import java.time.LocalDate;
import java.util.Random;

public class User {
    private static final Random random = new Random(System.currentTimeMillis());

    private int user_id;// ID
    private String user_name;// 用户名
    private  String anony_name;//用户昵称
    private  String password;//密码
    private String mail;// 邮箱
    private String phone_number;// 联系方式
    private String avatar;//头像
    private Integer user_role;// 用户角色(权限)
    private String role_name;// 角色对应描述(权限)
    private String regist_time;

    public User(){

    }

    public User(int user_id, String user_name, String anony_name, String password, String mail, String phone_number, String avatar, Integer user_role, String role_name, String regist_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.anony_name = anony_name;
        this.password = password;
        this.mail = mail;
        this.phone_number = phone_number;
        this.avatar = avatar;
        this.user_role = user_role;
        this.role_name = role_name;
        this.regist_time = regist_time;
    }

    public User(String user_name, String anony_name, String password , String phone_number){
        this.user_id = generate();
        this.user_name = user_name;
        this.anony_name = anony_name;
        this.password = password;
        this.phone_number = phone_number;
        this.user_role = 2;
        this.role_name = "user";

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAnony_name() { return anony_name; }

    public void setAnony_name(String anony_name) { this.anony_name = anony_name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAvatar() { return avatar; }

    public void setAvatar(String avatar) { this.avatar = avatar; }

    public Integer getUser_role() {
        return user_role;
    }

    public void setUser_role(Integer user_role) {
        this.user_role = user_role;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRegist_time() {
        return regist_time;
    }

    public void setRegist_time(String regist_time) {
        this.regist_time = regist_time;
    }

    public static Random getRandom() {
        return random;
    }

    public   int generate() {

        int randomNumber = random.nextInt(100000000);
        long timestamp = System.currentTimeMillis();
        String orderedIdStr = String.format("%d%08d", timestamp, randomNumber);
        int orderedId = Integer.parseInt(orderedIdStr.substring(0, 8));
        return orderedId;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", anony_name='" + anony_name + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", avatar='" + avatar + '\'' +
                ", user_role=" + user_role +
                ", role_name='" + role_name + '\'' +
                ", regist_time='" + regist_time + '\'' +
                '}';
    }
}
