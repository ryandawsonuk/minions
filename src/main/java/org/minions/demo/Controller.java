package org.minions.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Controller {

    private final String version = "0.1";

    private MinionsLibrary minionsLibrary;

    @Value("${spring.application.name}")
    private String appName;

    public Controller(MinionsLibrary minionsLibrary){
        this.minionsLibrary=minionsLibrary;
    }

    @RequestMapping( method=GET)
    @ResponseBody
    public String minion() throws UnknownHostException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Host: ").append(InetAddress.getLocalHost().getHostName()).append("<br/>");
        stringBuilder.append("Minion Type: ").append(appName).append("<br/>");
        stringBuilder.append("IP: ").append(InetAddress.getLocalHost().getHostAddress()).append("<br/>");
        stringBuilder.append("Version: ").append(version).append("<br/>");
        stringBuilder.append(minionsLibrary.getMinion(appName));
        return stringBuilder.toString();
    }
}
