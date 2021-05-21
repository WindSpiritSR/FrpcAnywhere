package cn.leviatan.frpcanywhere.model

class Value {
    enum class Section(val sectionName: String) {
        COMMON("common")
    }

    enum class ConfigCommon(val key: String, val defVal: String) {
        /**
         * Frp server address.
         *
         * Default is "0.0.0.0"
         */
        SERVER_ADDR("server_addr", "0.0.0.0"),

        /**
         * Frp server port.
         *
         * Default is "7000"
         */
        SERVER_PORT("server_port", "7000"),

        /**
         * If you want to connect frps by http proxy or socks5 proxy or ntlm proxy, you can set http_proxy here or in global environment variables.
         *
         * It only works when protocol is tcp.
         *
         * Default is ""
         */
        HTTP_PROXY("http_proxy", ""),

        /**
         * Console or real logFile path like "/path/to/frpc.log".
         *
         * Default is ""
         */
        LOG_FILE("log_file", ""),

        /**
         * Log level.
         *
         * {trace, debug, info, warn, error}
         *
         * Default is "info"
         */
        LOG_LEVEL("log_level", LogLevel.INFO.str),

        /**
         * Number of days to keep log files.
         *
         * Default is "3"
         */
        LOG_MAX_DAYS("log_max_days", "3"),

        /**
         * Disable log colors when log_file is console.
         *
         * Default is "false"
         */
        DISABLE_LOG_COLOR("disable_log_color", Enable.FALSE.str),

        /**
         * Connections will be established in advance.
         *
         * Default is "0"
         */
        POOL_COUNT("pool_count", "0"),

        /**
         * Your proxy name will be changed to {user}.{proxy}
         *
         * Default is ""
         */
        USER("user", ""),

        /**
         * Specify a dns server, so frpc will use this instead of default one.
         *
         * Default is ""
         */
        DNS_SERVER("dns_server", ""),

        /**
         * Decide if exit program when first login failed, otherwise continuous relogin to frps.
         *
         * Default is "true"
         */
        LOGIN_FAIL_EXIT("login_fail_exit", Enable.TRUE.str),

        /**
         * Communication protocol used to connect to server.
         *
         * {tcp, kcp, websocket}
         *
         * Default is "tcp"
         */
        PROTOCOL("protocol", Protocol.TCP.str),

        /**
         * If tls_enable is true, frpc will connect frps by tls.
         *
         * Default is "false"
         */
        TLS_ENABLE("tls_enable", Enable.FALSE.str),

        /**
         * Tls cert file path like /path/to/client.crt
         *
         * Default is ""
         */
        TLS_CERT_FILE("tls_cert_file", ""),

        /**
         * Tls cert key file path like /path/to/client.key
         *
         * Default is ""
         */
        TLS_KEY_FILE("tls_key_file", ""),

        /**
         * Tls ca cert file path like /path/to/ca.crt
         *
         * Default is ""
         */
        TLS_TRUSTED_CA_FILE("tls_trusted_ca_file", ""),

        /**
         * Tls server name.
         *
         * Default is ""
         */
        TLS_SERVER_NAME("tls_server_name", ""),

        /**
         * Heartbeat configure, it's not recommended to modify the default value.
         *
         * Default is "30"
         */
        HEARTBEAT_INTERVAL("heartbeat_interval", "30"),

        /**
         * Heartbeat configure, it's not recommended to modify the default value.
         *
         * Default is "90"
         */
        HEARTBEAT_TIMEOUT("heartbeat_timeout", "90"),

        /**
         * Specify udp packet size, unit is byte.
         *
         * This parameter should be same between client and server.
         *
         * It affects the udp and sudp proxy.
         *
         * Default is "1500"
         */
        UDP_PACKET_SIZE("udp_packet_size", "1500"),

        /**
         * Proxy names you want to start seperated by ',', if set "" means all proxies
         *
         * Default is ""
         */
        START("start", ""),

