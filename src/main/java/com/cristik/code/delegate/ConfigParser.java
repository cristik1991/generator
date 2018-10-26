package com.cristik.code.delegate;

import com.cristik.code.exception.ParseException;
import com.cristik.code.model.Config;

/**
 * @author cristik
 */
public interface ConfigParser {

    /**
     * 解析生成代码配置
     * @return
     * @throws ParseException
     */
    Config parse() throws ParseException;
}
