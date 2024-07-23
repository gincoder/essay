package com.panther.cry;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Getter
public class CryptConfig implements Serializable {

    private Mode mode = Mode.CTS;
    private Padding padding = Padding.PKCS5Padding;
    private String key = "12345689";
    private String iv = "123456789";

}