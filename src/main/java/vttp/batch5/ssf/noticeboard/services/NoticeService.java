package vttp.batch5.ssf.noticeboard.services;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batch5.ssf.noticeboard.models.Notice;

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
	public void postToNoticeServer(String response) {

        try (JsonReader jsonReader = Json.createReader(new StringReader(response))) {
            JsonObject root = jsonReader.readObject();
            JsonObject noticeJsonObject = root.getJsonObject("noticeJsonObject");

            String title = noticeJsonObject.getString("title");
            String poster = noticeJsonObject.getString("poster");
            Number postDate = noticeJsonObject.getJsonNumber("postDate").numberValue();
            String categories = noticeJsonObject.getString("categories");
            String text = noticeJsonObject.getString("text");

            Notice notice = new Notice(title, poster, postDate, categories, text);

			return notice;
		}
	}
}
