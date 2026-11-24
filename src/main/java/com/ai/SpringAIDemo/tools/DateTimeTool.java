package com.ai.springaidemo.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class DateTimeTool {
    private static final Logger log = LoggerFactory.getLogger(DateTimeTool.class);
    private static final ZoneId VIETNAM_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");

    @Tool(description = "Lấy ngày hiện tại theo chuẩn ISO-8601 (định dạng yyyy-MM-dd)")
    public String getCurrentDate() {
        log.info("Getting current date");
        return LocalDate.now(VIETNAM_ZONE).toString();
    }

    @Tool(description = "Lấy giờ hiện tại chính xác theo múi giờ Việt Nam (định dạng HH:mm:ss)")
    public String getCurrentTime() {
        log.info("Getting current time");
        return LocalTime.now(VIETNAM_ZONE)
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
