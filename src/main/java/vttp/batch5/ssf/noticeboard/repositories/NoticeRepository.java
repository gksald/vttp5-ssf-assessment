package vttp.batch5.ssf.noticeboard.repositories;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import vttp.batch5.ssf.noticeboard.constant.Constant;
import vttp.batch5.ssf.noticeboard.models.Notice;




@Repository
public class NoticeRepository {

	@Autowired
    @Qualifier(Constant.template01)
    RedisTemplate<String, Object> redisTemplate;

    public void create(String redisKey, String hashKey, String hashValue) {
        redisTemplate.opsForHash().put(redisKey, hashKey, hashValue);
    }

    public Object get(String redisKey, String hashKey) {
        return redisTemplate.opsForHash().get(redisKey, hashKey);
    }

    public Long delete(String redisKey, String hashKey) {
        return redisTemplate.opsForHash().delete(redisKey, hashKey);
    }

    public Boolean keyExists(String redisKey, String hashKey) {
        return redisTemplate.opsForHash().hasKey(redisKey, hashKey);
    }
    
    public static Map<Object, Object> getEntries(String redisKey) {
        return redisTemplate.opsForHash().entries(redisKey);
    }
    
    public Set<Object> getKeys(String redisKey) {
        return redisTemplate.opsForHash().keys(redisKey);
    }

    public List<Object> getValues(String redisKey) {
        return redisTemplate.opsForHash().values(redisKey);
    }


    public Long size(String redisKey) {
        return redisTemplate.opsForHash().size(redisKey);
    }

    public void expire(String redisKey, Long expireValue) {
        Duration expireDuration = Duration.ofSeconds(expireValue);
        redisTemplate.expire(redisKey, expireDuration);
    }



	// TODO: Task 4
	// You can change the signature of this method by adding any number of parameters
	// and return any type
	// 
	/*
	 * Write the redis-cli command that you use in this method in the comment. 
	 * For example if this method deletes a field from a hash, then write the following
	 * redis-cli command 
	 * 	hdel myhashmap a_key
	 *
	 *
	 */

    private String hashRef = "notice";
    private String keyRef = "noticeKey";
    
	public void insertNotices(Notice notice) {
        JsonObject jsonObj = noticeToJson(notice);
		template.opsForHash().put(hashRef, notice.getPoster().toString(), jsonObj.toString());
		template.opsForList().rightPush(keyRef, jsonObj.toString());
	}


}
