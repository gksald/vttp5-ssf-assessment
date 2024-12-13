package vttp.batch5.ssf.noticeboard.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NoticeService {

	    private final RestTemplate restTemplate = new RestTemplate();

		@Value("${api.key}")
   	 	private String apiKey;

    	@Value("${publishingnotice.api.base-url}")
    	private String publishingnoticeApiBaseUrl;

	// TODO: Task 3
	// You can change the signature of this method by adding any number of parameters
	// and return any type
	public void postToNoticeServer() {

	}
}
