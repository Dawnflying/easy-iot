package com.jarvis.easy.protocol.meta;

import com.jarvis.easy.common.feature.HasIdGenerator;
import com.jarvis.easy.common.feature.SupportTextSearch;
import lombok.Data;

/**
 * @author lixiaofei
 */
@Data
public class ProtocolMeta implements HasIdGenerator, SupportTextSearch {

    private String version;

    private String url;

    private String name;

    private String scriptUrl;
}
