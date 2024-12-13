package vttp.batch5.ssf.noticeboard.controllers;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.validation.Valid;
import vttp.batch5.ssf.noticeboard.constant.Constant;
import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.repositories.NoticeRepository;

// Use this class to write your request handlers
@Controller
@RequestMapping(path = "/noticeboard")
public class NoticeController {

    @Autowired
    NoticeRepository noticeRepo;
    // @Qualifier("notice")
    // RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/notice")
    public String createNotice(Model model) {
        
        Notice notice = new Notice();
        model.addAttribute("notice", notice);
        return "notice";
    }


    @PostMapping("/notice")
    public String postNotice(@Valid @ModelAttribute("notice") Notice entity, BindingResult results, Model model) {

        System.out.println(entity.toString());
        System.out.println(entity.getPostDate());

        if (results.hasErrors())
            return "notice";

        // serialise to JsonObject, then save the Jsonobject as a string using Map
        JsonObject jObject = Json.createObjectBuilder()
        .add("title", entity.getTitle())
        .add("poster", entity.getPoster())
        .add("postDate", entity.getPostDate().toString())
        .add("categories", (JsonValue) entity.getCategories())
        .add("text", entity.getText().build());
        noticeRepo.create(Constant.noticeKey, entity.getTitle().toString(), jObject.toString());
        
        return "redirect:/noticeboard/list";
    }

    
    @GetMapping("/list")
    public String personList(Model model) throws ParseException {
        Map<Object, Object> noticesObject = NoticeRepository.getEntries(Constant.noticeKey);

        List<Notice> notices = new ArrayList<>();

        for(Map.Entry<Object, Object> entry: noticesObject.entrySet()) {
            String stringValue = entry.getValue().toString();
            JsonReader jReader = Json.createReader(new StringReader(stringValue));
            JsonObject jObject = jReader.readObject();

            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zz yyyy");
            Date newPostDate = sdf.parse(jObject.getString("postDate"));

            notices.add(new Notice((jObject.getString("title")), jObject.getString("poster"), newPostDate, jObject.getString("categories"), jObject.getString("text")));

        }

        model.addAttribute("notices", notices);
        return "noticelist";
    }


    // Healthcheck

    @GetMapping("/health")
    public ResponseEntity<String> getMethodName() {
      Random randomKey = new Random();
      Integer randomValue = randomKey.nextInt(0, 10);

        if (randomValue > 0) {
            return ResponseEntity.ok("{}");
        }
        return ResponseEntity.status(503).body("{}");
   }
}