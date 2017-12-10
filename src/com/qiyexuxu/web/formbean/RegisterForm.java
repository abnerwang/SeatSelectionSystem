package com.qiyexuxu.web.formbean;

import com.qiyexuxu.common.InfoMessage;

import java.util.HashMap;
import java.util.Map;

public class RegisterForm {
    private String studentID;    // 学号
    private String username;   // 姓名
    private String password;    // 密码
    private String confirmedPassword;   // 确认密码

    private Map<String, String> errorMap = new HashMap<String, String>();      // 用来封装表单错误信息

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }


    /**
     * 判断用户提交的表单数据是否有效
     *
     * @return true: 用户表单有效; false: 用户表单无效
     */
    public boolean isValid() {
        boolean isOK = true;

        // 密码必须包含数字、字母、特殊字符 三种
        // 支持的特殊字符范围：^$./,;:’!@#%&*|?+()[]{}
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        // 验证学号
        if ("".equals(this.studentID) || "".equals(this.studentID.trim())) {
            isOK = false;
            errorMap.put("studentID", InfoMessage.ERROR_STUDENT_ID_NULL);
        }
        if (this.studentID.length() > 10) {
            isOK = false;
            errorMap.put("studentID", InfoMessage.ERROR_STUDENT_ID_LENGTH);
        }

        // 验证密码
        if ("".equals(this.password) || "".equals(this.password.trim())) {
            isOK = false;
            errorMap.put("password", InfoMessage.ERROR_PASSWORD_NULL);
        }
        if (this.password.length() < 6 || this.password.length() > 18) {
            isOK = false;
            errorMap.put("password", InfoMessage.ERROR_PASSWORD_LENGTH);
        }
        if (!this.password.matches(regex)) {
            isOK = false;
            errorMap.put("password", InfoMessage.ERROR_PASSWORD_FORMAT);
        }
        if (!this.password.equals(this.confirmedPassword)) {
            isOK = false;
            errorMap.put("confirmedPassword", InfoMessage.ERROR_PASSWORD_MATCH);
        }

        return isOK;
    }
}
