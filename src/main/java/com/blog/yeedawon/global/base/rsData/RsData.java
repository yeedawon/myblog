package com.blog.yeedawon.global.base.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {
    private final String resultCode;
    private final String msg;
    private final Object data;

    public static RsData of(String resultCode, String msg) {
        return RsData.of(resultCode, msg, null);
    }

    public static RsData of(String resultCode, String msg, Object data) {
        return new RsData(resultCode, msg, data);
    }

    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }
}
