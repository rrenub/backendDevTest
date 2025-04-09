package rrenub.com.backenddevtest.myapp.myapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rrenub.com.backenddevtest.myapp.myapp.client.ExternalProductClient;
import rrenub.com.backenddevtest.myapp.myapp.dto.ProductDto;

import java.util.List;
import java.util.Objects;

@Service
public class ProductsService {

    private ExternalProductClient externalApiClient;

    public ProductsService(ExternalProductClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    public Flux<ProductDto> getSimilarProducts(String productId) {
        Mono<List<String>> relatedProductIdsMono = externalApiClient.getRelatedProductIds(productId);

        return relatedProductIdsMono.flatMapMany(relatedProductIds ->
                Flux.fromIterable(relatedProductIds)
                    .flatMap(id -> externalApiClient.getProductDetails(id))
        );
    }
}
