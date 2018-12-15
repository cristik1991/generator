package com.cristik.code.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cristik
 */
@Data
@Accessors(chain = true)
public class JdbcConnection {

    private String userName;

    private String driverName;

    private String password;

    private String schema;

    private String url;
}