        /**
         * Authentication method
         *
         * {token, oidc}
         *
         * Default is "token"
         */
        AUTHENTICATION_METHOD("authentication_method", AuthMethod.TOKEN.str),

        /**
         * For authentication, should be same as your frps.ini
         *
         * Specifies whether to include authentication token in heartbeats sent to frps.
         *
         * Default is "false"
         */
        AUTHENTICATE_HEARTBEATS("authenticate_heartbeats", Enable.FALSE.str),

        /**
         * Specifies whether to include authentication token in new work connections sent to frps.
         *
         * Default is "false"
         */
        AUTHENTICATE_NEW_WORK_CONNS("authenticate_new_work_conns", Enable.FALSE.str),

        /**
         * Auth token.
         *
         * Default is ""
         */
        TOKEN("token", ""),

        /**
         * Specifies the client ID to use to get a token in OIDC authentication if AuthenticationMethod == "oidc".
         *
         * Default is ""
         */
        OIDC_CLIENT_ID("oidc_client_id", ""),

        /**
         * Specifies the client secret to use to get a token in OIDC authentication if AuthenticationMethod == "oidc".
         *
         * Default is ""
         */
        OIDC_CLIENT_SECRET("oidc_client_secret", ""),

        /**
         * Specifies the audience of the token in OIDC authentication if AuthenticationMethod == "oidc".
         *
         * Default is ""
         */
        OIDC_AUDIENCE("oidc_audience", ""),

        /**
         * Specifies the URL which implements OIDC Token Endpoint if AuthenticationMethod == "oidc".
         *
         * Default is ""
         */
        OIDC_TOKEN_ENDPOINT_URL("oidc_token_endpoint_url", ""),

        /**
         * Set admin address for control frpc's action by http api such as reload.
         *
         * Default is "0.0.0.0"
         */
        ADMIN_ADDR("admin_addr", "0.0.0.0"),

        /**
         * Set admin port for control frpc's action by http api such as reload.
         *
         * Default is "0"
         */
        ADMIN_PORT("admin_port", "0"),

        /**
         * Set admin user for control frpc's action by http api such as reload.
         *
         * Default is ""
         */
        ADMIN_USER("admin_user", ""),

        /**
         * Set admin password for control frpc's action by http api such as reload.
         *
         * Default is ""
         */
        ADMIN_PWD("admin_pwd", ""),

        /**
         * Set admin web asserts resources path like /path/to/asserts/, by default, these resources build-in lib.
         *
         * Default is ""
         */
        ASSERTS_DIR("asserts_dir", "")
    }

    enum class ConfigProxyBasic(val key: String, val defVal: String) {
        /**
         * Proxy type.
         *
         * {tcp, udp, http, https, stcp, sudp, xtcp, tcpmux}
         *
         * Default is "tcp"
         */
        TYPE("type", ProxyType.TCP.str),

        /**
         * Messages between frps and frpc will be encrypted.
         *
         * Default is "false"
         */
        USE_ENCRYPTION("use_encryption", Enable.FALSE.str),

        /**
         * Messages between frps and frpc will be compressed.
         *
         * Default is "false"
         */
        USE_COMPRESSION("use_compression", Enable.FALSE.str),

        /**
         * frpc will use proxy protocol to transfer connection info to your local service.
         *
         * {none, v1, v2}
         *
         * Default is ""
         */
        PROXY_PROTOCOL_VERSION("proxy_protocol_version", ProxyProtocolVer.NONE.str),

        /**
         * Limit bandwidth for this proxy, unit is KB and MB.
         *
         * Default is ""
         */
        BANDWIDTH_LIMIT("bandwidth_limit", "")
    }


    /**
     * local_ip and plugin must be configured at least one.
     */
    enum class ConfigProxyLocalServer(val key: String, val defVal: String) {
        /**
         * The IP address of the local service to be proxy.
         *
         * Default is "127.0.0.1"
         */
        LOCAL_IP("local_ip", "127.0.0.1"),

