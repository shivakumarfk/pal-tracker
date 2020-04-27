package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String PORT;
    private String MEMORY_LIMIT;
    private String CF_INSTANCE_INDEX;
    private String CF_INSTANCE_ADDR;

       public EnvController(@Value("${PORT:NOT SET}") String s,
                            @Value("${MEMORY_LIMIT:NOT SET}") String s1,
                            @Value("${CF_INSTANCE_INDEX:NOT SET}") String s2,
                            @Value("${CF_INSTANCE_ADDR:NOT SET}") String s3) {
        this.PORT = s;
        this.CF_INSTANCE_ADDR = s3;
        this.MEMORY_LIMIT = s1;
        this.CF_INSTANCE_INDEX = s2;

    }


    @GetMapping("/env")
    public Map <String, String> getEnv() throws Exception{

        Map <String, String> env = new HashMap<String,String >();

        env.put("PORT",PORT);
        System.out.println("PORT" + PORT);
        env.put("MEMORY_LIMIT",MEMORY_LIMIT);
        env.put("CF_INSTANCE_ADDR",CF_INSTANCE_ADDR);
        env.put("CF_INSTANCE_INDEX",CF_INSTANCE_INDEX);
        return env;
    }

}
