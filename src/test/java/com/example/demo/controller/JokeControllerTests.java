package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.Joke;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class JokeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllJokesTest() throws Exception{
        mockMvc.perform(get("/api/jokes"))
            .andExpect(status().isOk());
    }

    @Test
    public void jokeCURD() throws Exception{
        Joke joke = new Joke();
        joke.setTitle("title");
        joke.setContent("content");

        JSONObject object = new JSONObject();
        object.put("title", joke.getTitle());
        object.put("content", joke.getContent());

        MvcResult mvcResult = mockMvc.perform(post("/api/jokes")
                            .content(object.toJSONString())
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();
        String resStr = mvcResult.getResponse().getContentAsString();
        JSONObject resObject =  JSONObject.parseObject(resStr);
        assertThat(resObject).isNotNull();
        assertThat(resObject.getString("title")).isEqualTo("title");

        // 修改
        int id = resObject.getInteger("id");
        joke.setId(id);
        joke.setTitle("TITLE");
        mvcResult = mockMvc.perform(put("/api/jokes/"+id)
                            .content(JSONObject.toJSONString(joke))
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();
        object = JSONObject.parseObject(mvcResult.getResponse().getContentAsString());
        assertThat(object.getString("title")).isEqualTo("TITLE");

        //  删除
        // mockMvc.perform(delete("/api/jokes"))
    }
}