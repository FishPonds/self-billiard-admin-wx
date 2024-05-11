package com.lingdu.common.exception.file;

import com.lingdu.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author 猛男波波
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}