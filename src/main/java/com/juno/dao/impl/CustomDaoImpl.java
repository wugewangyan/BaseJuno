package com.juno.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.juno.dao.CustomDao;
import com.juno.model.Custom;

@Repository
public class CustomDaoImpl implements CustomDao {

	@Autowired  
	private RedisTemplate<Serializable, Serializable> redisTemplate;  
	
	@Override
	public void save(final Custom custom) {
		redisTemplate.execute(new RedisCallback<Object>(){
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.setNX(redisTemplate.getStringSerializer().serialize("custom.customId." + custom.getCustomId()),
						redisTemplate.getStringSerializer().serialize("custom.customId." + custom.getCustomName()));
				return null;
			}
		});
	}

	@Override
	public Custom get(final String customId) {
		return redisTemplate.execute(new RedisCallback<Custom>(){
			@Override
			public Custom doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("custom.customId." + customId);
				if(connection.exists(key)){
					byte[] value = connection.get(key);
					String customName = redisTemplate.getStringSerializer().deserialize(value);
					Custom custom = new Custom();
					custom.setCustomId(customId);
					custom.setCustomName(customName);
					return custom;
				}
				
				return null;
			}
		});
	}
	
	@Override
	public void delete(final String customId) {
		redisTemplate.execute(new RedisCallback<Object>(){
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.del(redisTemplate.getStringSerializer().serialize("custom.customId." + customId));
				return null;
			}
		});
	}

}
