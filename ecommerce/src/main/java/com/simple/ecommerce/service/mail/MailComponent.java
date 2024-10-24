package com.simple.ecommerce.service.mail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name="mailgun", url="https://api.mailgun.net/v3/")
public interface MailComponent {

    // @Value("${mailgun.api.key}")
    // private String API_KEY;
    
    // public JsonNode sendSimpleMessage() throws UnirestException {
    //     HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/sandboxe95dc03f6b1e43fe876fd4bf447a64a8.mailgun.org/messages")
    //         .basicAuth("api", API_KEY)
    //         .queryString("from", "Excited User <USER@sandboxe95dc03f6b1e43fe876fd4bf447a64a8.mailgun.org>")
    //         .queryString("to", "tjdwns6872@naver.com")
    //         .queryString("subject", "hello")
    //         .queryString("text", "testing")
    //         .asJson();
    //     return request.getBody();
  	// }

    @PostMapping("sandboxe95dc03f6b1e43fe876fd4bf447a64a8.mailgun.org/messages")
    ResponseEntity<String> sendEmail(@SpringQueryMap SendMailForm mailForm);
}
