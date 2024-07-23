package com.panther.bigDataExport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringBoot 实现 MySQL 百万级数据量导出并避免 OOM 的解决方案
 * 比较stream流读取与传统方案对内存占用的大小
 */
@RestController
@RequestMapping("download")
@Slf4j
public class DataExprotController {
    private final AuthorsService authorsService;

    public DataExprotController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("streamDownload")
    public void streamDownload(HttpServletResponse response)
            throws IOException {
        long start = System.currentTimeMillis();
        authorsService.streamDownload(response);
        log.info("streamDownload耗时：{}",(System.currentTimeMillis()-start));
    }

    @GetMapping("traditionDownload")
    public void traditionDownload(HttpServletResponse response)
            throws IOException {
        long start = System.currentTimeMillis();
        authorsService.traditionDownload(response);
        log.info("traditionDownload耗时：{}",(System.currentTimeMillis()-start));
    }



}
