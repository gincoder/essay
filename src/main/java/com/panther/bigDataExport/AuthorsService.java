package com.panther.bigDataExport;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class AuthorsService {
    private final SqlSessionTemplate sqlSessionTemplate;
    private final AuthorsMapper authorsMapper;

    public AuthorsService(SqlSessionTemplate sqlSessionTemplate, AuthorsMapper authorsMapper) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.authorsMapper = authorsMapper;
    }

    /**
     * stream读数据写文件方式
     *
     * @param httpServletResponse
     * @throws IOException
     */
    public void streamDownload(HttpServletResponse httpServletResponse)
            throws IOException {

        CustomResultHandler customResultHandler = new CustomResultHandler(new DownloadProcessor(httpServletResponse));
        sqlSessionTemplate.select(
                "com.panther.bigDataExport.AuthorsMapper.streamByExample", customResultHandler);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }

    /**
     * 传统下载方式
     *
     * @param httpServletResponse
     * @throws IOException
     */
    public void traditionDownload(HttpServletResponse httpServletResponse)
            throws IOException {
        List<Authors> authors = authorsMapper.selectByExample();
        DownloadProcessor downloadProcessor = new DownloadProcessor(httpServletResponse);
        authors.forEach(downloadProcessor::processData);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }
}
