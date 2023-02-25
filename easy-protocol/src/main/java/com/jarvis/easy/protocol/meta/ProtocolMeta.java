package com.jarvis.easy.protocol.meta;

import com.jarvis.easy.common.feature.IdGeneratorInterface;
import com.jarvis.easy.common.feature.TextSearchInterface;
import lombok.Data;

/**
 * @author lixiaofei
 */
@Data
public class ProtocolMeta implements IdGeneratorInterface, TextSearchInterface {

    private String version;

    private String url;

    private String name;

    private String scriptUrl;
}
