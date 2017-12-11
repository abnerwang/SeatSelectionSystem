package com.qiyexuxu.web.formbean;

import com.qiyexuxu.common.InfoMessage;

import java.util.HashMap;
import java.util.Map;

public class LoginForm {
    private String studentID;    // 学号
    private String password;    // 密码

    private Map<String, String> errorMap = new HashMap<String, String>();   // 封装表单错误信息

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        // 对学号的校验
        if ("".equals(this.studentID) || "".equals(this.studentID.trim())) {
            isOK = false;
            errorMap.put("studentID", InfoMessage.ERROR_STUDENT_ID_NULL);
        }
        if (this.studentID.length() > 10) {
            errorMap.put("studentID", InfoMessage.ERROR_STUDENT_ID_LENGTH);
        }

        return isOK;
    }
}