        /**
         * Port number of the local service to be proxy.
         *
         * Set "8080-8090" to expose multiple ports, add "range:" prefix to the section name.
         *
         * Default is ""
         */
        LOCAL_PORT("local_port", ""),

        /**
         * If plugin is defined, local_ip and local_port is useless.
         *
         * Plugin will handle connections got from frps.
         *
         * Default is ""
         */
        PLUGIN("plugin", ""),

        /**
         * Params with prefix "plugin_" that plugin needed.
         *
         * e.g: PLUGIN_PARAMS_KEY + "param_key" = "param_value"
         *
         * Default is ""
         */
        PLUGIN_PARAMS_KEY("plugin_", "")
    }

    enum class ConfigProxyGroup(val key: String, val defVal: String) {
        /**
         * Frps will load balancing connections for proxies in same group.
         *
         * Default is ""
         */
        GROUP("group", ""),

        /**
         * Group should have same group key.
         *
         * Default is ""
         */
        GROUP_KEY("group_key", ""),

        /**
         * Enable health check for the backend service.
         *
         * Frpc will connect local service's port to detect it's healthy status.
         *
         * {none, tcp, http}
         *
         * Default is ""
         */
        HEALTH_CHECK_TYPE("health_check_type", ProxyGroupHealthCheckType.NONE.str),

        /**
         * Health check connection timeout.
         *
         * Default is "3"
         */
        HEALTH_CHECK_TIMEOUT_S("health_check_timeout_s", "3"),

        /**
         * If continuous failed in 3 times, the proxy will be removed from frps.
         *
         * Default is "1"
         */
        HEALTH_CHECK_MAX_FAILED("health_check_max_failed", "1"),

        /**
         * Every 10 seconds will do a health check.
         *
         * Default is "10"
         */
        HEALTH_CHECK_INTERVAL_S("health_check_interval_s", "10"),

        /**
         * Frpc will send a GET http request '/url' to local http service.
         *
         * Http service is alive when it return 2xx http response code.
         *
         * Default is ""
         */
        HEALTH_CHECK_URL("health_check_url", "")
    }

    enum class ConfigProxyTcp(val key: String, val defVal: String) {
        /**
         * Remote port listen by frps.
         *
         * If the type is secret tcp, remote_port is useless.
         *
         * Default is ""
         */
        REMOTE_PORT("remote_port", "")
    }

    enum class ConfigProxyUdp(val key: String, val defVal: String) {
        /**
         * Remote port listen by frps.
         *
         * If the type is secret tcp, remote_port is useless.
         *
         * Default is ""
         */
        REMOTE_PORT("remote_port", "")
    }

    /**
     * custom_domains and subdomain must be configured at least one.
     *
     * Both can take effect at the same time.
     */
    enum class ConfigProxyHttp(val key: String, val defVal: String) {
        /**
         * If http request accessed by the user through vhost_http_port is in the domain name configured by custom_domains, it will be routed to the local service configured by this proxy.
         *
         * It can use string array like "web01.example.com,web02.domain.com".
         *
         * Default is ""
         */
        CUNSTOM_DOMAINS("custom_domains", ""),

        /**
         * It has the same function as custom_domains, but only needs to specify the subdomain name prefix, which will be combined with the subdomain_host of the server to generate the final bound domain name.
         *
         * Default is ""
         */
        SUBDOMAIN("subdomain", ""),

        /**
         * Using the rule of maximum prefix matching, the user request matching the location configuration of the response will be routed to this proxy.
         *
         * It can use string array like "/,/pic"
         *
         * Default is ""
         */
        LOCATIONS("locations", ""),

        /**
         * If set http_user, you need access custom_domains with Basic Auth certification.
         *
         * Default is ""
         */
        HTTP_UER("http_user", ""),

        /**
         * Work with http_user.
         *
         * Default is ""
         */
        HTTP_PWS("http_pwd", ""),

