package sandbox

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping("/hello")
    String home() {
        return "Hello World!";
    }
}
