package com.yza.xzs.domain.enums;

import java.util.*;

public enum RoleEnum {
    STUDENT(1,"STUDENT"),
    ADMIN(3,"ADMIN");

    int code;
    String name;

    RoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer,RoleEnum> keyMap=new HashMap<>();
    static {
        for(RoleEnum item:RoleEnum.values()){
            keyMap.put(item.getCode(), item);
        }
    }

    public RoleEnum fromCode(int code){
        return keyMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return "ROLE_" + name;
    }
}