        /**
         * Replace the Host field in the HTTP request sent to the local service.
         *
         * Default is ""
         */
        HOST_HEADER_REWRITE("host_header_rewrite", ""),

        /**
         * Params with prefix "header_" will be used to update http request headers.
         *
         * e.g: HEADER_KEY + "header_key" = "header_value"
         *
         * Default is ""
         */
        HEADER_KEY("header_", "")
    }

    /**
     * custom_domains and sub_domain must be configured at least one.
     *
     * Both can take effect at the same time.
     */
    enum class ConfigProxyHttps(val key: String, val defVal: String) {
        /**
         * If http request accessed by the user through vhost_http_port is in the domain name configured by custom_domains, it will be routed to the local service configured by this proxy.
         *
         * It can use string array like "web01.example.com,web02.domain.com".
         *
         * Default is ""
         */
        CUNSTOM_DOMAINS("custom_domains", ""),

        /**
         * It has the same function as custom_domains, but only needs to specify the subdomain name prefix, which will be combined with the subdomain_host of the server to generate the final bound domain name.
         *
         * Default is ""
         */
        SUB_DOMAIN("sub_domain", "")
    }

    enum class ConfigProxyStcp(val key: String, val defVal: String) {
        /**
         * frpc role visitor -> frps -> frpc role server
         *
         * {server, visitor}
         *
         * Default is "server"
         */
        ROLE("role", ProxyRole.SERVER.str),

        /**
         * sk used for authentication for visitors.
         *
         * Default is ""
         */
        SK("sk", "")
    }

    enum class ConfigProxySUdp(val key: String, val defVal: String) {
        /**
         * frpc role visitor -> frps -> frpc role server
         *
         * {server, visitor}
         *
         * Default is "server"
         */
        ROLE("role", ProxyRole.SERVER.str),

        /**
         * sk used for authentication for visitors.
         *
         * Default is ""
         */
        SK("sk", "")
    }

    enum class ConfigProxyXtcp(val key: String, val defVal: String) {
        /**
         * frpc role visitor -> frps -> frpc role server
         *
         * {server, visitor}
         *
         * Default is "server"
         */
        ROLE("role", ProxyRole.SERVER.str),

        /**
         * sk used for authentication for visitors.
         *
         * Default is ""
         */
        SK("sk", "")
    }

    enum class Enable(val str: String) {
        TRUE("true"),
        FALSE("false");

        override fun toString(): String {
            return this.str
        }
    }

    enum class LogLevel(val str: String) {
        TRACE("trace"),
        DEBUG("debug"),
        INFO("info"),
        WARN("warn"),
        ERROR("error");

        override fun toString(): String {
            return this.str
        }
    }

    enum class Protocol(val str: String) {
        TCP("tcp"),
        KCP("kcp"),
        WEBSOCKET("websocket");

        override fun toString(): String {
            return this.str
        }
    }

    enum class AuthMethod(val str: String) {
        TOKEN("token"),
        OIDC("oidc");

        override fun toString(): String {
            return this.str
        }
    }

    enum class ProxyType(val str: String) {
        TCP("tcp"),
        UDP("udp"),
        HTTP("http"),
        HTTPS("https"),
        STCP("stcp"),
        SUDP("sudp"),
        XTCP("xtcp"),
        TCPMUX("tcpmux");

        override fun toString(): String {
            return this.str
        }
    }

    enum class ProxyProtocolVer(val str: String) {
        NONE(""),
        V1("v1"),
        V2("v2");

        override fun toString(): String {
            return this.str
        }
    }

    enum class ProxyGroupHealthCheckType(val str: String) {
        NONE(""),
        TCP("tcp"),
        HTTP("http");

        override fun toString(): String {
            return this.str
        }
    }

    enum class ProxyRole(val str: String) {
        SERVER("server"),
        VISITOR("visitor");

        override fun toString(): String {
            return this.str
        }
    }
}