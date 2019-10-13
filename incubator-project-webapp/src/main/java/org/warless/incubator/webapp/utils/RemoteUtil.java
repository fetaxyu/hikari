package org.warless.incubator.webapp.utils;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class RemoteUtil {

    /**
     * Parse the remote address of the channel.
     *
     * @param channel
     * @return
     */
    public static String parseRemoteAddress(final Channel channel) {
        if (null == channel) {
            return "";
        }
        final SocketAddress remote = channel.remoteAddress();
        return doParse(remote != null ? remote.toString().trim() : "");
    }

    /**
     * Parse the local address of the channel.
     *
     * @param channel
     * @return
     */
    public static String parseLocalAddress(final Channel channel) {
        if (null == channel) {
            return "";
        }
        final SocketAddress local = channel.localAddress();
        return doParse(local != null ? local.toString().trim() : "");
    }

    /**
     * Parse the remote host ip of the channel.
     *
     * @param channel
     * @return
     */
    public static String parseRemoteIP(final Channel channel) {
        if (null == channel) {
            return "";
        }
        final InetSocketAddress remote = (InetSocketAddress) channel.remoteAddress();
        if (remote != null) {
            return remote.getAddress().getHostAddress();
        }
        return "";
    }

    /**
     * Parse the remote hostname of the channel.
     * <p>
     * Note: take care to use this method, for a reverse name lookup takes uncertain time in .
     *
     * @param channel
     * @return
     */
    public static String parseRemoteHostName(final Channel channel) {
        if (null == channel) {
            return "";
        }
        final InetSocketAddress remote = (InetSocketAddress) channel.remoteAddress();
        if (remote != null) {
            return remote.getAddress().getHostName();
        }
        return "";
    }

    /**
     * Parse the local host ip of the channel.
     *
     * @param channel
     * @return
     */
    public static String parseLocalIP(final Channel channel) {
        if (null == channel) {
            return "";
        }
        final InetSocketAddress local = (InetSocketAddress) channel.localAddress();
        if (local != null) {
            return local.getAddress().getHostAddress();
        }
        return "";
    }

    /**
     * Parse the remote host port of the channel.
     *
     * @param channel
     * @return int
     */
    public static int parseRemotePort(final Channel channel) {
        if (null == channel) {
            return -1;
        }
        final InetSocketAddress remote = (InetSocketAddress) channel.remoteAddress();
        if (remote != null) {
            return remote.getPort();
        }
        return -1;
    }

    /**
     * Parse the local host port of the channel.
     *
     * @param channel
     * @return int
     */
    public static int parseLocalPort(final Channel channel) {
        if (null == channel) {
            return -1;
        }
        final InetSocketAddress local = (InetSocketAddress) channel.localAddress();
        if (local != null) {
            return local.getPort();
        }
        return -1;
    }

    /**
     * Parse the socket address, omit the leading "/" if present.
     * <p>
     * e.g.1 /127.0.0.1:1234 -> 127.0.0.1:1234
     * e.g.2 sofatest-2.stack.alipay.net/10.209.155.54:12200 -> 10.209.155.54:12200
     *
     * @param socketAddress
     * @return String
     */
    public static String parseSocketAddressToString(SocketAddress socketAddress) {
        if (socketAddress != null) {
            return doParse(socketAddress.toString().trim());
        }
        return "";
    }

    /**
     * Parse the host ip of socket address.
     * <p>
     * e.g. /127.0.0.1:1234 -> 127.0.0.1
     *
     * @param socketAddress
     * @return String
     */
    public static String parseSocketAddressToHostIp(SocketAddress socketAddress) {
        final InetSocketAddress addrs = (InetSocketAddress) socketAddress;
        if (addrs != null) {
            InetAddress addr = addrs.getAddress();
            if (null != addr) {
                return addr.getHostAddress();
            }
        }
        return "";
    }

    /**
     * <ol>
     * <li>if an address starts with a '/', skip it.
     * <li>if an address contains a '/', substring it.
     * </ol>
     *
     * @param addr
     * @return
     */
    private static String doParse(String addr) {
        if (StringUtils.isBlank(addr)) {
            return "";
        }
        if (addr.charAt(0) == '/') {
            return addr.substring(1);
        } else {
            int len = addr.length();
            for (int i = 1; i < len; ++i) {
                if (addr.charAt(i) == '/') {
                    return addr.substring(i + 1);
                }
            }
            return addr;
        }
    }
}

