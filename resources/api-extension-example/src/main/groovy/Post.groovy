import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.bonitasoft.web.extension.rest.*
import javax.servlet.http.HttpServletRequest

public class Post implements RestApiController {

    @Override
    RestApiResponse doHandle(HttpServletRequest request, RestApiResponseBuilder apiResponseBuilder, RestAPIContext context) {
        String joinRequest = request.reader.readLines().join("\n")
        Map<String, String> response = [:]
        def slurper = new JsonSlurper()
        if (joinRequest != null && !joinRequest.empty) {
            def requestParameters = slurper.parseText(joinRequest)
            response.with{
                put "response", "hello from post resource with json payload"
                putAll requestParameters
            }
            apiResponseBuilder.withResponse new JsonBuilder(response).toPrettyString()
        }
        else{
            response.put "response", "hello from post resource with empty payload"
            apiResponseBuilder.withResponse new JsonBuilder(response).toPrettyString()
        }
        apiResponseBuilder.build()
    }

}
