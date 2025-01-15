package com.example.beer_app.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author mamula.lukas@gmail.com
 */
@Slf4j
public class EndpointPrinterUtil {
    public static void printToFile(Map<RequestMappingInfo, HandlerMethod> handlerMethods) throws IOException {
        var publicDir = getOutputFolderPath();
        var endpointsFilePath = publicDir.resolve("index.html");
        Files.deleteIfExists(endpointsFilePath);
        var outputFile = Files.createFile(endpointsFilePath);

        Map<RequestMappingInfo, HandlerMethod> sorted = new TreeMap<>(requestMappingInfoComparator());
        sorted.putAll(handlerMethods);

        sorted.forEach((requestMappingInfo, handlerMethod) -> {
            try {
                Files.writeString(outputFile, requestMappingInfo.toString() + ", " + handlerMethod + "<br/>", StandardOpenOption.APPEND);
            } catch (IOException e) {
                log.error("Couldn't write {}, {}", requestMappingInfo, handlerMethod);
            }
        });
    }

    private static Path getOutputFolderPath() throws IOException {
        var publicDir = Paths.get("./generated").toAbsolutePath();
        if (Files.isDirectory(publicDir)) {
            return publicDir;
        }

        return Files.createDirectory(publicDir);
    }

    private static Comparator<RequestMappingInfo> requestMappingInfoComparator() {
        return (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            if (o1.getPathPatternsCondition() == null && o2.getPathPatternsCondition() == null) {
                return 0;
            }
            if (o1.getPathPatternsCondition() == null) {
                return 1;
            }
            if (o2.getPathPatternsCondition() == null) {
                return -1;
            }
            var o1str = o1.getPathPatternsCondition() + " " + o1.getMethodsCondition();
            var o2str = o2.getPathPatternsCondition() + " " + o2.getMethodsCondition();
            return o1str.compareTo(o2str);
        };
    }
}
