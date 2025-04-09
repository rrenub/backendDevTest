package rrenub.com.backenddevtest.myapp.myapp.client;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rrenub.com.backenddevtest.myapp.myapp.configuration.ExternalApiConfiguration;
import rrenub.com.backenddevtest.myapp.myapp.dto.ProductDto;

import java.util.List;

@Component
public class ExternalProductClient {

    private final WebClient webClient;

    public ExternalProductClient(ExternalApiConfiguration externalApiConfiguration) {
        this.webClient = WebClient
                .builder()
                .baseUrl("http://localhost:3001")
                .build();
    }

    public Mono<List<String>> getRelatedProductIds(String productId) {
        return webClient.get()
                .uri("/product/{id}/similarids", productId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {});
    }

    @Cacheable(value = "products", key = "#productId")
    public Mono<ProductDto> getProductDetails(String productId) {
        return webClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .onErrorResume(e -> Mono.empty());
    }

}
