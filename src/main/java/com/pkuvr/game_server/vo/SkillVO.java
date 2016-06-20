package com.pkuvr.game_server.vo;

import java.io.Serializable;

public class SkillVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int skill;
    private int level;

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
