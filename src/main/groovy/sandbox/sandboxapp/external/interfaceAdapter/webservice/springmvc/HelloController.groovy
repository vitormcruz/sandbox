package sandbox.sandboxapp.external.interfaceAdapter.webservice.springmvc

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping("/hello.do")
    String home() {
        return "Hello World!";
    }
}
