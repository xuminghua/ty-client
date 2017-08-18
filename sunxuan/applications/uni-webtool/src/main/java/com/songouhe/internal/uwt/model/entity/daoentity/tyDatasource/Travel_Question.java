package com.songouhe.internal.uwt.model.entity.daoentity.tyDatasource;

import java.util.Date;

public class Travel_Question {
    private Long id;

    private Long travelId;

    private Long askUserId;

    private String question;

    private String answer;

    private Long answerUserId;

    private Date anserTime;

    private Date createTime;

    private Short status;

    private Integer tmpIntvalue;

    private Integer tmp1Charvalue;

    private Integer tmp2Charvalue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    public Long getAskUserId() {
        return askUserId;
    }

    public void setAskUserId(Long askUserId) {
        this.askUserId = askUserId;
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

    public Long getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(Long answerUserId) {
        this.answerUserId = answerUserId;
    }

    public Date getAnserTime() {
        return anserTime;
    }

    public void setAnserTime(Date anserTime) {
        this.anserTime = anserTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getTmpIntvalue() {
        return tmpIntvalue;
    }

    public void setTmpIntvalue(Integer tmpIntvalue) {
        this.tmpIntvalue = tmpIntvalue;
    }

    public Integer getTmp1Charvalue() {
        return tmp1Charvalue;
    }

    public void setTmp1Charvalue(Integer tmp1Charvalue) {
        this.tmp1Charvalue = tmp1Charvalue;
    }

    public Integer getTmp2Charvalue() {
        return tmp2Charvalue;
    }

    public void setTmp2Charvalue(Integer tmp2Charvalue) {
        this.tmp2Charvalue = tmp2Charvalue;
    }
}