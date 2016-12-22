package com.bengi.util;

import com.mongodb.ServerAddress;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ding Guo Hua on 12/22/2016.
 */
public class MongoUtils {

    public static List<ServerAddress> parseReplicaSet(String replicaSetString) {
        String[] replicaSetStringArray = StringUtils.commaDelimitedListToStringArray(replicaSetString);
        List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>(replicaSetStringArray.length);

        for (String element : replicaSetStringArray) {

            ServerAddress address = parseServerAddress(element);

            if (address != null) {
                serverAddresses.add(address);
            }
        }

        if (serverAddresses.isEmpty()) {
            throw new IllegalArgumentException("Could not resolve at least one server of the replica set configuration! Validate your config!");
        }
        return serverAddresses;
    }

    private static ServerAddress parseServerAddress(String source) {

        String[] hostAndPort = StringUtils.delimitedListToStringArray(source.trim(), ":");

        if (!StringUtils.hasText(source) || hostAndPort.length > 2) {
            System.out.println("Could not parse address source " + source + ". Check your replica set configuration!");
            return null;
        }
        try {
            return hostAndPort.length == 1 ? new ServerAddress(hostAndPort[0]) : new ServerAddress(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
        }
        catch ( NumberFormatException e ) {
            System.out.println("Could not parse port " + hostAndPort[1] + ". Check your replica set configuration!");
        }

        return null;
    }
}
