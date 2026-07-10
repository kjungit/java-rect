package org.example.feignclient.client;

import org.example.feignclient.dto.DataRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Feign Client 선언부
 * interface라서 우리가 직접 구현하지 않는다.
 * 즉 다른 서버로 HTTP 요청을 보내는 코드를 인터페이스 선언만으로 대신 만들어주는 것.
 */

@FeignClient(name = "exampleClient", url = "${feign-api.url}/api/data")
public interface ExampleClient {
    @GetMapping("/{id}")
    String getData( @PathVariable("id") Long id );

    @PostMapping
    String createData( @RequestBody DataRequest dataRequest );

    @PutMapping("/{id}")
    String updateData( @PathVariable Long id, @RequestBody DataRequest dataRequest );

    @DeleteMapping("/{id}")
    String deleteData( @PathVariable Long id );
}
