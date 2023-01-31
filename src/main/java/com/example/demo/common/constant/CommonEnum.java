package com.example.demo.common.constant;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author tanyue
 *
 * @Date 2022/10/28
 **/
public interface CommonEnum
{

    /**
     * @return 枚举code
     */
    int getCode();
    /**
     * @return 枚举name
     */
    String getName();
    /**
     *
     * @return 枚举英文名(enum.name)
     */
    String name();

    static <T extends CommonEnum> T getByCode(Class<T> enumClazz, Integer code){
        return Arrays.stream(enumClazz.getEnumConstants())
                    .filter(item -> item.getCode() == code)
                    .findAny()
                    .orElse(null);
    }

    static <T extends CommonEnum, R> T getByProperty(Class<T> enumClazz, Function<T, R> property, R value) {
        return Arrays.stream(enumClazz.getEnumConstants())
                .filter(item -> property.apply(item).equals(value)).findAny().orElse(null);
    }

}
