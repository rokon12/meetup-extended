package com.bazlur.meetup.extended.integration.meetup;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/30/17.
 * https://www.youtube.com/watch?v=o69o-U5ZxWU
 */
@Service
public class MeetupClient {
    private static final Logger LOGGER = getLogger(MeetupClient.class);

    @Value("${meetup.api.key}")
    private String apiToken = "523b6e47215429147e4633354840327c";

    private final RestTemplate restTemplate;

    public MeetupClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.additionalCustomizers(rt ->
                rt.getInterceptors().add(new MeetupAppTokenInterceptor(apiToken)))
                .build();

        LOGGER.info("token:{}", apiToken);
    }

    public List<Meetup> getRecentNews() {
        ResponseEntity<Meetup[]> response = doGetRecentNews();

        return Arrays.asList(response.getBody());
    }

    private ResponseEntity<Meetup[]> doGetRecentNews() {
        String url = String.format("https://api.meetup.com/%s/events", "jug-bd");

        return invoke(createRequestEntity(url), Meetup[].class);
    }

    private <T> ResponseEntity<T> invoke(RequestEntity<?> request, Class<T> type) {
        try {
            return this.restTemplate.exchange(request, type);
        } catch (RestClientException ex) {
            throw ex;
        }
    }

    private RequestEntity<?> createRequestEntity(String url) {
        try {
            return RequestEntity.get(new URI(url))
                    .accept(MediaType.APPLICATION_JSON).build();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("Invalid URL " + url, ex);
        }
    }

    private static class MeetupAppTokenInterceptor implements ClientHttpRequestInterceptor {

        private final String token;

        MeetupAppTokenInterceptor(String token) {
            this.token = token;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                            ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

            if (StringUtils.hasText(this.token)) {
                byte[] basicAuthValue = this.token.getBytes(StandardCharsets.UTF_8);
                httpRequest.getHeaders().set(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString(basicAuthValue));
            }
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }
    }
}
