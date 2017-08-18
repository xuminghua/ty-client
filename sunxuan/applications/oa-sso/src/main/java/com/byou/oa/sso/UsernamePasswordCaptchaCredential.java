package com.byou.oa.sso;

import org.jasig.cas.authentication.UsernamePasswordCredential;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by sunxuan on 16-7-15.
 */
public class UsernamePasswordCaptchaCredential extends UsernamePasswordCredential {
    /**
     *
     */
    private static final long serialVersionUID = -864735145551932618L;
    public UsernamePasswordCaptchaCredential() {
        System.out.print("1captcha:" + captcha);
    }

    public UsernamePasswordCaptchaCredential(String userName, String password, String captcha) {
        super(userName, password);
        System.out.print("2captcha:" + captcha);
        this.captcha = captcha;
    }
    @NotNull
    @Size(min=1,message = "required.captcha")
    private String captcha;

    public String getCaptcha() {
        System.out.print("captcha:" + captcha);
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
