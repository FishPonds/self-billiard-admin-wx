package com.lingdu.common.utils.uuid;

import java.util.Random;
import java.security.SecureRandom;
/**
 * ID生成器工具类
 * 
 * @author 猛男波波
 */
public class IdUtils
{
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 包含36个字符（26个字母和10个数字）
     */
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /**
     * 随机码长度
     */
    private static final int CODE_LENGTH = 9;
    /**
     * 随机数生成器
     */
    private static final Random RANDOM = new SecureRandom();

    /**
     * 生成桌台随机码
     *
     * @return 随机码
     */
    public static String generateRandomCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}