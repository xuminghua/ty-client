package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource.travel;

import java.sql.Timestamp;

public class Travel_Question {
    private Long id;

    private Long travel_id;

    private Long ask_user_id;

    private String question;

    private String answer;

    private Long answer_user_id;

    private Timestamp anser_time;

    private Timestamp create_time;

    private Short status;

    private Integer tmp_intvalue;

    private Integer tmp1_charvalue;

    private Integer tmp2_charvalue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(Long travel_id) {
        this.travel_id = travel_id;
    }

    public Long getAsk_user_id() {
        return ask_user_id;
    }

    public void setAsk_user_id(Long ask_user_id) {
        this.ask_user_id = ask_user_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getAnswer_user_id() {
        return answer_user_id;
    }

    public void setAnswer_user_id(Long answer_user_id) {
        this.answer_user_id = answer_user_id;
    }

    public Timestamp getAnser_time() {
        return anser_time;
    }

    public void setAnser_time(Timestamp anser_time) {
        this.anser_time = anser_time;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getTmp_intvalue() {
        return tmp_intvalue;
    }

    public void setTmp_intvalue(Integer tmp_intvalue) {
        this.tmp_intvalue = tmp_intvalue;
    }

    public Integer getTmp1_charvalue() {
        return tmp1_charvalue;
    }

    public void setTmp1_charvalue(Integer tmp1_charvalue) {
        this.tmp1_charvalue = tmp1_charvalue;
    }

    public Integer getTmp2_charvalue() {
        return tmp2_charvalue;
    }

    public void setTmp2_charvalue(Integer tmp2_charvalue) {
        this.tmp2_charvalue = tmp2_charvalue;
    }
}